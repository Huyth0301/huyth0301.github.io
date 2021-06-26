package com.btl.quanlynhanvien;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.btl.Adapter.BangluongAdapter;
import com.btl.db.DBHelper;
import com.btl.model.Employee;

import java.util.ArrayList;
import java.util.Date;

public class Activity_BangLuong extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    ListView lv_bangluong;
    DBHelper dbHelper;
    ArrayList<Employee> employeeArrayList = new ArrayList<>();
    String Month="";


    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acctivity__bangluong);

        lv_bangluong = (ListView)findViewById(R.id.lv_bangluong);
        spinner = (Spinner)findViewById(R.id.spinner);

        Date date=new Date();
        Month=String.valueOf(date.getMonth()+1);


        String[] arr=new String[]{
                "01", "02", "03","04", "05", "06","07", "08", "09","10", "11", "12",
        };
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,arr);
        spinner.setAdapter(adapter);


        dbHelper = new DBHelper(this);
        employeeArrayList = dbHelper.getAllEmployee();
        BangluongAdapter bangluongAdapter = new BangluongAdapter(Activity_BangLuong.this, R.layout.item_bangluong, employeeArrayList,Month);
        lv_bangluong.setAdapter(bangluongAdapter);
        spinner.setOnItemSelectedListener(this);
        spinner.setSelection(date.getMonth());
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Month = parent.getItemAtPosition(position).toString();
        BangluongAdapter bangluongAdapter = new BangluongAdapter(Activity_BangLuong.this, R.layout.item_bangluong, employeeArrayList,Month);
        lv_bangluong.setAdapter(bangluongAdapter);
        Toast.makeText(parent.getContext(), "Tháng: " + Month, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}