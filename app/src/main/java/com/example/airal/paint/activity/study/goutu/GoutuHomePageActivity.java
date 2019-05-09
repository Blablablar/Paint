package com.example.airal.paint.activity.study.goutu;

import android.content.Intent;
import android.view.View;

import com.example.airal.paint.R;
import com.example.airal.paint.activity.BaseActivity;
import com.example.airal.paint.activity.study.tikuan.TIkuanHomePageActivity;
import com.example.airal.paint.activity.study.yinzhang.PreviewActivity;

public class GoutuHomePageActivity extends BaseActivity {
    @Override
    protected int getContentLayout() {
        return R.layout.activity_js_goutu_home;
    }

    @Override
    public void initView() {
        findViewById(R.id.iv_type_1).setOnClickListener(this);
        findViewById(R.id.iv_type_2).setOnClickListener(this);
        findViewById(R.id.iv_type_3).setOnClickListener(this);
        findViewById(R.id.iv_type_4).setOnClickListener(this);
        findViewById(R.id.iv_type_5).setOnClickListener(this);
        findViewById(R.id.iv_type_6).setOnClickListener(this);
        findViewById(R.id.iv_type_7).setOnClickListener(this);
        findViewById(R.id.iv_type_8).setOnClickListener(this);
        findViewById(R.id.iv_type_9).setOnClickListener(this);
        findViewById(R.id.iv_type_10).setOnClickListener(this);
        findViewById(R.id.iv_type_11).setOnClickListener(this);
        findViewById(R.id.iv_type_12).setOnClickListener(this);
        findViewById(R.id.iv_type_13).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = new Intent(GoutuHomePageActivity.this, PreviewActivity.class);
        switch (v.getId()){
            case R.id.iv_type_1:
                intent.putExtra("drawable", R.drawable.goutu_1);
                startActivity(intent);
                break;
            case R.id.iv_type_2:
                intent.putExtra("drawable", R.drawable.goutu_2);
                startActivity(intent);
                break;
            case R.id.iv_type_3:
                intent.putExtra("drawable", R.drawable.goutu_3);
                startActivity(intent);
                break;
            case R.id.iv_type_4:
                intent.putExtra("drawable", R.drawable.goutu_4);
                startActivity(intent);
                break;
            case R.id.iv_type_5:
                intent.putExtra("drawable", R.drawable.goutu_5);
                startActivity(intent);
                break;
            case R.id.iv_type_6:
                intent.putExtra("drawable", R.drawable.goutu_6);
                startActivity(intent);
                break;
            case R.id.iv_type_7:
                intent.putExtra("drawable", R.drawable.goutu_7);
                startActivity(intent);
                break;
            case R.id.iv_type_8:
//                intent.putExtra("drawable", R.drawable.goutu_8);
//                startActivity(intent);
                break;
            case R.id.iv_type_9:
                intent.putExtra("drawable", R.drawable.goutu_9);
                startActivity(intent);
                break;
            case R.id.iv_type_10:
                intent.putExtra("drawable", R.drawable.goutu_10);
                startActivity(intent);
                break;
            case R.id.iv_type_11:
                intent.putExtra("drawable", R.drawable.goutu_11);
                startActivity(intent);
                break;
            case R.id.iv_type_12:
                intent.putExtra("drawable", R.drawable.goutu_12);
                startActivity(intent);
                break;
            case R.id.iv_type_13:
                intent.putExtra("drawable", R.drawable.goutu_13);
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

