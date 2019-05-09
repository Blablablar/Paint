package com.example.airal.paint.activity.setting;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;

import com.example.airal.paint.AudioService;
import com.example.airal.paint.System.SysConfig;
import com.example.airal.paint.tool.LifecycleHandler;

import java.util.List;

public class CommonUtils {
    public static void changeBookMusic(Context context, int position){
        System.out.println("changeMusic");
        if(LifecycleHandler.isApplicationVisible()){
            Intent intent = new Intent();
            intent.setClass(context, AudioService.class);
            //启动Service，然后绑定该Service，这样我们可以在同时销毁该Activity，看看歌曲是否还在播放
            context.stopService(intent);
            AudioService.player.release();
            AudioService.player = MediaPlayer.create(context.getApplicationContext(), SysConfig.bookMUusicId[position]);
            //启动Service，然后绑定该Service，这样我们可以在同时销毁该Activity，看看歌曲是否还在播放
            context.startService(intent);
        }
    }

    public static void stopMusic(Context context){
        System.out.println("stop");
        if(LifecycleHandler.isApplicationVisible()){
            Intent intent = new Intent();
            intent.setClass(context, AudioService.class);
            //启动Service，然后绑定该Service，这样我们可以在同时销毁该Activity，看看歌曲是否还在播放
            context.stopService(intent);
            AudioService.player.stop();
        }
    }

    public static void restartAudio(Context context){
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
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
            intent.setClass(context, AudioService.class);
            context.startService(intent);
        }
    }
}
