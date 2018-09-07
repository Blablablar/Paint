package com.example.airal.paint.activity.setting;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.view.View;

import com.example.airal.paint.AudioService;
import com.example.airal.paint.R;
import com.example.airal.paint.System.SysConfig;
import com.example.airal.paint.activity.BaseActivity;
import com.example.airal.paint.tool.LifecycleHandler;

/**
 * Created by airal on 2018/9/7.
 */

public class ChooseMusicActivity extends BaseActivity {
    @Override
    protected int getContentLayout() {
        return R.layout.activity_setting_choose_music;
    }

    @Override
    public void initView() {
        findViewById(R.id.iv_music_1).setOnClickListener(this);
        findViewById(R.id.iv_music_2).setOnClickListener(this);
        findViewById(R.id.iv_music_3).setOnClickListener(this);
        findViewById(R.id.iv_music_4).setOnClickListener(this);
        findViewById(R.id.iv_music_5).setOnClickListener(this);
        findViewById(R.id.iv_music_stop).setOnClickListener(this);
    }

    @Override
    public void loadData() {

    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_music_1:
                changeMusic(0);
                break;
            case R.id.iv_music_2:
                changeMusic(1);
                break;
            case R.id.iv_music_3:
                changeMusic(2);
                break;
            case R.id.iv_music_4:
                changeMusic(3);
                break;
            case R.id.iv_music_5:
                changeMusic(4);
                break;
            case R.id.iv_music_stop:
                stopMusic();
                break;
            default:
                break;
        }
    }

    public void changeMusic(int position){
        System.out.println("changeMusic");
        if(LifecycleHandler.isApplicationVisible()){
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), AudioService.class);
            //启动Service，然后绑定该Service，这样我们可以在同时销毁该Activity，看看歌曲是否还在播放
            stopService(intent);
            AudioService.player.release();
            AudioService.player = MediaPlayer.create(getApplicationContext(), SysConfig.musicId[position]);
            //启动Service，然后绑定该Service，这样我们可以在同时销毁该Activity，看看歌曲是否还在播放
            startService(intent);
        }
        SharedPreferences sharedPreferences = getSharedPreferences("music", Context.MODE_PRIVATE); //私有数据
        SharedPreferences.Editor editor = sharedPreferences.edit();//获取编辑器
        editor.putInt("music_index", position);
        editor.commit();//提交修改
    }

    public void stopMusic(){
        System.out.println("stop");
        if(LifecycleHandler.isApplicationVisible()){
            Intent intent = new Intent();
            intent.setClass(getApplicationContext(), AudioService.class);
            //启动Service，然后绑定该Service，这样我们可以在同时销毁该Activity，看看歌曲是否还在播放
            stopService(intent);
            AudioService.player.stop();
        }
    }
}
