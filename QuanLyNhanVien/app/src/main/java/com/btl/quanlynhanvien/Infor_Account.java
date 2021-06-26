package com.btl.quanlynhanvien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.btl.db.DBHelper;
import com.btl.model.Account;
import com.btl.model.Employee;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Infor_Account extends AppCompatActivity {

    TextInputEditText ipedt_user,ipedt_pass;
    TextInputLayout textInputLayout_user,textInputLayout_pass,textInputLayout_employee;
    AutoCompleteTextView completeTextView;
    DBHelper dbHelper;
    boolean isAdd=true;
    int idnv;
    ArrayList<Employee>employees=new ArrayList<>();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(isAdd){
            MenuInflater menuInflater=getMenuInflater();
            menuInflater.inflate(R.menu.my_menu_add_account,menu);

        }
        else{
            MenuInflater menuInflater=getMenuInflater();
            menuInflater.inflate(R.menu.my_menu_account,menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add_account){
            if(!dbHelper.isExistsAcCount(ipedt_user.getText().toString())){
                dbHelper.addAcCount(new Account(idnv, ipedt_user.getText().toString()
                        , ipedt_pass.getText().toString()));
                Intent intent = new Intent(Infor_Account.this, Activity_Account.class);
                startActivity(intent);
                finish();
            }
            else{
                Toast.makeText(Infor_Account.this, "Tài khoản đã tồn tại "  , Toast.LENGTH_SHORT).show();
            }
        }
        else if(item.getItemId()==R.id.delete_account){
            dbHelper.deleteAccount(idnv);
            Intent intent = new Intent(Infor_Account.this, Activity_Account.class);
            startActivity(intent);
            finish();
        }
        if(item.getItemId()==R.id.update_account){
            Account acCount=new Account(ipedt_user.getText().toString(),ipedt_pass.getText().toString());
            if(!dbHelper.isExistsAcCount(acCount)){
                dbHelper.updateAccount(new Account(idnv, ipedt_user.getText().toString()
                        , ipedt_pass.getText().toString()));
                Intent intent = new Intent(Infor_Account.this, Activity_Account.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(Infor_Account.this, "Tài khoản đã tồn tại "  , Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor_ac_count);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle("Tài khoản");
        }
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("data");
        completeTextView=(AutoCompleteTextView)findViewById(R.id.autoComplete_employee);
        ipedt_user=(TextInputEditText)findViewById(R.id.ipedt_user);
        ipedt_pass=(TextInputEditText)findViewById(R.id.ipedt_password);
        textInputLayout_employee=(TextInputLayout)findViewById(R.id.textInputLayout_employee);
        textInputLayout_user=(TextInputLayout)findViewById(R.id.textInputLayout_user);
        textInputLayout_pass=(TextInputLayout)findViewById(R.id.textInputLayout_password);
        dbHelper=new DBHelper(this);
        employees=dbHelper.getEmployeeNotAC();
        String[] arr=new String[employees.size()];
        for(int i=0;i<employees.size();i++){
            arr[i]=employees.get(i).getName();
        }

        ArrayAdapter adapter=new ArrayAdapter(this,R.layout.dropdown,arr);
        completeTextView.setAdapter(adapter);
        completeTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                idnv=employees.get(position).getId();

            }
        });
        if(bundle!=null){
            isAdd=false;
            Account acCount=(Account)bundle.getSerializable("account");
            idnv=acCount.getIdnv();
            ipedt_user.setText(acCount.getUser());
            ipedt_pass.setText(acCount.getPassword());
            completeTextView.setText(dbHelper.getEmployee(acCount.getIdnv()).getName());
        }
    }
}