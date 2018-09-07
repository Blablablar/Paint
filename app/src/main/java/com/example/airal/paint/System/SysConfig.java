package com.example.airal.paint.System;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.os.Build;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.widget.Toast;

import com.example.airal.paint.R;
import com.example.airal.paint.model.Record;

import java.util.ArrayList;

/**
 * Created by airal on 2018/5/7.
 */

public class SysConfig {
    public static Bitmap bitmap=null;
    public static Bitmap tempSaveBitmap=null;
    public static Bitmap yzbitmap=null;
    public static int musicId[]={R.raw.music_1, R.raw.music_2, R.raw.music_3, R.raw.music_4, R.raw.music_5};
    public Record tikuanRecord;

    public static Bitmap loadBitmapFromView(View v) {
        int w = v.getWidth();
        int h = v.getHeight();
////        //c.drawColor(Color.WHITE);
////        /** 如果不设置canvas画布为白色，则生成透明 */
//        v.layout(0, 0, w, h);
        Bitmap bmp= Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bmp);
        v.draw(canvas);
        return bmp;
    }

    public static void saveBitmap(Activity activity, Bitmap bitmap){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(activity.getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(activity,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
            }else {
                MediaStore.Images.Media.insertImage(activity.getContentResolver(), bitmap,"title", "description");
                Toast.makeText(activity.getApplicationContext(),"保存成功",Toast.LENGTH_SHORT).show();
            }
        } else {
            MediaStore.Images.Media.insertImage(activity.getContentResolver(), bitmap, "title", "description");
            Toast.makeText(activity.getApplicationContext(), "保存成功", Toast.LENGTH_SHORT).show();
        }
    }

    public static Bitmap mergeBitmap(Bitmap firstBitmap, Bitmap secondBitmap) {
        Bitmap bitmap = Bitmap.createBitmap(firstBitmap.getWidth(), firstBitmap.getHeight(), firstBitmap.getConfig());
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(firstBitmap, new Matrix(), null);
        canvas.drawBitmap(secondBitmap, 0, 0, null);
        return bitmap;
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

    public static ArrayList resizeBitmapList(Bitmap source) {
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
}
