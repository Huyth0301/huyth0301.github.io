package com.btl.quanlynhanvien;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.btl.db.DBHelper;
import com.btl.model.PhongBan;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class Infor_PhongBan extends AppCompatActivity {

    TextInputEditText ipedt_tenPB,ipedt_diaChi;
    TextInputLayout textInputLayout_tenPB,textInputLayout_diaChi;
    DBHelper dbHelper=new DBHelper(this);
    boolean isAdd=true;
    ArrayList<PhongBan> phongBans=new ArrayList<PhongBan>();
    int idpb;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(isAdd){
            MenuInflater menuInflater=getMenuInflater();
            menuInflater.inflate(R.menu.my_menu_add_phongban,menu);

        }
        else{
            MenuInflater menuInflater=getMenuInflater();
            menuInflater.inflate(R.menu.my_menu_phongban,menu);
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add_PhongBan){
            PhongBan pb=new PhongBan(1,ipedt_tenPB.getText().toString()
                    ,ipedt_diaChi.getText().toString());
            dbHelper.addPhongBan(pb);
            Intent intent = new Intent(Infor_PhongBan.this, Activity_PhongBan.class);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId()==R.id.delete_phongban){
            if(dbHelper.deletePhongBan(idpb)==1){
                Intent intent = new Intent(Infor_PhongBan.this, Activity_PhongBan.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(Infor_PhongBan.this,"Không thể xóa phòng ban",Toast.LENGTH_SHORT).show();
            }


        }
        else if(item.getItemId()==R.id.update_phongban){
            PhongBan pb=new PhongBan(idpb,ipedt_tenPB.getText().toString()
                    ,ipedt_diaChi.getText().toString());
            dbHelper.updatePhongBan(pb);
            Intent intent = new Intent(Infor_PhongBan.this, Activity_PhongBan.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_phong_ban);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle("Phòng Ban");
        }
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("data");
        ipedt_tenPB=(TextInputEditText)findViewById(R.id.ipedt_tenPB);
        ipedt_diaChi=(TextInputEditText)findViewById(R.id.ipedt_diachi);
        textInputLayout_tenPB=(TextInputLayout)findViewById(R.id.textInputLayout_tenPB);
        textInputLayout_diaChi=(TextInputLayout)findViewById(R.id.textInputLayout_diaChiPB);
        if(bundle!=null){
            isAdd=false;
            PhongBan phongBan=(PhongBan) bundle.getSerializable("phongban");
            idpb=phongBan.getIdPB();
            ipedt_tenPB.setText(phongBan.getTenPB());
            ipedt_diaChi.setText(phongBan.getDiaDiem());
        }
    }
}