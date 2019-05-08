package com.example.airal.paint.activity.create;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.airal.paint.R;
import com.example.airal.paint.System.SysConfig;
import com.example.airal.paint.activity.BaseActivity;
import com.example.airal.paint.activity.create.TikuanActivity;
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

public class CreatePaintActivity extends BaseActivity implements View.OnClickListener{
    private HorizontalListView horizontalListView;
    private ImageView ivBg;
    RelativeLayout relativeLayout;
    List<Record>recordList=new ArrayList<>();
    List<Integer> imgIds=new ArrayList();
    private HorizontalAdapter horizontalAdapter;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_create_paint;
    }

    @Override
    public void loadData() {

    }

    public void setRandomPos(View v){
        Random rand = new Random();
        int wMax=screenwidth-100;
        int wMin=100;

        int hMax=screenheight-100;
        int hMin=100;

        int w = rand.nextInt(wMax)%(wMax-wMin+1) + wMin;
        int h = rand.nextInt(hMax)%(hMax-hMin+1) + hMin;

        v.setTranslationX(w);
        v.setTranslationY(-h);
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().getSharedElementEnterTransition().setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(400);
            getWindow().getSharedElementReturnTransition().setInterpolator(new AccelerateDecelerateInterpolator()).setDuration(400);
        }

        getScreenSize();
        relativeLayout=findViewById(R.id.rl_page_view);

        TouchImageView touchImageView=new TouchImageView(getApplicationContext());
        touchImageView.setImageResource(R.mipmap.duicheng);
        //设置成全屏模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        horizontalListView=findViewById(R.id.listview);
        initZhiwu();
        horizontalAdapter=new HorizontalAdapter(this,imgIds);
//        horizontalAdapter.setItemListener(new HorizontalAdapter.onItemClickListener(){
//            @Override
//            public void onItemClick(ImageView v) {
//                viewNum++;
//                TouchImageView view=new TouchImageView(getApplicationContext());
//                view.setImageDrawable(v.getDrawable());
//                relativeLayout.addView(view);
//                setRandomPos(view);
//                currentView=view;
//                Record record=new Record();
//                record.tag=Record.TAG_ADD;
//                record.view=view;
//                recordList.add(record);
//                System.out.println(recordList.size());
//
//                view.setImageListener(new TouchImageView.onImageClickListener() {
//                    @Override
//                    public void onItemClick(View v) {
//                        if(mode==MODE_NONE){
//                            currentView=(TouchImageView) v;
//                        }
//                    }
//
//                    @Override
//                    public void addRecord(Record record) {
//                        recordList.add(record);
//                        //System.out.println(recordList.size());
//                    }
//                });
//            }
//        });
        horizontalListView.setAdapter(horizontalAdapter);
        horizontalListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewNum++;
                TouchImageView appendView=new TouchImageView(getApplicationContext());
                appendView.setImageDrawable(getResources().getDrawable((int)imgIds.get(position)));
                ViewGroup parentView = (ViewGroup) appendView.getParent();
                if (parentView != null) {
                    parentView.removeAllViews();
                }
                relativeLayout.addView(appendView);
                setRandomPos(appendView);
                currentView=appendView;
                Record record=new Record();
                record.tag=Record.TAG_ADD;
                record.view=appendView;
                recordList.add(record);
                System.out.println(recordList.size());

                appendView.setImageListener(new TouchImageView.onImageClickListener() {
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

        ivBg=findViewById(R.id.iv_backgroud);
        bgIds.add(R.drawable.bg1);
        bgIds.add(R.drawable.bg2);
        bgIds.add(R.drawable.bg3);
        bgIds.add(R.drawable.bg4);
        currentBg=getIntent().getIntExtra("position",0);
        ivBg.setImageResource((int)bgIds.get(currentBg));

        findViewById(R.id.iv_bottom_func_1).setOnClickListener(this);
        findViewById(R.id.iv_bottom_func_2).setOnClickListener(this);

        findViewById(R.id.iv_delete).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);
        findViewById(R.id.iv_cancel).setOnClickListener(this);

        findViewById(R.id.iv_renwu).setOnClickListener(this);
        findViewById(R.id.iv_zhiwu).setOnClickListener(this);
        findViewById(R.id.iv_shanshi).setOnClickListener(this);
        mHandler = new MyHandler();
        mHandler.postDelayed(r,500);
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
                    Record record=new Record();
                    record.tag=Record.TAG_TOUCH;
                    record.view=currentView;
                    record.x=currentView.getX();
                    record.y=currentView.getY();
                    record.scale=currentView.getScaleX();
                    record.rotato=currentView.getRotation();
                    record.rotatoY=currentView.getRotationY();
                    recordList.add(record);

                    currentView.setRotationY(currentView.getRotationY()+180);
                }
                break;
            //更换背景图片
            case R.id.iv_bottom_func_2:
//                currentBg=changeCurrentBg(currentBg);
//                ivBg.setImageResource((int)bgIds.get(currentBg));

                RelativeLayout frameLayout=findViewById(R.id.framelayout);
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
                            view.setRotationY(record.rotatoY);
                            recordList.remove(recordList.size()-1);

                            System.out.println("CANCEL");
                            System.out.println(recordList.size());
                        }
                    }
                }
                break;
            //返回
            case R.id.iv_back:
                onToolExitAnimation();
                ActivityCompat.finishAfterTransition(this);
                break;
            //删除
            case R.id.iv_delete:
                relativeLayout.removeAllViews();
                break;
            //人物素材
            case R.id.iv_renwu:
                initRenwu();
                horizontalAdapter=new HorizontalAdapter(this,imgIds);
                horizontalListView.setAdapter(horizontalAdapter);
                break;
            //植物素材
            case R.id.iv_zhiwu:
                initZhiwu();
                horizontalAdapter=new HorizontalAdapter(this,imgIds);
                horizontalListView.setAdapter(horizontalAdapter);
                break;
            //山石素材
            case R.id.iv_shanshi:
                initShanshi();
                horizontalAdapter=new HorizontalAdapter(this,imgIds);
                horizontalListView.setAdapter(horizontalAdapter);
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
    Record record;
    int time=0;
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
                        time=0;
                        record = new Record();
                        record.tag=Record.TAG_TOUCH;
                        record.view=currentView;
                        record.x=currentView.getX();
                        record.y=currentView.getY();
                        record.scale=currentView.getScaleX();
                        record.rotato=currentView.getRotation();
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
                            if(time==0&&record!=null){
                                recordList.add(record);
                            }
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
        float scale=view.getScaleX()+(float) (temp-1)/2;
        if(scale>0.15&&scale<4){
            view.setScaleX(scale);
            view.setScaleY(scale);
        }
        System.out.println("scale:"+scale);
    }

    @Override
    public void onBackPressed() {
        onToolExitAnimation();
        ActivityCompat.finishAfterTransition(this);
    }

    public void onToolInAnimation(){
        findViewById(R.id.ll_left_fuc).setVisibility(View.VISIBLE);
        startEnterAnim(findViewById(R.id.ll_left_fuc), top);

        findViewById(R.id.iv_back).setVisibility(View.VISIBLE);
        startEnterAnim(findViewById(R.id.iv_back), top);

//        findViewById(R.id.ll_right_top).setVisibility(View.VISIBLE);
//        startEnterAnim(findViewById(R.id.ll_right_top), top);

//        findViewById(R.id.bottom_right).setVisibility(View.VISIBLE);
//        startEnterAnim(findViewById(R.id.bottom_right), bottom);

        findViewById(R.id.listview).setVisibility(View.VISIBLE);
        startEnterAnim(findViewById(R.id.listview), bottom);
    }

    public void onToolExitAnimation(){
        startExitAnim(findViewById(R.id.iv_back), top);
//        startExitAnim(findViewById(R.id.ll_right_top), top);
//        startExitAnim(findViewById(R.id.bottom_right), bottom);
        startExitAnim(findViewById(R.id.listview), bottom);
    }

    int animTime=400;
//    public void aphlaAnim(View view){
//        ObjectAnimator.ofFloat(view, View.ALPHA, 0, 1)
//                .setDuration(animTime)
//                .start();
//    }
    int bottom=0;
    int top=1;
    public void startEnterAnim(View view, int bottomOrTop) {
        //底部功能按钮动画
        Animation animation;
        if(bottomOrTop==0){
            animation = AnimationUtils.loadAnimation(this, R.anim.bm_dialog_enter);
        }else{
            animation = AnimationUtils.loadAnimation(this, R.anim.bm_dialog_top_enter);
        }
        animation.setDuration(animTime / 2);
        view.startAnimation(animation);
    }

    public void startExitAnim(View view,  int bottomOrTop) {
        //底部功能按钮动画
        Animation animation;
        if(bottomOrTop==0){
            animation = AnimationUtils.loadAnimation(this, R.anim.bm_dialog_exit);
        }else{
            animation = AnimationUtils.loadAnimation(this, R.anim.bm_dialog_top_exit);
        }
        animation.setDuration(animTime / 2);
        view.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private static class MyHandler extends android.os.Handler{
    }
    MyHandler mHandler;
    Runnable r = new Runnable() {
        @Override
        public void run() {
            onToolInAnimation();
        }
    };
    Runnable exit = new Runnable() {
        @Override
        public void run() {

        }
    };

    private void initRenwu(){
        imgIds.clear();
        imgIds.add(R.mipmap.renwu_1);
        imgIds.add(R.mipmap.renwu_2);
        imgIds.add(R.mipmap.renwu_3);
        imgIds.add(R.mipmap.renwu_4);
        imgIds.add(R.mipmap.renwu_5);
        imgIds.add(R.mipmap.renwu_6);
        imgIds.add(R.mipmap.renwu_7);
        imgIds.add(R.mipmap.renwu_8);
        imgIds.add(R.mipmap.renwu_9);
        imgIds.add(R.mipmap.renwu_10);
        imgIds.add(R.mipmap.renwu_11);
        imgIds.add(R.mipmap.renwu_12);
        imgIds.add(R.mipmap.renwu_13);
        imgIds.add(R.mipmap.renwu_14);
        imgIds.add(R.mipmap.renwu_15);
        imgIds.add(R.mipmap.renwu_16);
        imgIds.add(R.mipmap.renwu_17);
        imgIds.add(R.mipmap.renwu_18);
        imgIds.add(R.mipmap.renwu_19);
        imgIds.add(R.mipmap.renwu_20);
        imgIds.add(R.mipmap.renwu_21);
        imgIds.add(R.mipmap.renwu_22);
        imgIds.add(R.mipmap.renwu_23);
        imgIds.add(R.mipmap.renwu_24);
        imgIds.add(R.mipmap.renwu_25);
        imgIds.add(R.mipmap.renwu_26);
        imgIds.add(R.mipmap.renwu_27);
        imgIds.add(R.mipmap.renwu_28);
        imgIds.add(R.mipmap.renwu_29);
        imgIds.add(R.mipmap.renwu_30);
        imgIds.add(R.mipmap.renwu_31);
        imgIds.add(R.mipmap.renwu_32);
        imgIds.add(R.mipmap.renwu_33);
        imgIds.add(R.mipmap.renwu_34);
        imgIds.add(R.mipmap.renwu_35);
        imgIds.add(R.mipmap.renwu_36);
        imgIds.add(R.mipmap.renwu_37);
        imgIds.add(R.mipmap.renwu_38);
        imgIds.add(R.mipmap.renwu_39);
        imgIds.add(R.mipmap.renwu_40);
        imgIds.add(R.mipmap.renwu_41);
        imgIds.add(R.mipmap.renwu_42);
        imgIds.add(R.mipmap.renwu_43);
        imgIds.add(R.mipmap.renwu_44);
        imgIds.add(R.mipmap.renwu_45);
        imgIds.add(R.mipmap.renwu_46);
        imgIds.add(R.mipmap.renwu_47);
        imgIds.add(R.mipmap.renwu_48);
        imgIds.add(R.mipmap.renwu_49);
        imgIds.add(R.mipmap.renwu_50);
        imgIds.add(R.mipmap.renwu_51);
        imgIds.add(R.mipmap.renwu_52);
        imgIds.add(R.mipmap.renwu_53);
        imgIds.add(R.mipmap.renwu_54);
        imgIds.add(R.mipmap.renwu_55);
        imgIds.add(R.mipmap.renwu_56);
        imgIds.add(R.mipmap.renwu_57);
    }

    private void initShanshi(){
        imgIds.clear();
        imgIds.add(R.mipmap.shanshi_1);
        imgIds.add(R.mipmap.shanshi_2);
        imgIds.add(R.mipmap.shanshi_3);
        imgIds.add(R.mipmap.shanshi_4);
        imgIds.add(R.mipmap.shanshi_5);
        imgIds.add(R.mipmap.shanshi_6);
        imgIds.add(R.mipmap.shanshi_7);
        imgIds.add(R.mipmap.shanshi_8);
        imgIds.add(R.mipmap.shanshi_9);
        imgIds.add(R.mipmap.shanshi_10);
        imgIds.add(R.mipmap.shanshi_11);
        imgIds.add(R.mipmap.shanshi_12);
        imgIds.add(R.mipmap.shanshi_13);
        imgIds.add(R.mipmap.shanshi_14);
        imgIds.add(R.mipmap.shanshi_15);
        imgIds.add(R.mipmap.shanshi_16);
        imgIds.add(R.mipmap.shanshi_17);
        imgIds.add(R.mipmap.shanshi_18);
        imgIds.add(R.mipmap.shanshi_19);
        imgIds.add(R.mipmap.shanshi_20);
        imgIds.add(R.mipmap.shanshi_21);
        imgIds.add(R.mipmap.shanshi_22);
        imgIds.add(R.mipmap.shanshi_23);
        imgIds.add(R.mipmap.shanshi_24);
        imgIds.add(R.mipmap.shanshi_25);
        imgIds.add(R.mipmap.shanshi_26);
        imgIds.add(R.mipmap.shanshi_27);
        imgIds.add(R.mipmap.shanshi_28);
        imgIds.add(R.mipmap.shanshi_29);
        imgIds.add(R.mipmap.shanshi_30);
        imgIds.add(R.mipmap.shanshi_31);
        imgIds.add(R.mipmap.shanshi_32);
        imgIds.add(R.mipmap.shanshi_33);
        imgIds.add(R.mipmap.shanshi_34);
        imgIds.add(R.mipmap.shanshi_35);
        imgIds.add(R.mipmap.shanshi_36);
        imgIds.add(R.mipmap.shanshi_37);
        imgIds.add(R.mipmap.shanshi_38);
        imgIds.add(R.mipmap.shanshi_39);
        imgIds.add(R.mipmap.shanshi_40);
        imgIds.add(R.mipmap.shanshi_41);
        imgIds.add(R.mipmap.shanshi_42);
        imgIds.add(R.mipmap.shanshi_43);
        imgIds.add(R.mipmap.shanshi_44);
        imgIds.add(R.mipmap.shanshi_45);
        imgIds.add(R.mipmap.shanshi_46);
        imgIds.add(R.mipmap.shanshi_47);
        imgIds.add(R.mipmap.shanshi_48);
        imgIds.add(R.mipmap.shanshi_49);
        imgIds.add(R.mipmap.shanshi_50);
        imgIds.add(R.mipmap.shanshi_51);
        imgIds.add(R.mipmap.shanshi_52);
        imgIds.add(R.mipmap.shanshi_53);
        imgIds.add(R.mipmap.shanshi_54);
        imgIds.add(R.mipmap.shanshi_55);
        imgIds.add(R.mipmap.shanshi_56);
        imgIds.add(R.mipmap.shanshi_57);
        imgIds.add(R.mipmap.shanshi_58);
        imgIds.add(R.mipmap.shanshi_59);
        imgIds.add(R.mipmap.shanshi_60);
        imgIds.add(R.mipmap.shanshi_61);
        imgIds.add(R.mipmap.shanshi_62);
        imgIds.add(R.mipmap.shanshi_63);
        imgIds.add(R.mipmap.shanshi_64);
        imgIds.add(R.mipmap.shanshi_65);
        imgIds.add(R.mipmap.shanshi_66);
        imgIds.add(R.mipmap.shanshi_67);
        imgIds.add(R.mipmap.shanshi_68);
    }

    private void initZhiwu(){
        imgIds.clear();
        imgIds.add(R.mipmap.zhiwu_1);
        imgIds.add(R.mipmap.zhiwu_2);
        imgIds.add(R.mipmap.zhiwu_3);
        imgIds.add(R.mipmap.zhiwu_4);
        imgIds.add(R.mipmap.zhiwu_5);
        //imgIds.add(R.mipmap.zhiwu_6);
        imgIds.add(R.mipmap.zhiwu_7);
        imgIds.add(R.mipmap.zhiwu_8);
        imgIds.add(R.mipmap.zhiwu_9);
        imgIds.add(R.mipmap.zhiwu_10);
        //imgIds.add(R.mipmap.zhiwu_11);
        imgIds.add(R.mipmap.zhiwu_12);
        imgIds.add(R.mipmap.zhiwu_13);
        imgIds.add(R.mipmap.zhiwu_14);
        imgIds.add(R.mipmap.zhiwu_15);
        imgIds.add(R.mipmap.zhiwu_16);
        imgIds.add(R.mipmap.zhiwu_17);
        imgIds.add(R.mipmap.zhiwu_18);
        imgIds.add(R.mipmap.zhiwu_19);
        imgIds.add(R.mipmap.zhiwu_20);
        imgIds.add(R.mipmap.zhiwu_21);
        imgIds.add(R.mipmap.zhiwu_22);
        imgIds.add(R.mipmap.zhiwu_23);
        imgIds.add(R.mipmap.zhiwu_24);
        imgIds.add(R.mipmap.zhiwu_25);
        imgIds.add(R.mipmap.zhiwu_26);
        imgIds.add(R.mipmap.zhiwu_27);
        imgIds.add(R.mipmap.zhiwu_28);
        imgIds.add(R.mipmap.zhiwu_29);
        imgIds.add(R.mipmap.zhiwu_30);
        imgIds.add(R.mipmap.zhiwu_31);
        imgIds.add(R.mipmap.zhiwu_32);
        imgIds.add(R.mipmap.zhiwu_33);
        imgIds.add(R.mipmap.zhiwu_34);
        imgIds.add(R.mipmap.zhiwu_35);
        imgIds.add(R.mipmap.zhiwu_36);
        imgIds.add(R.mipmap.zhiwu_37);
        imgIds.add(R.mipmap.zhiwu_38);
        //imgIds.add(R.mipmap.zhiwu_39);
        imgIds.add(R.mipmap.zhiwu_40);
        imgIds.add(R.mipmap.zhiwu_41);
        //imgIds.add(R.mipmap.zhiwu_42);
        imgIds.add(R.mipmap.zhiwu_43);
        imgIds.add(R.mipmap.zhiwu_44);
        imgIds.add(R.mipmap.zhiwu_45);
        imgIds.add(R.mipmap.zhiwu_46);
        imgIds.add(R.mipmap.zhiwu_47);
        imgIds.add(R.mipmap.zhiwu_48);
        imgIds.add(R.mipmap.zhiwu_49);
        imgIds.add(R.mipmap.zhiwu_50);
    }
}
