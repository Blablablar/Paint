package com.example.airal.paint.activity.study.tikuan;

import android.content.Intent;
import android.view.View;

import com.example.airal.paint.R;
import com.example.airal.paint.activity.BaseActivity;
import com.example.airal.paint.activity.study.yinzhang.PreviewActivity;
import com.example.airal.paint.activity.study.yinzhang.YzHomePageActivity;

public class TIkuanHomePageActivity extends BaseActivity {
    @Override
    protected int getContentLayout() {
        return R.layout.activity_js_tikuan_home;
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
        Intent intent = new Intent(TIkuanHomePageActivity.this, PreviewActivity.class);
        switch (v.getId()){
            case R.id.iv_type_1:
                intent.putExtra("drawable", R.drawable.tikuan_content_one);
                startActivity(intent);
                break;
            case R.id.iv_type_2:
                intent.putExtra("drawable", R.drawable.tikuan_content_two);
                startActivity(intent);
                break;
            case R.id.iv_type_3:
                intent.putExtra("drawable", R.drawable.tikuan_content_three);
                startActivity(intent);
                break;
            case R.id.iv_type_4:
                intent.putExtra("drawable", R.drawable.tikuan_content_four);
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

