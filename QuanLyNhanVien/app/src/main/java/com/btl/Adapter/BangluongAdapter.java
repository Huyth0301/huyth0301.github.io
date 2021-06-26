package com.btl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.btl.db.DBHelper;
import com.btl.model.Employee;
import com.btl.model.Luong;
import com.btl.quanlynhanvien.R;

import java.util.ArrayList;


public class BangluongAdapter extends ArrayAdapter<Employee>{

    Context context;
    ArrayList<Employee> arrayList;
    String month;

    int layoutResoure;
    int stt = 0;
    public BangluongAdapter(@NonNull Context context, int resource,ArrayList<Employee> employees,String month) {
        super(context, resource,employees);
        this.context=context;
        this.layoutResoure=resource;
        this.arrayList=employees;
        this.month=month;


    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater=LayoutInflater.from(context);
        convertView=inflater.inflate(layoutResoure,parent,false);

        TextView txt_id = (TextView)convertView.findViewById(R.id.txt_id_ep);
        TextView txt_name = (TextView)convertView.findViewById(R.id.txt_name_ep);
        TextView txt_bacluong = (TextView)convertView.findViewById(R.id.txt_bacluong_ep);
        TextView txt_tongcong = (TextView)convertView.findViewById(R.id.txt_ngaycong_ep);
        TextView txt_tongluong = (TextView)convertView.findViewById(R.id.txt_tongluongep);

        stt +=1 ;
        txt_id.setText(String.valueOf(stt));
        txt_name.setText(arrayList.get(position).getName());
        txt_bacluong.setText(String.valueOf(arrayList.get(position).getId_bacluong()));
        DBHelper dbHelper=new DBHelper(context);
        int snc=dbHelper.getallsongaycong(arrayList.get(position).getId(),month);
        txt_tongcong.setText(String.valueOf(snc));
        Luong luong=dbHelper.getLuong(arrayList.get(position).getId_bacluong());
        float lcb=luong.getLuongCoBan();
        float hsl=luong.getHeSoLuong();
        float hspc=luong.getHeSoPhuCap();
        float tongcong= (float) (lcb*hsl*(snc*1.0/26)+hspc);
        txt_tongluong.setText(String.valueOf(tongcong));
        return convertView;
    }
}
