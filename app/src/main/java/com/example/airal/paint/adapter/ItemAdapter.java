package com.example.airal.paint.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.airal.paint.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by airal on 2018/9/7.
 */

public class ItemAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<Bitmap> list = new ArrayList<>();

    public ItemAdapter(Context context, List<Bitmap> list) {
        this.list.addAll(list);
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public Bitmap getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Bitmap bitmap = list.get(position);
        if(convertView==null){
            convertView = mInflater.inflate(R.layout.image_item, parent, false);
        }
        ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_image);
        imageView.setImageBitmap(bitmap);
        return convertView;
    }
}