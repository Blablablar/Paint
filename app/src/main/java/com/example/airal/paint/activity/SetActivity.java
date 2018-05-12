package com.example.airal.paint.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.airal.paint.AudioService;
import com.example.airal.paint.R;
import com.example.airal.paint.System.SysConfig;
import com.example.airal.paint.tool.LifecycleHandler;

/**
 * Created by airal on 2018/4/25.
 */

public class SetActivity extends MyActivity{
    Spinner spinner;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initView(){
        spinner=findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(
                this, R.array.music, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        adapter.notifyDataSetChanged();
//        spinner.setSelection(0);
        SharedPreferences share=getSharedPreferences("music", Activity.MODE_PRIVATE);
        currentPosition=share.getInt("music_index",0);
        spinner.setSelection(currentPosition);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                System.out.println(spinner.getSelectedItemPosition());
                if(currentPosition!=position){
                    changeMusic(position);
                }
                spinner.setSelection(position);
                currentPosition=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    int currentPosition=-1;
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
}
