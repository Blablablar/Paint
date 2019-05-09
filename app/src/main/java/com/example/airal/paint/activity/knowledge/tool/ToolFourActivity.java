package com.example.airal.paint.activity.knowledge.tool;

import android.graphics.Bitmap;

import com.example.airal.paint.R;
import com.example.airal.paint.System.SysConfig;
import com.example.airal.paint.activity.BaseActivity;
import com.example.airal.paint.adapter.ItemAdapter;
import com.example.airal.paint.tool.HorizontalListView;

import java.util.List;

/**
 * Created by airal on 2018/10/10.
 */

public class ToolFourActivity extends BaseActivity {
    private HorizontalListView listView;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_long_image_full;
    }

    @Override
    public void initView() {
        List<Bitmap> list = SysConfig.resizeBitmapList(SysConfig.drawableToBitmap(getResources().getDrawable(R.drawable.gsxl_goutu)));
        ItemAdapter itemAdapter = new ItemAdapter(getApplicationContext(), list);
        listView = (HorizontalListView) findViewById(R.id.listview);
        listView.setAdapter(itemAdapter);
        listView.scrollTo(listView.mMaxX);
    }

    @Override
    public void loadData() {

    }
}
