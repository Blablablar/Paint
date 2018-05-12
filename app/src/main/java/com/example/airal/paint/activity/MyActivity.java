package com.example.airal.paint.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;

import com.example.airal.paint.AudioService;
import com.example.airal.paint.R;
import com.example.airal.paint.tool.LifecycleHandler;

import java.util.List;

/**
 * Created by airal on 2018/4/24.
 */

public abstract class MyActivity extends AppCompatActivity implements View.OnClickListener{

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

    public abstract void initView();

    public abstract void loadData();

    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("Pause");
    }

    protected abstract int getContentLayout();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        System.out.println("onCreate");
        int layoutId = this.getContentLayout();
        setContentView(layoutId);
        initView();
        loadData();
        if(findViewById(R.id.iv_back)!=null){
            findViewById(R.id.iv_back).setOnClickListener(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("Stop");
        if(!LifecycleHandler.isApplicationVisible()){
            Intent intent = new Intent();
            intent.setClass(this, AudioService.class);
            //启动Service，然后绑定该Service，这样我们可以在同时销毁该Activity，看看歌曲是否还在播放
            stopService(intent);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> services = am.getRunningServices(100);
        boolean find=false;
        for (int i=0;i<services.size();i++) {
            // 得到所有正在运行的服务的名称
            String name = services.get(i).service.getClassName();
            if (AudioService.class.equals(name)) {
                find=true;
            }
        }
        if(!find){
            Intent intent = new Intent();
            intent.setClass(this, AudioService.class);
            startService(intent);
        }
    }
}
