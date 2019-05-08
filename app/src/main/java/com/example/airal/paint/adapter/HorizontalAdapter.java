package com.example.airal.paint.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.airal.paint.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by airal on 2018/4/24.
 */

public class HorizontalAdapter extends BaseAdapter implements View.OnClickListener{
    private LayoutInflater mInflater;
    private List<Integer> list = new ArrayList();

    public HorizontalAdapter(Context context, List<Integer> imgIds){
        list.clear();
        list.addAll(imgIds);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.item_horizontal, null);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.imageView.setImageResource((int)list.get(position));
        //holder.imageView.setOnClickListener(this);
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    private static class ViewHolder {
        public ImageView imageView;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.image:
                //myListener.onItemClick((ImageView) v);
                break;
            default:
                break;
        }
    }

//    public void setItemListener(onItemClickListener myListener) {
//        this.myListener = myListener;
//    }
//
//    public onItemClickListener myListener;
//
//    public interface onItemClickListener{
//        void onItemClick(ImageView v);
//    }
}

