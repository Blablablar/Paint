package com.example.airal.paint.activity.setting;

import android.view.View;

import com.example.airal.paint.R;
import com.example.airal.paint.activity.BaseActivity;

/**
 * Created by airal on 2018/9/7.
 */

public class ProjectIntroduceActivity extends BaseActivity{
    @Override
    protected int getContentLayout() {
        return R.layout.activity_setting_project_introduce;
    }

    @Override
    public void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    public void loadData() {

    }
}
