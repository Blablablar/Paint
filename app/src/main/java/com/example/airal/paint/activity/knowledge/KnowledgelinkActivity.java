package com.example.airal.paint.activity.knowledge;

import android.content.Intent;
import android.view.View;

import com.example.airal.paint.R;
import com.example.airal.paint.activity.BaseActivity;
import com.example.airal.paint.activity.knowledge.type.GuohuaLeiBieActivity;

/**
 * Created by airal on 2018/5/11.
 */

public class KnowledgelinkActivity extends BaseActivity {
    @Override
    protected int getContentLayout() {
        return R.layout.activity_knowledge_link;
    }

    @Override
    public void initView() {
        findViewById(R.id.iv_zhishi_1).setOnClickListener(this);
        findViewById(R.id.iv_zhishi_2).setOnClickListener(this);
        findViewById(R.id.iv_zhishi_3).setOnClickListener(this);
        findViewById(R.id.iv_zhishi_4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_zhishi_1:
                //startActivity(new Intent(KnowledgelinkActivity.this, GushuActivity.class));
                break;
            case R.id.iv_zhishi_2:
                //startActivity(new Intent(KnowledgelinkActivity.this, GuohuaToolActivity.class));
                break;
            case R.id.iv_zhishi_3:
                //startActivity(new Intent(KnowledgelinkActivity.this, GuohuaHuafuActivity.class));
                break;
            case R.id.iv_zhishi_4:
                startActivity(new Intent(KnowledgelinkActivity.this, GuohuaLeiBieActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void loadData() {

    }
}
