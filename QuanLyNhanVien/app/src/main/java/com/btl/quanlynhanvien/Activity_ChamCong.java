package com.btl.quanlynhanvien;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.btl.Adapter.ChamcongAdapter;
import com.btl.db.DBHelper;
import com.btl.model.Account;
import com.btl.model.ChamCong;
import com.btl.model.Employee;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;


public class Activity_ChamCong extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    TextView name_ep;
    Button btn_chamcong;
    ListView lv_chamcong;
    String username = "", pass = "";
    ArrayList<Account> arrayList=new ArrayList<>();
    ArrayList<ChamCong> listChamCong = new ArrayList<>();
    ArrayList<Employee> listNhanvien = new ArrayList<>();
    DBHelper dbHelper;
    int idnv;
    LocalDate date;
    String Date;
    Spinner spinner;
    String Month="";

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chamcong);
        date = LocalDate.now();
        Date = String.valueOf(date);

        Intent intent=getIntent();
        name_ep = (TextView)findViewById(R.id.txt_name);
        btn_chamcong = (Button)findViewById(R.id.btn_chamcong);
        lv_chamcong = (ListView)findViewById(R.id.lv_chamcong);

        spinner = (Spinner)findViewById(R.id.spinner_chamcong);


        Date date1=new Date();
        Month=String.valueOf(date1.getMonth()+1);



        String[] arr=new String[]{
                "01", "02", "03","04", "05", "06","07", "08", "09","10", "11", "12",
        };
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arr);
        spinner.setAdapter(adapter);
        spinner.setSelection(date1.getMonth());
        spinner.setOnItemSelectedListener(this);


        Bundle bundle=intent.getBundleExtra("Data_account");
        if (bundle != null){
            username = (String) bundle.getSerializable("username");
            pass = (String)bundle.getSerializable("password");
            dbHelper = new DBHelper(this);


            arrayList = dbHelper.getAcCounttouser(username, pass);
            String[] arrac=new String[arrayList.size()];
            for(int i=0;i<arrayList.size();i++){
                arrac[i]= String.valueOf(arrayList.get(i).getIdnv());
                idnv =Integer.parseInt(String.format(arrac[i])) ;
            }

            dbHelper = new DBHelper(this);
            listChamCong = dbHelper.getallchamcongtoid(idnv,Month);
            ChamcongAdapter chamcongAdapter = new ChamcongAdapter(Activity_ChamCong.this, R.layout.item_chamcong, listChamCong);
            lv_chamcong.setAdapter(chamcongAdapter);

            name_ep.setText("Nhân viên: "+dbHelper.getEmployee(idnv).getName());
        }
        if (Checknotdate(Date, idnv) == false){
            btn_chamcong.setText("Đã chấm công ");
        }


        btn_chamcong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = LocalDate.now();
                Date = String.valueOf(date);
                if (Checknotdate(Date, idnv)){
                    dbHelper.addChamcong(new ChamCong(1,idnv, Date));
                    btn_chamcong.setText("Đã chấm công");
                    recreate();
                }
//

            }
        });

    }

    boolean Checknotdate(String date, int id){
        if(dbHelper.isNotDate(date, id)){
            return true;
        }
        return false;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Month = parent.getItemAtPosition(position).toString();
        listChamCong = dbHelper.getallchamcongtoid(idnv,Month);
        ChamcongAdapter chamcongAdapter = new ChamcongAdapter(Activity_ChamCong.this, R.layout.item_chamcong, listChamCong);
        lv_chamcong.setAdapter(chamcongAdapter);
        Toast.makeText(parent.getContext(), "Tháng: " + Month, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}