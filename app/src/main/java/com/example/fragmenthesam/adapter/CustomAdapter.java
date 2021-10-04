package com.example.fragmenthesam.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.fragmenthesam.R;
import com.example.fragmenthesam.entity.Picture;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Picture> arrayList;
    private int layoutId;

    public CustomAdapter(Context context, ArrayList<Picture> arrayList , int layoutId) {
        this.context = context;
        this.arrayList = arrayList;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        if (view==null){
            view = LayoutInflater.from(context).inflate(layoutId,null);
        }
        Picture head = arrayList.get(position);
        ImageView PicDetails = view.findViewById(R.id.PicDetails);
        PicDetails.setImageResource(head.getPicID());
        return view;
    }
}
