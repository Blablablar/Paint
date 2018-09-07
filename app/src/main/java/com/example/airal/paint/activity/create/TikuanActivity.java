package com.example.airal.paint.activity.create;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.airal.paint.R;
import com.example.airal.paint.System.SysConfig;
import com.example.airal.paint.activity.BaseActivity;
import com.example.airal.paint.activity.create.SharePaintActivity;

/**
 * Created by airal on 2018/5/5.
 */

public class TikuanActivity extends BaseActivity {
    EditText editText;
    ImageView imageView;
    TextView textView1;
    TextView textView2;
    LinearLayout linearLayout;
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.button:
                if(editText.getText().length()==4){
                    Typeface tf=Typeface.createFromAsset(getAssets(),"fonts/nokia.ttf");
                    textView1.setTypeface(tf);
                    textView2.setTypeface(tf);
                    textView1.setText(editText.getText().subSequence(0,2));
                    textView2.setText(editText.getText().subSequence(2,4));
                    mHandler.postDelayed(r,1000);
                }
                break;
            case R.id.iv_next:
                startActivity(new Intent(this, SharePaintActivity.class));
            default:
                break;
        }
    }

    @Override
    protected int getContentLayout() {
        return R.layout.activity_tikuan;
    }

    @Override
    protected void onResume() {
        super.onResume();
        SysConfig.tempSaveBitmap=null;
    }

    @Override
    public void loadData() {

    }

    //Bitmap bitmap1;
    @Override
    public void initView(){
        linearLayout=findViewById(R.id.linearLayout);

        textView1=findViewById(R.id.textview_1);
        textView2=findViewById(R.id.textview_2);
        Button button=findViewById(R.id.button);
        button.setOnClickListener(this);
        editText=findViewById(R.id.editText);
        imageView=findViewById(R.id.imageview);
        Bitmap bitmap1= BitmapFactory.decodeResource(getResources(), R.mipmap.yinzhang);
        imageView.setImageBitmap(bitmap1);

        findViewById(R.id.iv_next).setOnClickListener(this);

        mHandler = new MyHandler();
    }

    private Bitmap decodeResource(Resources resources, int id) {
        TypedValue value = new TypedValue();
        resources.openRawResource(id, value);
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inTargetDensity = value.density;
        return BitmapFactory.decodeResource(resources, id, opts);
    }


    private static class MyHandler extends android.os.Handler{

    }
    MyHandler mHandler;
    Runnable r = new Runnable() {
        @Override
        public void run() {
            Bitmap bitmap1= decodeResource(getResources(), R.mipmap.yinzhang);
            Bitmap bitmap2= SysConfig.loadBitmapFromView(linearLayout);
            Bitmap mergeBitmap=SysConfig.mergeBitmap(bitmap1,bitmap2);
            imageView.setImageBitmap(mergeBitmap);
            SysConfig.yzbitmap=mergeBitmap;
        }
    };
}
