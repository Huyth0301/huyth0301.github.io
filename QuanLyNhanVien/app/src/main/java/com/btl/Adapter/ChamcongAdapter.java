package com.btl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.btl.model.ChamCong;
import com.btl.quanlynhanvien.R;

import java.util.ArrayList;

public class ChamcongAdapter extends ArrayAdapter<ChamCong> {
    Context context;
    ArrayList<ChamCong> arrayList;
    int layoutResoure;
    int stt = 0;

    public ChamcongAdapter(@NonNull Context context, int resource, ArrayList<ChamCong> objects) {
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
        stt += 1;

        TextView txt_stt = (TextView)convertView.findViewById(R.id.txt_Stt);
        TextView txt_date = (TextView)convertView.findViewById(R.id.txt_date);

        txt_stt.setText(String.valueOf(stt));
        txt_date.setText(String.valueOf((arrayList.get(position).getDate())));

        return convertView;
    }

}
