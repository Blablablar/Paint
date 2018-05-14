package com.example.airal.paint.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.airal.paint.R;
import com.example.airal.paint.System.SysConfig;
import com.example.airal.paint.adapter.HorizontalAdapter;
import com.example.airal.paint.model.Record;
import com.example.airal.paint.tool.HorizontalListView;
import com.example.airal.paint.tool.TouchImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by airal on 2018/4/24.
 */

public class CreatePaintActivity extends MyActivity implements View.OnClickListener{
    private HorizontalListView horizontalListView;
    private ImageView ivBg;
    RelativeLayout relativeLayout;
    List<Record>recordList=new ArrayList<>();
    Record record=new Record();

    @Override
    protected int getContentLayout() {
        return R.layout.activity_create_paint;
    }

    @Override
    public void loadData() {

    }

    public void setRandomPos(View v){
        Random rand = new Random();
        int wMax=screenwidth-screenheight/5;
        int wMin=100;

        int hMax=screenheight/5*4;
        int hMin=100;

        int w = rand.nextInt(wMax)%(wMax-wMin+1) + wMin;
        int h = rand.nextInt(hMax)%(hMax-hMin+1) + hMin;

        v.setTranslationX(w);
        v.setTranslationY(h);
    }

    int screenwidth=0;
    int screenheight=0;
    public void getScreenSize(){
        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        manager.getDefaultDisplay().getMetrics(outMetrics);
        screenwidth=outMetrics.widthPixels;
        screenheight=outMetrics.heightPixels;
    }

    int viewNum=0;
    @Override
    public void initView(){
        getScreenSize();
        relativeLayout=findViewById(R.id.rl_page_view);

        TouchImageView touchImageView=new TouchImageView(getApplicationContext());
        touchImageView.setImageResource(R.mipmap.duicheng);
        //设置成全屏模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        horizontalListView=findViewById(R.id.listview);
        List imgIds=new ArrayList();
        imgIds.add(R.mipmap.bm_icon_0);
        imgIds.add(R.mipmap.bm_icon_1);
        imgIds.add(R.mipmap.bm_icon_2);
        imgIds.add(R.mipmap.bm_icon_3);
        imgIds.add(R.mipmap.bm_icon_4);
        imgIds.add(R.mipmap.bm_icon_0);
        imgIds.add(R.mipmap.bm_icon_1);
        imgIds.add(R.mipmap.bm_icon_2);
        imgIds.add(R.mipmap.bm_icon_3);
        imgIds.add(R.mipmap.bm_icon_4);
        imgIds.add(R.mipmap.bm_icon_0);
        imgIds.add(R.mipmap.bm_icon_1);
        imgIds.add(R.mipmap.bm_icon_2);
        imgIds.add(R.mipmap.bm_icon_3);
        imgIds.add(R.mipmap.bm_icon_4);
        HorizontalAdapter horizontalAdapter=new HorizontalAdapter(this,imgIds);
        horizontalAdapter.setItemListener(new HorizontalAdapter.onItemClickListener(){
            @Override
            public void onItemClick(ImageView v) {
                viewNum++;
                TouchImageView view=new TouchImageView(getApplicationContext());
                view.setImageDrawable(v.getDrawable());
                relativeLayout.addView(view);
                setRandomPos(view);
                currentView=view;
                Record record=new Record();
                record.tag=Record.TAG_ADD;
                record.view=view;
                recordList.add(record);
                System.out.println(recordList.size());

                view.setImageListener(new TouchImageView.onImageClickListener() {
                    @Override
                    public void onItemClick(View v) {
                        if(mode==MODE_NONE){
                            currentView=(TouchImageView) v;
                        }
                    }

                    @Override
                    public void addRecord(Record record) {
                        recordList.add(record);
                        //System.out.println(recordList.size());
                    }
                });
            }
        });
        horizontalListView.setAdapter(horizontalAdapter);

        ivBg=findViewById(R.id.iv_backgroud);
        bgIds.add(R.mipmap.backgroud1);
        bgIds.add(R.mipmap.backgroud2);
        bgIds.add(R.mipmap.backgroud3);
        currentBg=getIntent().getIntExtra("position",0);
        ivBg.setImageResource((int)bgIds.get(currentBg));

        findViewById(R.id.iv_bottom_func_1).setOnClickListener(this);
        findViewById(R.id.iv_bottom_func_2).setOnClickListener(this);

        findViewById(R.id.iv_delete).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_cancel).setOnClickListener(this);
    }

    List bgIds=new ArrayList();
    int currentBg=0;
    int maxBgNum=3;
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_bottom_func_1:
//                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//                        ActivityCompat.requestPermissions(this,
//                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
//                                1);
//                    }else {
//                        MediaStore.Images.Media.insertImage(getContentResolver(), capture(this), "title", "description");
//                    }
//                }
                if(currentView!=null){
                    currentView.setRotationY(currentView.getRotationY()+180);
                }
                break;
            //更换背景图片
            case R.id.iv_bottom_func_2:
//                currentBg=changeCurrentBg(currentBg);
//                ivBg.setImageResource((int)bgIds.get(currentBg));

                FrameLayout frameLayout=findViewById(R.id.framelayout);
                SysConfig.bitmap= SysConfig.loadBitmapFromView(frameLayout);
                //SysConfig.saveBitmap(this,SysConfig.bitmap);

                Intent intent=new Intent(getApplicationContext(),TikuanActivity.class);
                startActivity(intent);

                break;

            //撤回
            case R.id.iv_cancel:
                if(recordList.size()>0){
                    Record record=recordList.get(recordList.size()-1);
                    if(record!=null){
                        TouchImageView view=record.view;
                        if(record.tag==Record.TAG_ADD){
                            view.setVisibility(View.GONE);
                            recordList.remove(recordList.size()-1);

                            System.out.println("GONE");
                            System.out.println(recordList.size());
                        }else if(record.tag==Record.TAG_TOUCH){
                            view.setX(record.x);
                            view.setY(record.y);
                            view.setScaleX(record.scale);
                            view.setScaleY(record.scale);
                            view.setRotation(record.rotato);
                            recordList.remove(recordList.size()-1);

                            System.out.println("CANCEL");
                            System.out.println(recordList.size());
                        }
                    }
                }
                break;
            //返回
            case R.id.iv_back:
                ActivityCompat.finishAfterTransition(this);
                break;
            //删除
            case R.id.iv_delete:
                relativeLayout.removeAllViews();
                break;
            default:
                break;
        }
    }

    public int changeCurrentBg(int currentBgNum) {
        if(currentBgNum>=maxBgNum-1){
            currentBgNum=0;
        }else{
            currentBgNum++;
        }
        return currentBgNum;
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
    float saveRotate;
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
//        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            onUserInteraction();
//        }

        if (getWindow().superDispatchTouchEvent(event)) {
            return true;
        }
        return onTouchEvent(event);
    }

    public Bitmap capture(Activity activity) {
        activity.getWindow().getDecorView().setDrawingCacheEnabled(true);
        Bitmap bmp = activity.getWindow().getDecorView().getDrawingCache();
        return bmp;
    }

    // 计算两个触摸点之间的距离
    private float calDis(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float) Math.sqrt(x * x + y * y);
    }

    private void setScale(TouchImageView view,double temp)
    {
        record=new Record();
        record.tag=Record.TAG_TOUCH;
        record.view=view;
        record.x=view.getX();
        record.y=view.getY();
        record.scale=view.getScaleX();
        record.rotato=view.getRotation();

        float scale=view.getScaleX()+(float) (temp-1)/2;
        if(scale>0.2&&scale<5){
            view.setScaleX(scale);
            view.setScaleY(scale);
        }
        System.out.println("scale:"+scale);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }
}
