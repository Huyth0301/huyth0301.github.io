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

import com.btl.Adapter.PhongBanAdapter;
import com.btl.db.DBHelper;
import com.btl.model.PhongBan;

import java.util.ArrayList;

public class Activity_PhongBan extends AppCompatActivity implements AdapterView.OnItemClickListener{

    TextView tv_namePB,tv_diaChiPB;
    ListView listView;
    PhongBanAdapter phongBanAdapter;
    ArrayList<PhongBan> phongBans=new ArrayList<PhongBan>();
    DBHelper dbHelper;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add){
            Intent intent=new Intent(Activity_PhongBan.this, Infor_PhongBan.class);
            startActivity(intent);

        }
        else if(item.getItemId()==R.id.employee){
            Intent intent=new Intent(Activity_PhongBan.this, Activity_Employee.class);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId()==R.id.account){
            Intent intent=new Intent(Activity_PhongBan.this, Activity_Account.class);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId()==R.id.bacluong){
            Intent intent=new Intent(Activity_PhongBan.this, Activity_BacLuong.class);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId()==R.id.logout){
            Intent intent=new Intent(Activity_PhongBan.this, Activity_Login.class);
            startActivity(intent);
            finish();

        }
        else if(item.getItemId()==R.id.bangluong){
            Intent intent=new Intent(Activity_PhongBan.this, Activity_BangLuong.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phong_ban_);
        listView=(ListView)findViewById(R.id.lv_PhongBan);
        dbHelper=new DBHelper(this);
        phongBans = dbHelper.getAllPhongBan();
        phongBanAdapter=new PhongBanAdapter(Activity_PhongBan.this,R.layout.item_phongban,phongBans);
        listView.setAdapter(phongBanAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Bundle bundle=new Bundle();
        PhongBan phongBan=new PhongBan(phongBans.get(position).getIdPB(),
                phongBans.get(position).getTenPB(),
                phongBans.get(position).getDiaDiem());
        bundle.putSerializable("phongban",phongBan);
        Intent intent=new Intent(Activity_PhongBan.this, Infor_PhongBan.class);
        intent.putExtra("data",bundle);
        startActivity(intent);
    }
}