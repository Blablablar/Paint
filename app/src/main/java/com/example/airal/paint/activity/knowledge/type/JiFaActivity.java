package com.example.airal.paint.activity.knowledge.type;

import android.graphics.Bitmap;

import com.example.airal.paint.R;
import com.example.airal.paint.System.SysConfig;
import com.example.airal.paint.activity.BaseActivity;
import com.example.airal.paint.adapter.ItemAdapter;
import com.example.airal.paint.tool.HorizontalListView;

import java.util.List;

/**
 * Created by airal on 2018/9/7.
 */

public class JiFaActivity extends BaseActivity {
    private HorizontalListView listView;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_long_image;
    }

    @Override
    public void initView() {
        List<Bitmap> list = SysConfig.resizeBitmapList(SysConfig.drawableToBitmap(getResources().getDrawable(R.mipmap.long_jifa)));
        ItemAdapter itemAdapter = new ItemAdapter(getApplicationContext(), list);
        listView = (HorizontalListView) findViewById(R.id.listview);
        listView.setAdapter(itemAdapter);
    }

    @Override
    public void loadData() {

    }
}

