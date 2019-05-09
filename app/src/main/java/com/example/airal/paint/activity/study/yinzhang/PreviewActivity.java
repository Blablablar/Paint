package com.example.airal.paint.activity.study.yinzhang;

import android.graphics.Bitmap;

import com.example.airal.paint.R;
import com.example.airal.paint.System.SysConfig;
import com.example.airal.paint.activity.BaseActivity;
import com.example.airal.paint.adapter.ItemAdapter;
import com.example.airal.paint.tool.HorizontalListView;

import java.util.List;

public class PreviewActivity extends BaseActivity {
    private HorizontalListView listView;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_long_image_full;
    }

    @Override
    public void initView() {
        int id = getIntent().getIntExtra("drawable", -1);
        if(id!=-1){
            List<Bitmap> list = SysConfig.resizeBitmapList(SysConfig.drawableToBitmap(getResources().getDrawable(id)));
            ItemAdapter itemAdapter = new ItemAdapter(getApplicationContext(), list);
            listView = (HorizontalListView) findViewById(R.id.listview);
            listView.setAdapter(itemAdapter);
            listView.scrollTo(listView.mMaxX);
        }
    }

    @Override
    public void loadData() {

    }
}


