package com.example.airal.paint.activity.knowledge.type;

import android.content.Intent;
import android.view.View;

import com.example.airal.paint.R;
import com.example.airal.paint.activity.BaseActivity;
import com.example.airal.paint.activity.knowledge.GuohuaHuafuActivity;
import com.example.airal.paint.activity.knowledge.GuohuaToolActivity;
import com.example.airal.paint.activity.knowledge.GushuActivity;

/**
 * Created by airal on 2018/9/7.
 */

public class GuohuaLeiBieActivity extends BaseActivity{
    @Override
    protected int getContentLayout() {
        return R.layout.activity_type;
    }

    @Override
    public void initView() {
        findViewById(R.id.iv_type_1).setOnClickListener(this);
        findViewById(R.id.iv_type_2).setOnClickListener(this);
        findViewById(R.id.iv_type_3).setOnClickListener(this);
        findViewById(R.id.iv_type_4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_type_1:
                startActivity(new Intent(GuohuaLeiBieActivity.this, BiaoxianActivity.class));
                break;
            case R.id.iv_type_2:
                startActivity(new Intent(GuohuaLeiBieActivity.this, JiFaActivity.class));
                break;
            case R.id.iv_type_3:
                startActivity(new Intent(GuohuaLeiBieActivity.this, ColorActivity.class));
                break;
            case R.id.iv_type_4:
                startActivity(new Intent(GuohuaLeiBieActivity.this, TicaiActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void loadData() {

    }
}
