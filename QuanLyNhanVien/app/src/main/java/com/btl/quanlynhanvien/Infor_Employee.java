package com.btl.quanlynhanvien;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.btl.db.DBHelper;
import com.btl.model.Employee;
import com.btl.model.Luong;
import com.btl.model.PhongBan;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class Infor_Employee extends AppCompatActivity implements View.OnClickListener {

    ArrayList<PhongBan> phongBan = new ArrayList<>();
    ArrayList<Luong> bacLuongs = new ArrayList<>();

    ImageView imageView;
    TextInputEditText inpedt_name,inpedt_addr,inpedt_phone;
    TextInputLayout textInputLayout_name,textInputLayout_address,textInputLayout_phone,textInputLayout_chucvu, textInputLayout_phongban, textInputLayout_bacluong;
    RadioButton radioButton_nam,radioButton_nu;
    RadioGroup radioGroup;
    AutoCompleteTextView autoCompleteTextView, autoCompleteTextViewPhongban, autoCompleteTextViewBacluong;
    DBHelper dbHelper=new DBHelper(this);
    String GT="Nam";
    int ID;
    boolean isadd=true;
    String name;
    String chucvu="";
    String phone;
    String namepb = "";
    int IDPB = -1;
    int id_bacluong= -1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        if(isadd){
            menuInflater.inflate(R.menu.my_menu_add_person,menu);
        }
        else{
            menuInflater.inflate(R.menu.my_menu_employee,menu);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        String name=inpedt_name.getText().toString();
        String address=inpedt_addr.getText().toString();
        String phone=inpedt_phone.getText().toString();
        if(isValidateData(name,chucvu,address,phone)) {
            if (item.getItemId() == R.id.contact) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + phone));
                if (ActivityCompat.checkSelfPermission(Infor_Employee.this,
                        Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Infor_Employee.this, new String[]{Manifest.permission.CALL_PHONE}, 100);
                }
                startActivity(callIntent);
            } else if (item.getItemId() == R.id.add_person) {


                dbHelper.addEmployee(new Employee(1, inpedt_name.getText().toString(), GT, inpedt_addr.getText().toString(),
                        inpedt_phone.getText().toString(), chucvu.toString(),IDPB, id_bacluong));

                Intent intent = new Intent(Infor_Employee.this, Activity_Employee.class);
                startActivity(intent);
                finish();
            } else if (item.getItemId() == R.id.update_person) {
                dbHelper.updateEmployee(new Employee(ID, inpedt_name.getText().toString(), GT,
                        inpedt_addr.getText().toString(), inpedt_phone.getText().toString(), chucvu, IDPB, id_bacluong));

                Intent intent = new Intent(Infor_Employee.this, Activity_Employee.class);
                startActivity(intent);
                finish();
            }else if(item.getItemId()==R.id.delete_person){
                deleteEmployee();
            }

        }
        return super.onOptionsItemSelected(item);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_employee);
        Intent intent=getIntent();
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle("Nhân viên");
        }

        imageView=(ImageView)findViewById(R.id.info_img);
        inpedt_name=(TextInputEditText) findViewById(R.id.ipedt_name);
        inpedt_addr=(TextInputEditText) findViewById(R.id.ipedt_address);
        inpedt_phone=(TextInputEditText) findViewById(R.id.ipedt_phone);
        autoCompleteTextView=(AutoCompleteTextView)findViewById(R.id.autoComplete);
        autoCompleteTextViewPhongban=(AutoCompleteTextView)findViewById(R.id.lb_phongban);
        autoCompleteTextViewBacluong=(AutoCompleteTextView)findViewById(R.id.autoCompletebacluong) ;

        radioButton_nam=(RadioButton)findViewById(R.id.radio_nam);
        radioButton_nu=(RadioButton)findViewById(R.id.radio_nu);
        radioGroup=(RadioGroup)findViewById(R.id.radiogroup);

        textInputLayout_name=(TextInputLayout)findViewById(R.id.textInputLayout_name);
        textInputLayout_address=(TextInputLayout)findViewById(R.id.textInputLayout_address);
        textInputLayout_phone=(TextInputLayout)findViewById(R.id.textInputLayout_phone);
        textInputLayout_chucvu=(TextInputLayout)findViewById(R.id.textInputLayout_chucvu);
        textInputLayout_phongban=(TextInputLayout)findViewById(R.id.textInputLayout_phongban);
        textInputLayout_bacluong = (TextInputLayout)findViewById(R.id.textInputLayout_bacluong);

        TextChange();
        String[] arr=new String[]{
          "Kế toán", "Quản lý", "Kỹ thuật"
        };

        ArrayAdapter adapter=new ArrayAdapter(this,R.layout.dropdown,arr);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                chucvu=parent.getItemAtPosition(position).toString();
                textInputLayout_chucvu.setHelperText("");
            }
        });

        phongBan = dbHelper.getAllPhongBan();
        String[] arrpb=new String[phongBan.size()];
        for(int i=0;i<phongBan.size();i++){
            arrpb[i]=phongBan.get(i).getTenPB();
        }

        ArrayAdapter adapterpb=new ArrayAdapter(this,R.layout.dropdown,arrpb);
        autoCompleteTextViewPhongban.setAdapter(adapterpb);
        autoCompleteTextViewPhongban.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IDPB = phongBan.get(position).getIdPB();
                textInputLayout_phongban.setHelperText("");
            }
        });

        bacLuongs = dbHelper.getAllBacLuong();
        String[] arrbl=new String[bacLuongs.size()];
        for(int i=0;i<bacLuongs.size();i++){
            arrbl[i]= String.valueOf(bacLuongs.get(i).getIdBacLuong());
        }
        ArrayAdapter adapterHSL = new ArrayAdapter(this, R.layout.dropdown, arrbl);
        autoCompleteTextViewBacluong.setAdapter(adapterHSL);
        autoCompleteTextViewBacluong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                id_bacluong = bacLuongs.get(position).getIdBacLuong();
                textInputLayout_bacluong.setHelperText("");
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb=(RadioButton)findViewById(checkedId);
                if(rb.getText().equals("Nam")){
                    imageView.setImageResource(R.drawable.profile);
                    GT="Nam";
                }
                else{
                    imageView.setImageResource(R.drawable.girl);
                    GT="Nữ";
                }
            }
        });


        radioButton_nam.setChecked(true);
        imageView.setImageResource(R.drawable.profile);

        Bundle bundle=intent.getBundleExtra("Data");

        if(bundle!=null){
            isadd=false;
            Employee employee= (Employee) bundle.getSerializable("Employee");

            if(employee.getGioiTinh().equals("Nam")){
                radioButton_nam.setChecked(true);
                imageView.setImageResource(R.drawable.profile);
            }
            else{
                radioButton_nu.setChecked(true);
                imageView.setImageResource(R.drawable.girl);
            }



            ID=employee.getId();
            name=employee.getName();
            chucvu=employee.getChucVu();
            id_bacluong =employee.getId_bacluong();
            String id = String.valueOf(id_bacluong);
            IDPB = employee.getIDPB();
            phone=employee.getSdt();
            inpedt_name.setText(employee.getName());
            inpedt_addr.setText(employee.getAddr());
            inpedt_phone.setText(employee.getSdt());
            autoCompleteTextView.setText(employee.getChucVu(),false);

            ArrayList<PhongBan> phongBanArrayList = new ArrayList<>();
            phongBanArrayList = dbHelper.getPhongBanName(IDPB);
            for(int i=0;i<phongBanArrayList.size();i++){
                namepb = phongBanArrayList.get(i).getTenPB();
            }
            autoCompleteTextViewPhongban.setText(namepb, false);
            autoCompleteTextViewBacluong.setText(id, false);
        }


    }

    @Override
    public void onClick(View v) {
//
    }
    void deleteEmployee(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Xóa nhân viên");
        builder.setMessage("Bạn có chắc chắn muốn xóa "+name+" ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(dbHelper.deleteEmployee(ID)==1){
                    Intent intent = new Intent(Infor_Employee.this, Activity_Employee.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(Infor_Employee.this,"Không thể xóa nhân viên",Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }
    boolean isValidatePhone(String p){
        String pattern="0+[0-9]{9}";
        if(p.equals("")){
            textInputLayout_phone.setHelperText("Required*");
            return false;
        }
        else {
            if(!p.matches(pattern)){
                textInputLayout_phone.setHelperText("Error phone number*");
                return false;
            }
        }
        return true;
    }
    boolean isValidateName(String s){
        if(s.equals("")){
            textInputLayout_name.setHelperText("Required*");
            return false;
        }
        return true;
    }
    boolean isValidateAddress(String s){
        if(s.equals("")){
            textInputLayout_address.setHelperText("Required*");
            return false;
        }
        return true;
    }
    boolean isValidatephongban(int s){
        if(s == -1){
            textInputLayout_phongban.setHelperText("Required*");
            return false;
        }
        return true;
    }
    boolean isValidatebacluong(int s){
        if(s == -1){
            textInputLayout_bacluong.setHelperText("Required*");
            return false;
        }
        return true;
    }
    boolean isValidateChucvu(String s){
        if(s.equals("")){
            textInputLayout_chucvu.setHelperText("Required*");
            return false;
        }
               return true;
    }
    boolean isValidateData(String name,String chucvu,String address,String phone){
        if(!isValidateName(name)&&!isValidateAddress(address)&&!isValidateChucvu(chucvu)&&!isValidatebacluong(id_bacluong)&&isValidatephongban(IDPB)&& !isValidatePhone(phone)){
            textInputLayout_name.setHelperText("Required*");
            textInputLayout_address.setHelperText("Required*");
            textInputLayout_phone.setHelperText("Required*");
            textInputLayout_phongban.setHelperText("Required*");
            textInputLayout_chucvu.setHelperText("Required*");
            textInputLayout_bacluong.setHelperText("Required*");
            return false;
        }
        if(!isValidateName(name)||!isValidateChucvu(chucvu)||!isValidatephongban(IDPB) || !isValidateAddress(address)||!isValidatebacluong(id_bacluong) ||!isValidatePhone(phone)){
            return false;
        }
        return true;
    }
    void TextChange(){
        inpedt_name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(inpedt_name.getText().toString().equals("")){
                    textInputLayout_name.setHelperText("Required*");
                }
                else{
                    textInputLayout_name.setHelperText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inpedt_addr.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(inpedt_addr.getText().toString().equals("")){
                    textInputLayout_address.setHelperText("Required*");
                }
                else{
                    textInputLayout_address.setHelperText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        inpedt_phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(inpedt_phone.getText().toString().equals("")){
                    textInputLayout_phone.setHelperText("Required*");
                }
                else{
                    textInputLayout_phone.setHelperText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}