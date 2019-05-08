package com.example.airal.paint.activity.create;

import android.app.ActivityOptions;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.example.airal.paint.R;
import com.example.airal.paint.System.SysConfig;
import com.example.airal.paint.activity.BaseActivity;
import com.example.airal.paint.adapter.ItemAdapter;
import com.example.airal.paint.tool.HorizontalListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by airal on 2018/5/3.
 */

public class ChooseBgActivity extends BaseActivity implements View.OnClickListener{
    private HorizontalListView listView;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_choose_bg;
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initView() {
        List<Bitmap> list = new ArrayList<>();
        list.add(BitmapFactory.decodeResource(getResources(),R.drawable.bg1));
        list.add(BitmapFactory.decodeResource(getResources(),R.drawable.bg2));
        list.add(BitmapFactory.decodeResource(getResources(),R.drawable.bg3));
        list.add(BitmapFactory.decodeResource(getResources(),R.drawable.bg4));
        list.add(BitmapFactory.decodeResource(getResources(),R.drawable.bg5));

        ItemAdapter itemAdapter = new ItemAdapter(getApplicationContext(), list);
        listView = (HorizontalListView) findViewById(R.id.listview);
        listView.setAdapter(itemAdapter);
        listView.scrollTo(listView.mMaxX);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position!=4){
                    Intent intent = new Intent(getApplicationContext(), CreatePaintActivity.class);
                    intent.putExtra("position", position);
                    ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ChooseBgActivity.this, view, "bg");
                    startActivity(intent, options.toBundle());
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
}
