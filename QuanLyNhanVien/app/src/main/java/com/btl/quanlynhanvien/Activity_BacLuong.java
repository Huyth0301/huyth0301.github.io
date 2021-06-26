package com.btl.quanlynhanvien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.btl.Adapter.BacLuongAdapter;
import com.btl.db.DBHelper;
import com.btl.model.Luong;

import java.util.ArrayList;

public class Activity_BacLuong extends AppCompatActivity implements AdapterView.OnItemClickListener {

    TextView tv_HSL,tv_LCB,tv_BL,tv_HSPC;
    ListView listView;
    ArrayList<Luong> luongs=new ArrayList<>();
    boolean isadd=false;
    DBHelper dbHelper;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add){
            Intent intent=new Intent(Activity_BacLuong.this, Infor_BacLuong.class);
            startActivity(intent);

        }

        else if(item.getItemId()==R.id.employee){
            Intent intent=new Intent(Activity_BacLuong.this, Activity_Employee.class);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId()==R.id.account){
            Intent intent=new Intent(Activity_BacLuong.this, Activity_Account.class);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId()==R.id.phongban){
            Intent intent=new Intent(Activity_BacLuong.this, Activity_PhongBan.class);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId()==R.id.logout){
            Intent intent=new Intent(Activity_BacLuong.this, Activity_Login.class);
            startActivity(intent);
            finish();

        }
        else if(item.getItemId()==R.id.bangluong){
            Intent intent=new Intent(Activity_BacLuong.this, Activity_BangLuong.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bac_luong);
        listView=(ListView)findViewById(R.id.lv_BacLuong);
        dbHelper=new DBHelper(this);

        luongs=dbHelper.getAllBacLuong();
        BacLuongAdapter bacLuongAdapter=new BacLuongAdapter(Activity_BacLuong.this,R.layout.item_bacluong,luongs);
        listView.setAdapter(bacLuongAdapter);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle=new Bundle();
        Luong luong=new Luong(luongs.get(position).getIdBacLuong(),luongs.get(position).getLuongCoBan(),
                luongs.get(position).getHeSoLuong(),luongs.get(position).getHeSoPhuCap());
        bundle.putSerializable("luong",luong);
        Intent intent=new Intent(Activity_BacLuong.this, Infor_BacLuong.class);
        intent.putExtra("data",bundle);
        startActivity(intent);

    }
}