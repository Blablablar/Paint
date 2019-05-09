package com.example.airal.paint.activity.knowledge.tool;

import android.content.Intent;
import android.view.View;

import com.example.airal.paint.R;
import com.example.airal.paint.activity.BaseActivity;
import com.example.airal.paint.activity.knowledge.tool.bi.BiHomePageActivity;
import com.example.airal.paint.activity.knowledge.tool.mo.MoHomePageActivity;
import com.example.airal.paint.activity.knowledge.tool.se.SeHomePageActivity;
import com.example.airal.paint.activity.knowledge.tool.yan.YanHomePageActivity;
import com.example.airal.paint.activity.knowledge.tool.zhi.ZhiHomePageActivity;

/**
 * Created by airal on 2018/9/7.
 */

public class GuohuaToolHomeActivity extends BaseActivity {
    @Override
    protected int getContentLayout() {
        return R.layout.activity_knowledge_tool;
    }


    @Override
    public void initView() {
        findViewById(R.id.iv_type_1).setOnClickListener(this);
        findViewById(R.id.iv_type_2).setOnClickListener(this);
        findViewById(R.id.iv_type_3).setOnClickListener(this);
        findViewById(R.id.iv_type_4).setOnClickListener(this);
        findViewById(R.id.iv_type_5).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_type_1:
                startActivity(new Intent(this, SeHomePageActivity.class));
                break;
            case R.id.iv_type_2:
                startActivity(new Intent(this, YanHomePageActivity.class));
                break;
            case R.id.iv_type_3:
                startActivity(new Intent(this, MoHomePageActivity.class));
                break;
            case R.id.iv_type_4:
                startActivity(new Intent(this, BiHomePageActivity.class));
                break;
            case R.id.iv_type_5:
                startActivity(new Intent(this, ZhiHomePageActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void loadData() {

    }
}
