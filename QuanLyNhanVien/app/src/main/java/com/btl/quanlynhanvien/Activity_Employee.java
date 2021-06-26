package com.btl.quanlynhanvien;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.btl.Adapter.EmployeeAdapter;
import com.btl.db.DBHelper;
import com.btl.model.Employee;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Activity_Employee extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {


    ListView listView;
    ViewPager viewPager;
    TabLayout tabLayout;
    ArrayList<Employee> arrayList=new ArrayList<>();
    TextView textView;
    EditText editText_search;
    DBHelper dbHelper;
    EmployeeAdapter adapter;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add){
            Intent intent=new Intent(Activity_Employee.this, Infor_Employee.class);
            startActivity(intent);

        }
        else if(item.getItemId()==R.id.account){
            Intent intent=new Intent(Activity_Employee.this, Activity_Account.class);
            startActivity(intent);

        }
        else if(item.getItemId()==R.id.bacluong){
            Intent intent=new Intent(Activity_Employee.this, Activity_BacLuong.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.phongban){
            Intent intent=new Intent(Activity_Employee.this, Activity_PhongBan.class);
            startActivity(intent);
        }
        else if(item.getItemId()==R.id.logout){
            Intent intent=new Intent(Activity_Employee.this, Activity_Login.class);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId()==R.id.bangluong){
            Intent intent=new Intent(Activity_Employee.this, Activity_BangLuong.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle("Manager Employee");
        }

        listView=(ListView)findViewById(R.id.lv_employee);
        textView=(TextView) findViewById(R.id.tv_employee);
        editText_search=(EditText)findViewById(R.id.edt_search);
        dbHelper=new DBHelper(this);
        arrayList=dbHelper.getAllEmployee();
        adapter=new EmployeeAdapter(Activity_Employee.this,R.layout.item,arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);
        editText_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                arrayList=dbHelper.getAllEmployee(editText_search.getText().toString());

                adapter=new EmployeeAdapter(Activity_Employee.this,R.layout.item,arrayList);
                listView.setAdapter(adapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        Intent intent=new Intent(Activity_Employee.this, Infor_Employee.class);
        Employee employee=new Employee(arrayList.get(position).getId(),arrayList.get(position).getName(),
                arrayList.get(position).getGioiTinh(),arrayList.get(position).getAddr()
                ,arrayList.get(position).getSdt(),arrayList.get(position).getChucVu(), arrayList.get(position).getIDPB(), arrayList.get(position).getId_bacluong());

        Bundle bundle=new Bundle();
        bundle.putSerializable("Employee",employee);
        intent.putExtra("Data",bundle);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

    }

}