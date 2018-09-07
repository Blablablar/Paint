package com.example.airal.paint;
import android.app.Application;
import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;

import com.example.airal.paint.tool.LifecycleHandler;

/**
 * Created by airal on 2018/4/24.
 */

public class AudioService extends Service implements MediaPlayer.OnCompletionListener{
    private final IBinder binder = new AudioBinder();
    public static MediaPlayer player;
    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return binder;
    }
    /**
     * 当Audio播放完的时候触发该动作
     */
    @Override
    public void onCompletion(MediaPlayer player) {
        // TODO Auto-generated method stub
        //stopSelf();//结束了，则结束Service
    }

    //在这里我们需要实例化MediaPlayer对象
    @Override
    public void onCreate(){
        super.onCreate();
        //我们从raw文件夹中获取一个应用自带的mp3文件
        //LifecycleHandler.player.setOnCompletionListener(this);
    }

    /**
     * 该方法在SDK2.0才开始有的，替代原来的onStart方法
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        if(player!=null&&!player.isPlaying()){
            player.start();
            player.setLooping(true);
        }
        return START_STICKY;
    }
    @Override
    public void onDestroy(){
        //super.onDestroy();
        if(player!=null&&player.isPlaying()){
            player.pause();
        }
        //player.release();
    }

    //为了和Activity交互，我们需要定义一个Binder对象
    class AudioBinder extends Binder{

        //返回Service对象
        AudioService getService(){
            return AudioService.this;
        }
    }

    //后退播放进度
    public void haveFun(){
        if(player!=null && player.isPlaying() && player.getCurrentPosition()>2500){
            player.seekTo(player.getCurrentPosition()-2500);
        }
    }

//    /**
//     * Created by airal on 2018/4/24.
//     */
//
//    public static class AppApplication extends Application
//    {
//        @Override
//        public void onCreate()
//        {
//            registerActivityLifecycleCallbacks(new LifecycleHandler());
//            super.onCreate();
//        }
//
//    }
}
