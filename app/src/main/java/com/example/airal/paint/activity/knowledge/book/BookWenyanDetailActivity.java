package com.example.airal.paint.activity.knowledge.book;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.example.airal.paint.R;
import com.example.airal.paint.System.SysConfig;
import com.example.airal.paint.activity.BaseActivity;
import com.example.airal.paint.activity.setting.CommonUtils;
import com.example.airal.paint.adapter.ItemAdapter;
import com.example.airal.paint.tool.HorizontalListView;

import java.util.List;

public class BookWenyanDetailActivity extends BaseActivity {
    private HorizontalListView listView;
    private boolean isPlaying = false;
    @Override
    protected int getContentLayout() {
        return R.layout.activity_long_image_full;
    }

    @Override
    public void initView() {
        List<Bitmap> list = SysConfig.resizeBitmapList(SysConfig.drawableToBitmap(getResources().getDrawable(R.mipmap.book_wenyan)));
        ItemAdapter itemAdapter = new ItemAdapter(getApplicationContext(), list);
        listView = (HorizontalListView) findViewById(R.id.listview);
        listView.setAdapter(itemAdapter);
        listView.scrollTo(listView.mMaxX);
        final ImageView ivPlay = findViewById(R.id.iv_play);
        ivPlay.setVisibility(View.VISIBLE);
        ivPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ivPlay.isSelected()){
                    CommonUtils.stopMusic(getBaseContext());
                    ivPlay.setSelected(false);
                    isPlaying = false;
                }else{
                    CommonUtils.changeBookMusic(getBaseContext(), 1);
                    ivPlay.setSelected(true);
                    isPlaying = true;
                }
            }
        });
    }

    @Override
    public void loadData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(isPlaying){
            CommonUtils.restartAudio(getBaseContext());
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        CommonUtils.stopMusic(getBaseContext());
    }
}

