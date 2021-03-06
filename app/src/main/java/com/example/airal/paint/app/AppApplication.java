package com.example.airal.paint.app;

import android.app.Application;

import com.example.airal.paint.tool.LifecycleHandler;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;


/**
 * Created by airal on 2018/4/24.
 */

public class AppApplication extends Application{
    public static boolean isDrag=false;
    @Override
    public void onCreate() {
        super.onCreate();
        Fresco.initialize(this);
        registerActivityLifecycleCallbacks(new LifecycleHandler());

        UMConfigure.init(this,"5a12384aa40fa3551f0001d1"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        PlatformConfig.setWeixin("wxe99afbcfaf66097d", "424210e0bf1014fd9697bb6839387570");
    }

}
