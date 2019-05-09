package com.example.airal.paint.activity.study;

import android.content.Intent;
import android.view.View;

import com.example.airal.paint.R;
import com.example.airal.paint.activity.BaseActivity;
import com.example.airal.paint.activity.study.goutu.GoutuHomePageActivity;
import com.example.airal.paint.activity.study.jieziyuan.JieziyuanHomeActivity;
import com.example.airal.paint.activity.study.jifa.JifaHomePageActivity;
import com.example.airal.paint.activity.study.tikuan.TIkuanHomePageActivity;
import com.example.airal.paint.activity.study.yinzhang.YzHomePageActivity;

/**
 * Created by airal on 2018/10/10.
 */

public class StudyHomeActivity extends BaseActivity {
    @Override
    protected int getContentLayout() {
        return R.layout.activity_study_home;
    }


    @Override
    public void initView() {
        findViewById(R.id.iv_type_1).setOnClickListener(this);
        findViewById(R.id.iv_type_2).setOnClickListener(this);
        findViewById(R.id.iv_type_3).setOnClickListener(this);
        findViewById(R.id.iv_type_4).setOnClickListener(this);
        findViewById(R.id.iv_type_5).setOnClickListener(this);
        findViewById(R.id.iv_type_6).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_type_1:
                startActivity(new Intent(this, YzHomePageActivity.class));
                break;
            case R.id.iv_type_2:
                startActivity(new Intent(this, TIkuanHomePageActivity.class));
                break;
            case R.id.iv_type_3:
                startActivity(new Intent(this, JifaHomePageActivity.class));
                break;
            case R.id.iv_type_4:
                startActivity(new Intent(this, GoutuHomePageActivity.class));
                break;
            case R.id.iv_type_5:
                startActivity(new Intent(this, JianShanHomeActivity.class));
                break;
            case R.id.iv_type_6:
                startActivity(new Intent(this, JieziyuanHomeActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void loadData() {

    }
}
