package com.example.airal.paint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.airal.paint.System.SysConfig;
import com.example.airal.paint.activity.HomePageActivity;
import com.example.airal.paint.activity.BaseActivity;
import com.example.airal.paint.adapter.ItemAdapter;
import com.example.airal.paint.tool.HorizontalListView;

import java.util.ArrayList;
import java.util.List;

public class LaunchActivity extends BaseActivity {
    private TextView tvToast;
    private HorizontalListView listView;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void loadData() {
        stopService(getIntent());
        startService(getIntent());
    }

    public void startMusic(){
        SharedPreferences share=getSharedPreferences("music", MODE_PRIVATE);
        int position=share.getInt("music_index",0);

        AudioService.player = MediaPlayer.create(this, SysConfig.musicId[position]);
        Intent intent = new Intent();
        intent.setClass(this, AudioService.class);
    }

    @Override
    public void initView(){
        List<Bitmap>list = SysConfig.resizeBitmapList(SysConfig.drawableToBitmap(getResources().getDrawable(R.mipmap.homebg3)));
        ItemAdapter itemAdapter = new ItemAdapter(LaunchActivity.this, list);
        listView = (HorizontalListView) findViewById(R.id.listview);
        listView.setAdapter(itemAdapter);
        listView.scrollTo(listView.mMaxX);
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                        // 触摸移动时的操作
                        tvToast.setVisibility(View.GONE);
                        break;
                    case MotionEvent.ACTION_UP:
                        // 触摸抬起时的操作
                        handlerPostDelayed();
                        break;
                }
                return false;
            }
        });
        //设置成全屏模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //ivHomeBg=findViewById(R.id.iv_home_bg);
        tvToast=findViewById(R.id.tv_toast);
        tvToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
                finish();
            }
        });
        startMusic();
        startAnim();
    }

    int animTime = 3000;
    public void startAnim(){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.homebg_come_in);
        animation.setDuration(animTime);
        listView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tvToast.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }



    private static Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            tvToast.setVisibility(View.VISIBLE);
        }

    };
    // handler+postDelayed 方式，反复发送延时消息
    private void handlerPostDelayed() {
        mHandler.postDelayed(mRunnable, 1500);
    }
}
