package com.btl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.btl.db.DBHelper;
import com.btl.model.Account;
import com.btl.quanlynhanvien.R;

import java.util.ArrayList;

public class AccountAdapter extends ArrayAdapter<Account> {
    Context context;
    ArrayList<Account> arrayList;
    int layoutResoure;

    public AccountAdapter(@NonNull Context context, int resource, ArrayList<Account> AcCount) {
        super(context, resource, AcCount);
        this.context=context;
        this.layoutResoure=resource;
        this.arrayList=AcCount;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        convertView=inflater.inflate(layoutResoure,parent,false);
         ImageView imageView=(ImageView)convertView.findViewById(R.id.img_account);
         imageView.setImageResource(R.drawable.ic_baseline_account_circle_24);
         TextView tv_employee=(TextView)convertView.findViewById(R.id.tv_tenNV);
        DBHelper dbHelper=new DBHelper(context);
        String TenNV=dbHelper.getEmployee(arrayList.get(position).getIdnv()).getName();
         tv_employee.setText(TenNV);
         TextView tv_user=(TextView)convertView.findViewById(R.id.tv_user);
         tv_user.setText(arrayList.get(position).getUser());
        TextView tv_pass=(TextView)convertView.findViewById(R.id.tv_pass);
        tv_pass.setText(arrayList.get(position).getPassword());

        Animation animation= AnimationUtils.loadAnimation(context,R.anim.left_to_right);
        convertView.startAnimation(animation);
        return convertView;
    }
}
