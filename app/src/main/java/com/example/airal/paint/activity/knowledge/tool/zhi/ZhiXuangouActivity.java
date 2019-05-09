package com.example.airal.paint.activity.knowledge.tool.zhi;

import android.graphics.Bitmap;

import com.example.airal.paint.R;
import com.example.airal.paint.System.SysConfig;
import com.example.airal.paint.activity.BaseActivity;
import com.example.airal.paint.adapter.ItemAdapter;
import com.example.airal.paint.tool.HorizontalListView;

import java.util.List;

public class ZhiXuangouActivity extends BaseActivity {
    private HorizontalListView listView;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_long_image_full;
    }

    @Override
    public void initView() {
        List<Bitmap> list = SysConfig.resizeBitmapList(SysConfig.drawableToBitmap(getResources().getDrawable(R.drawable.zhi_xuangou_detail)));
        ItemAdapter itemAdapter = new ItemAdapter(getApplicationContext(), list);
        listView = (HorizontalListView) findViewById(R.id.listview);
        listView.setAdapter(itemAdapter);
        listView.scrollTo(listView.mMaxX);
    }

    @Override
    public void loadData() {

    }
}


