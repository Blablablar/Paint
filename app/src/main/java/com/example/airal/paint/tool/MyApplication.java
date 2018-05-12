package com.example.airal.paint.tool;

import android.app.Application;
import android.media.MediaPlayer;

import com.example.airal.paint.R;

import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by airal on 2018/4/24.
 */

public class MyApplication extends Application{
    public static boolean isDrag=false;
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new LifecycleHandler());
    }

}
