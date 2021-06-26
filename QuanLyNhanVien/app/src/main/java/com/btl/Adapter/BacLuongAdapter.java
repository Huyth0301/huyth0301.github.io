package com.btl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.btl.model.Luong;
import com.btl.quanlynhanvien.R;

import java.util.ArrayList;
import java.util.List;

public class BacLuongAdapter extends ArrayAdapter<Luong> {

    Context context;
    ArrayList<Luong> arrayList;
    int layoutResoure;



    public BacLuongAdapter(@NonNull Context context, int resource,ArrayList<Luong> objects) {
        super(context, resource, objects);
        this.context=context;
        this.layoutResoure=resource;
        this.arrayList=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater=LayoutInflater.from(context);
        convertView=layoutInflater.inflate(layoutResoure,parent,false);
        TextView tv_bacluong=(TextView)convertView.findViewById(R.id.tv_BacLuong);
        TextView tv_LCB=(TextView)convertView.findViewById(R.id.tv_LuongCoBan);
        TextView tv_HSL=(TextView)convertView.findViewById(R.id.tv_HeSoLuong);
        TextView tv_HSPC=(TextView)convertView.findViewById(R.id.tv_HeSoPhuCap);
        tv_bacluong.setText(String.valueOf(arrayList.get(position).getIdBacLuong()));
        tv_LCB.setText(String.valueOf(arrayList.get(position).getLuongCoBan()));
        tv_HSL.setText(String.valueOf((arrayList.get(position).getHeSoLuong())));
        tv_HSPC.setText(String.valueOf((arrayList.get(position).getHeSoPhuCap())));
        return convertView;
    }
}
