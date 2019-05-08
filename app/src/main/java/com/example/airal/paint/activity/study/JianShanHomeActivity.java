package com.example.airal.paint.activity.study;

import android.content.Intent;
import android.view.View;

import com.example.airal.paint.R;
import com.example.airal.paint.activity.BaseActivity;

/**
 * Created by airal on 2018/10/10.
 */

public class JianShanHomeActivity extends BaseActivity {
    @Override
    protected int getContentLayout() {
        return R.layout.activity_jianshang_home;
    }


    @Override
    public void initView() {
        findViewById(R.id.iv_type_1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_type_1:
                startActivity(new Intent(this, GuanShanImgActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void loadData() {

    }
}

