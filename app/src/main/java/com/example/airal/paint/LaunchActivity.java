package com.example.airal.paint;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.airal.paint.System.SysConfig;
import com.example.airal.paint.activity.HomePageActivity;
import com.example.airal.paint.activity.BaseActivity;
import com.example.airal.paint.tool.HorizontalListView;

import java.util.ArrayList;
import java.util.List;

public class LaunchActivity extends BaseActivity {
    private TextView tvToast;
    private HorizontalListView listView;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void loadData() {
        stopService(getIntent());
        startService(getIntent());
    }

    public void startMusic(){
        SharedPreferences share=getSharedPreferences("music", MODE_PRIVATE);
        int position=share.getInt("music_index",0);

        AudioService.player = MediaPlayer.create(this, SysConfig.musicId[position]);
        Intent intent = new Intent();
        intent.setClass(this, AudioService.class);
    }

    @Override
    public void initView(){
        List<Bitmap>list = resizeBitmapList(drawableToBitmap(getResources().getDrawable(R.mipmap.homebg)));
        ItemAdapter itemAdapter = new ItemAdapter(LaunchActivity.this, list);
        listView = (HorizontalListView) findViewById(R.id.listview);
        listView.setAdapter(itemAdapter);
        listView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // 触摸按下时的操作

                        break;
                    case MotionEvent.ACTION_MOVE:
                        // 触摸移动时的操作
                        tvToast.setVisibility(View.GONE);
                        break;
                    case MotionEvent.ACTION_UP:
                        // 触摸抬起时的操作
                        handlerPostDelayed();
                        break;
                }
                return false;
            }
        });
        //设置成全屏模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //ivHomeBg=findViewById(R.id.iv_home_bg);
        tvToast=findViewById(R.id.tv_toast);
        tvToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomePageActivity.class));
                finish();
            }
        });

       // ivHomeBg.setImageBitmap(decodeSampledBitmapFromResource(imagePath,100,100));
//        ivHomeBg.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent=new Intent(getApplicationContext(),HomePageActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
//                finish();
//            }
//        });
        startMusic();
        startAnim();
    }

    int animTime = 3000;
    public void startAnim(){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.homebg_come_in);
        animation.setDuration(animTime);
        listView.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tvToast.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public ArrayList resizeBitmapList(Bitmap source) {
        BitmapFactory.Options bfoOptions = new BitmapFactory.Options();
        bfoOptions.inScaled = false;

        ArrayList bitmapsList = new ArrayList<>();
        int width = source.getWidth();
        int height = source.getHeight();
        int maxWidth = 1000;
        int count = width%maxWidth==0?width/maxWidth:width/maxWidth+1;
        if(width > maxWidth) {
            for(int i=0; i<count;i++){
                if(i != count -1) {
                    bitmapsList.add(Bitmap.createBitmap(source,maxWidth * i, 0,maxWidth, height));
                    System.out.println(maxWidth * i);
                }else{
                    bitmapsList.add(Bitmap.createBitmap(source,maxWidth * i, 0,width-maxWidth * i, height));
                }
            }
        }else{
            bitmapsList.add(source);
        }
        return bitmapsList;
    }


    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
    public static Bitmap decodeSampledBitmapFromFilePath(String imagePath,
                                                         int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imagePath, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(imagePath,options);
    }


    public static Bitmap drawableToBitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }

    public class ItemAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private List<Bitmap> list = new ArrayList<>();

        public ItemAdapter(Context context, List<Bitmap> list) {
            this.list.addAll(list);
            mInflater = LayoutInflater.from(context);
        }

        @Override
        public Bitmap getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Bitmap bitmap = list.get(position);
            if(convertView==null){
                convertView = mInflater.inflate(R.layout.image_item, parent, false);
            }
            ImageView imageView = (ImageView) convertView.findViewById(R.id.iv_image);
            imageView.setImageBitmap(bitmap);
            return convertView;
        }
    }

    private static Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            tvToast.setVisibility(View.VISIBLE);
        }

    };
    // handler+postDelayed 方式，反复发送延时消息
    private void handlerPostDelayed() {
        mHandler.postDelayed(mRunnable, 1000);
    }
}
