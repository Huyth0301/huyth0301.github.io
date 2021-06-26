package com.btl.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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

import com.btl.model.Employee;
import com.btl.quanlynhanvien.R;

import java.util.ArrayList;

public class EmployeeAdapter extends ArrayAdapter<Employee> {
    Context context;
    ArrayList<Employee> arrayList;
    int layoutResoure;
    public EmployeeAdapter(@NonNull Context context, int resource,ArrayList<Employee> employees) {
        super(context, resource,employees);
        this.context=context;
        this.layoutResoure=resource;
        this.arrayList=employees;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        convertView=inflater.inflate(layoutResoure,parent,false);
        TextView tv_name=(TextView)convertView.findViewById(R.id.tv_name);
        tv_name.setText(arrayList.get(position).getName());


        ImageView img=(ImageView)convertView.findViewById(R.id.img_DaiDien);

        if(arrayList.get(position).getGioiTinh().equals("Nam")){
            img.setImageResource(R.drawable.profile);
        }
        else{
            img.setImageResource(R.drawable.girl);
        }

        TextView tv_chucvu=(TextView)convertView.findViewById(R.id.tv_chucVu);
        tv_chucvu.setText(arrayList.get(position).getChucVu());


        Animation animation= AnimationUtils.loadAnimation(context,R.anim.left_to_right);
        convertView.startAnimation(animation);
        return convertView;
    }
}
