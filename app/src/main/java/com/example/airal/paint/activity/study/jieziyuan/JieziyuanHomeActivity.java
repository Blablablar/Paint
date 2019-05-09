package com.example.airal.paint.activity.study.jieziyuan;

import android.content.Intent;
import android.view.View;

import com.example.airal.paint.R;
import com.example.airal.paint.activity.BaseActivity;
import com.example.airal.paint.activity.study.jifa.JifaHomePageActivity;
import com.example.airal.paint.activity.study.yinzhang.PreviewActivity;

public class JieziyuanHomeActivity extends BaseActivity {
    @Override
    protected int getContentLayout() {
        return R.layout.activity_js_jiezihua_home;
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
        Intent intent = new Intent(JieziyuanHomeActivity.this, PreviewActivity.class);
        switch (v.getId()){
            case R.id.iv_type_1:
//                intent.putExtra("drawable", R.drawable.jifa_one);
//                startActivity(intent);
                break;
            case R.id.iv_type_2:
//                intent.putExtra("drawable", R.drawable.jifa_four);
//                startActivity(intent);
                break;
            case R.id.iv_type_3:
//                intent.putExtra("drawable", R.drawable.jifa_two);
//                startActivity(intent);
                break;
            case R.id.iv_type_4:
//                intent.putExtra("drawable", R.drawable.jifa_three);
//                startActivity(intent);
                break;
            case R.id.iv_type_5:
                intent.putExtra("drawable", R.drawable.jiezihua_chuanba);
                startActivity(intent);
                break;
            case R.id.iv_type_6:
                intent.putExtra("drawable", R.drawable.jiezihua_xu);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    @Override
    public void loadData() {

    }
}

