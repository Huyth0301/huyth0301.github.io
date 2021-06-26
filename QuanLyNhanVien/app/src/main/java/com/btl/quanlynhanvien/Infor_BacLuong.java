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
import com.btl.model.Luong;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Infor_BacLuong extends AppCompatActivity {

    TextInputEditText ipedt_HSL,ipedt_LCB,ipedt_HSPC;
    TextInputLayout textInputLayout_LCB,textInputLayout_HSL,textInputLayout_HSPC;
    DBHelper dbHelper=new DBHelper(this);
    int idbl;
    boolean isAdd=true;



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(isAdd){
            MenuInflater menuInflater=getMenuInflater();
            menuInflater.inflate(R.menu.my_menu_add_bacluong,menu);

        }
        else{
            MenuInflater menuInflater=getMenuInflater();
            menuInflater.inflate(R.menu.my_menu_bacluong,menu);
        }
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.add_BacLuong){
            Intent intent = new Intent(Infor_BacLuong.this, Activity_BacLuong.class);
            float hsl = Float.parseFloat(ipedt_HSL.getText().toString());
            float lcb = Float.parseFloat(ipedt_LCB.getText().toString());
            float hspc = Float.parseFloat(ipedt_HSPC.getText().toString());
            Luong luong = new Luong(1,lcb,hsl,hspc);
            dbHelper.addBacLuong(luong);
            startActivity(intent);
            finish();
        }
        else if(item.getItemId()==R.id.delete_luong){
            if( dbHelper.deleteBacLuong(idbl)==1){
                Intent intent = new Intent(Infor_BacLuong.this, Activity_BacLuong.class);
                startActivity(intent);
                finish();
            }
            else {
                Toast.makeText(Infor_BacLuong.this,"Không thể xóa bậc lương",Toast.LENGTH_SHORT).show();
            }

        }
        else if(item.getItemId()==R.id.update_luong){

            float hsl = Float.parseFloat(ipedt_HSL.getText().toString());
            float lcb = Float.parseFloat(ipedt_LCB.getText().toString());
            float hspc = Float.parseFloat(ipedt_HSPC.getText().toString());
            Luong luong = new Luong(idbl,lcb,hsl,hspc);
            Intent intent = new Intent(Infor_BacLuong.this, Activity_BacLuong.class);
            dbHelper.updateBacLuong(luong);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infor__bac_luong);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.setTitle("Lương");
        }
        ipedt_HSL = findViewById(R.id.ipedt_HSL);
        ipedt_LCB = findViewById(R.id.ipedt_LCB);
        ipedt_HSPC = findViewById(R.id.ipedt_HSPC);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("data");
        if(bundle!=null){
            isAdd=false;
            Luong luong= (Luong) bundle.getSerializable("luong");
            idbl=luong.getIdBacLuong();
            ipedt_LCB.setText(String.valueOf(luong.getLuongCoBan()));
            ipedt_HSL.setText(String.valueOf(luong.getHeSoLuong()));
            ipedt_HSPC.setText(String.valueOf(luong.getHeSoPhuCap()));
        }


    }
}