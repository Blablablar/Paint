package com.example.airal.paint.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.airal.paint.R;

/**
 * Created by airal on 2018/4/24.
 */

public class HomePageActivity extends MyActivity implements View.OnClickListener{
    private LinearLayout ll_0_0;
    private LinearLayout ll_0_1;
    private LinearLayout ll_1_0;
    private LinearLayout ll_1_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        initView();
    }

    @Override
    public void initView(){
        //设置成全屏模式
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ll_0_0=findViewById(R.id.ll_0_0);
        ll_0_1=findViewById(R.id.ll_0_1);
        ll_1_0=findViewById(R.id.ll_1_0);
        ll_1_1=findViewById(R.id.ll_1_1);

        ll_0_0.setOnClickListener(this);
        ll_0_1.setOnClickListener(this);
        ll_1_0.setOnClickListener(this);
        ll_1_1.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_0_0:
                break;
            case R.id.ll_0_1:
                break;
            case R.id.ll_1_0:
                startActivity(new Intent(getApplicationContext(),ChooseBgActivity.class));
                break;
            case R.id.ll_1_1:
                startActivity(new Intent(getApplicationContext(),SetActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}

