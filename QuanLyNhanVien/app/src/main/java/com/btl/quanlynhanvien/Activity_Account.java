package com.btl.quanlynhanvien;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.btl.Adapter.AccountAdapter;
import com.btl.db.DBHelper;
import com.btl.model.Account;

import java.util.ArrayList;

public class Activity_Account extends AppCompatActivity implements AdapterView.OnItemClickListener {
    ImageView imageView;
    TextView tv_user,tv_pass;
    ListView listView;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add){
            Intent intent=new Intent(Activity_Account.this, Infor_Account.class);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId()==R.id.employee){
            Intent intent=new Intent(Activity_Account.this, Activity_Employee.class);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId()==R.id.phongban){
            Intent intent=new Intent(Activity_Account.this, Activity_PhongBan.class);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId()==R.id.bacluong){
            Intent intent=new Intent(Activity_Account.this, Activity_BacLuong.class);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId()==R.id.logout){
            Intent intent=new Intent(Activity_Account.this, Activity_Login.class);
            startActivity(intent);
            finish();

        }
        else if(item.getItemId()==R.id.bangluong){
            Intent intent=new Intent(Activity_Account.this, Activity_BangLuong.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    AccountAdapter arrayAdapter;
    ArrayList<Account> accounts=new ArrayList<Account>();
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        listView=(ListView)findViewById(R.id.lv_acCount);
        dbHelper=new DBHelper(this);
        accounts=dbHelper.getAllAcCount();
        arrayAdapter=new AccountAdapter(Activity_Account.this,R.layout.item_account,accounts);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle=new Bundle();
        Account acCount=new Account(accounts.get(position).getIdnv(),accounts.get(position).getUser(),accounts.get(position).getPassword());
        bundle.putSerializable("account",acCount);
        Intent intent=new Intent(Activity_Account.this, Infor_Account.class);
        intent.putExtra("data",bundle);
        startActivity(intent);
    }
}