package com.example.airal.paint.activity;

import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.airal.paint.R;
import com.example.airal.paint.System.SysConfig;
import com.example.airal.paint.model.Record;
import com.example.airal.paint.tool.TouchImageView;
import com.mob.MobSDK;

import java.util.HashMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by airal on 2018/5/5.
 */

public class SharePaintActivity extends MyActivity{
    @Override
    public void onClick(View v) {
        Bitmap bitmap;
        bitmap=SysConfig.loadBitmapFromView(findViewById(R.id.framelayout));
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
            touchImageView.setVisibility(View.GONE);
            SysConfig.tempSaveBitmap=bitmap;
        }
        switch(v.getId()){
            case R.id.iv_wx:
                if(null!=bitmap){
                    share(Wechat.NAME, bitmap);
                }else {
                    Toast.makeText(this,"保存出错",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_wx_moments:
                if(null!=bitmap){
                    share(WechatMoments.NAME, bitmap);
                }else {
                    Toast.makeText(this,"保存出错",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.iv_share:
                rlFullScreen.setVisibility(View.VISIBLE);
                startAlphaAnim(rlBottomBg,0,1, bottomAnimTime);
                rlBottomBg.setVisibility(View.VISIBLE);
                startBottomEnterAnim();
                break;
            case R.id.iv_save:
                if(null!=bitmap){
                    SysConfig.saveBitmap(this, bitmap);
                }else {
                    Toast.makeText(this,"保存出错",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.bg_bottom:
                startAlphaAnim(rlBottomBg,1,0, bottomAnimTime/2);
                startBottomExitAnim();
                break;
            default:
                break;
        }
    }

    LinearLayout rlBottom;
    RelativeLayout rlBottomBg;
    RelativeLayout rlFullScreen;
    @Override
    protected int getContentLayout() {
        return R.layout.activity_share;
    }

    @Override
    public void loadData() {
        MobSDK.init(this);
    }

    TouchImageView touchImageView;
    ImageView imageView;
    @Override
    public void initView(){
        imageView=findViewById(R.id.iv_bg);
        rlBottomBg=findViewById(R.id.bg_bottom);
        rlBottomBg.setOnClickListener(this);
        rlFullScreen=findViewById(R.id.full_screen);
        rlBottom=findViewById(R.id.rl_share);

        //bitmap=SysConfig.mergeBitmap(SysConfig.bitmap, SysConfig.yzbitmap);

        imageView.setImageBitmap(SysConfig.bitmap);

        touchImageView=findViewById(R.id.yinzhang);
        currentView=touchImageView;
        touchImageView.setImageBitmap(SysConfig.yzbitmap);

        if(SysConfig.tempSaveBitmap!=null){
            imageView.setImageBitmap(SysConfig.tempSaveBitmap);
            touchImageView.setVisibility(View.GONE);
        }

        findViewById(R.id.iv_wx).setOnClickListener(this);
        findViewById(R.id.iv_wx_moments).setOnClickListener(this);
        findViewById(R.id.iv_share).setOnClickListener(this);
        findViewById(R.id.iv_save).setOnClickListener(this);
    }


    public void share(String platform,Bitmap bitmap){
        Platform.ShareParams params=new Platform.ShareParams();
        params.setImageData(bitmap);
        params.setShareType(Platform.SHARE_IMAGE);
        params.setText("text");
        params.setTitle("title");
        //Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        Platform wechat = ShareSDK.getPlatform(platform);
        wechat.setPlatformActionListener(new PlatformActionListener() {
            @Override
            public void onComplete(Platform platform, int i, HashMap<String, Object> hashMap) {
                Toast.makeText(getApplicationContext(),"分享成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(Platform platform, int i, Throwable throwable) {
                Toast.makeText(getApplicationContext(),"分享失败",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel(Platform platform, int i) {
                Toast.makeText(getApplicationContext(),"已取消",Toast.LENGTH_SHORT).show();
            }
        });
        wechat.share(params);
    }

    private static final int MODE_NONE = 0;// 默认的触摸模式
    private static final int MODE_DRAG = 1;// 拖拽模式
    private static final int MODE_ZOOM = 2;// 缩放模式
    private int mode = MODE_NONE;

    float moveX;
    float moveY;
    double scale=1;
    private float oldDis = 1f;
    TouchImageView currentView;
    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if(currentView!=null) {
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN:// 单点接触屏幕时
                    moveX = event.getRawX();
                    moveY = event.getRawY();
                    mode = MODE_DRAG;
                    break;
                case MotionEvent.ACTION_POINTER_DOWN:
                    // 第二个手指按下事件
                    oldDis = calDis(event);
                    if (oldDis > 10F) {
                        mode = MODE_ZOOM;
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    //两点触控拖放
                    if (mode == MODE_ZOOM && event.getPointerCount() == 2) {
                        float x = event.getX(0) - event.getX(1);
                        float y = event.getY(0) - event.getY(1);

                        float newDis = calDis(event);
                        //指尖移动距离大于10F缩放
                        if (newDis > 15F) {
                            scale = newDis / oldDis;
                            setScale(currentView,scale);
                        }
                    }
                    break;
                case MotionEvent.ACTION_UP:// 单点离开屏幕时
                    mode = MODE_NONE;
                    break;
                case MotionEvent.ACTION_POINTER_UP:// 第二个点离开屏幕时
                    mode = MODE_NONE;
                    break;
                default:
                    break;
            }
        }

        if (getWindow().superDispatchTouchEvent(event)) {
            return true;
        }
        return onTouchEvent(event);
    }

    // 计算两个触摸点之间的距离
    private float calDis(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private void setScale(TouchImageView view,double temp)
    {
        float scale=view.getScaleX()+(float) (temp-1)/2;
        if(scale>0.2&&scale<5){
            view.setScaleX(scale);
            view.setScaleY(scale);
        }
        System.out.println("scale:"+scale);
    }

    int bottomAnimTime = 300;
    public void startBottomEnterAnim() {
        //底部功能按钮动画
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.bm_dialog_enter);
        animation.setDuration(bottomAnimTime/2);
        rlBottom.startAnimation(animation);
        rlBottom.setVisibility(View.VISIBLE);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                rlFullScreen.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void startBottomExitAnim() {
        //底部功能按钮动画
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.bm_dialog_exit);
        animation.setDuration(bottomAnimTime/2);
        rlBottom.startAnimation(animation);
        rlBottom.setVisibility(View.GONE);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                rlFullScreen.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void startAlphaAnim(View view, int start, int end, int animTime){
        ObjectAnimator.ofFloat(view, View.ALPHA, start, end)
                .setDuration(animTime)
                .start();
    }

    public void addRecord(TouchImageView view){
        Record record=new Record();
        record.tag=Record.TAG_TOUCH;
        record.view=view;
        record.x=view.getX();
        record.y=view.getY();
        record.scale=view.getScaleX();
        record.rotato=view.getRotation();
    }
}
