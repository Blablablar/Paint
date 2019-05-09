package com.example.airal.paint.activity.study.yinzhang;

import android.content.Intent;
import android.view.View;

import com.example.airal.paint.R;
import com.example.airal.paint.activity.BaseActivity;
import com.example.airal.paint.activity.knowledge.type.BiaoxianActivity;
import com.example.airal.paint.activity.knowledge.type.ColorActivity;
import com.example.airal.paint.activity.knowledge.type.GuohuaLeiBieActivity;
import com.example.airal.paint.activity.knowledge.type.JiFaActivity;
import com.example.airal.paint.activity.knowledge.type.TicaiActivity;

public class YzHomePageActivity extends BaseActivity {
    @Override
    protected int getContentLayout() {
        return R.layout.activity_js_yz_home;
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
        Intent intent = new Intent(YzHomePageActivity.this, PreviewActivity.class);
        switch (v.getId()){
            case R.id.iv_type_1:
                intent.putExtra("drawable", R.drawable.yz_fangfa);
                startActivity(intent);
                break;
            case R.id.iv_type_2:
                intent.putExtra("drawable", R.drawable.yz_wz);
                startActivity(intent);
                break;
            case R.id.iv_type_3:
                intent.putExtra("drawable", R.drawable.yz_daxiao);
                startActivity(intent);
                break;
            case R.id.iv_type_4:
                intent.putExtra("drawable", R.drawable.yz_content);
                startActivity(intent);
                break;
            case R.id.iv_type_5:
                intent.putExtra("drawable", R.drawable.yz_type);
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
