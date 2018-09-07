package com.example.airal.paint.activity;

import android.content.Intent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.airal.paint.R;
import com.example.airal.paint.activity.create.ChooseBgActivity;
import com.example.airal.paint.activity.setting.SetActivity;

/**
 * Created by airal on 2018/4/24.
 */

public class HomePageActivity extends BaseActivity {
    private ImageView ll_0_0;
    private ImageView ll_0_1;
    private ImageView ll_1_0;
    private ImageView ll_1_1;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_home_page;
    }

    @Override
    public void loadData() {

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
                //设置关于
                startActivity(new Intent(getApplicationContext(),SetActivity.class));
                break;
            case R.id.ll_0_1:
                //知识链接
                break;
            case R.id.ll_1_0:
                //自由创作
                startActivity(new Intent(getApplicationContext(),ChooseBgActivity.class));
                break;
            case R.id.ll_1_1:
                //学习
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

