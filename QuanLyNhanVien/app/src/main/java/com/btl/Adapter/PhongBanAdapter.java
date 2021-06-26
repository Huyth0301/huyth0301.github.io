package com.btl.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.btl.model.PhongBan;
import com.btl.quanlynhanvien.R;

import java.util.ArrayList;

public class PhongBanAdapter extends ArrayAdapter<PhongBan> {
    Context context;
    ArrayList<PhongBan> arrayList = new ArrayList<>();
    int layoutResoure;
    public PhongBanAdapter(@NonNull Context context, int resource, @NonNull ArrayList<PhongBan> objects) {
        super(context, resource, objects);
        this.context=context;
        this.layoutResoure=resource;
        this.arrayList=objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater= LayoutInflater.from(context);
        convertView=inflater.inflate(layoutResoure,parent,false);

        TextView tv_idPB=(TextView)convertView.findViewById(R.id.tv_IDPB);
        tv_idPB.setText(String.valueOf(arrayList.get(position).getIdPB()));

        TextView tv_tenPB=(TextView)convertView.findViewById(R.id.tv_tenPB);
        tv_tenPB.setText(arrayList.get(position).getTenPB());

        TextView tv_diadiem=(TextView)convertView.findViewById(R.id.tv_DiaDiem);
        tv_diadiem.setText(arrayList.get(position).getDiaDiem());


        Animation animation= AnimationUtils.loadAnimation(context,R.anim.left_to_right);
        convertView.startAnimation(animation);
        return convertView;
    }
}
