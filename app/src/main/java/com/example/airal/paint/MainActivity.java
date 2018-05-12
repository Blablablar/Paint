package com.example.airal.paint;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.airal.paint.System.SysConfig;
import com.example.airal.paint.activity.HomePageActivity;
import com.example.airal.paint.activity.MyActivity;
import com.example.airal.paint.tool.LifecycleHandler;

public class MainActivity extends MyActivity {
    ImageView ivHomeBg;
    TextView tvToast;

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
        //设置成全屏模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ivHomeBg=findViewById(R.id.iv_home_bg);
        tvToast=findViewById(R.id.tv_toast);
        tvToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomePageActivity.class));
            }
        });
        ivHomeBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),HomePageActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
        startMusic();
        startAnim();
    }

    int animTime=3000;
    public void startAnim(){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.homebg_come_in);
        animation.setDuration(animTime);
        ivHomeBg.startAnimation(animation);
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

}
