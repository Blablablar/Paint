package com.example.airal.paint.activity.knowledge.book;

import android.content.Intent;
import android.view.View;

import com.example.airal.paint.R;
import com.example.airal.paint.activity.BaseActivity;
import com.example.airal.paint.activity.knowledge.tool.bi.BiHomePageActivity;
import com.example.airal.paint.activity.knowledge.tool.mo.MoHomePageActivity;
import com.example.airal.paint.activity.knowledge.tool.se.SeHomePageActivity;
import com.example.airal.paint.activity.knowledge.tool.yan.YanHomePageActivity;
import com.example.airal.paint.activity.knowledge.tool.zhi.ZhiHomePageActivity;

public class BookChooseActivity extends BaseActivity {
    @Override
    protected int getContentLayout() {
        return R.layout.activity_knowledge_book_choose;
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
        Class mame;
        String type = getIntent().getStringExtra("type");
        if(type.equals("baihua")){
            mame = BookBaihuaDetailActivity.class;
        }else{
            mame = BookWenyanDetailActivity.class;
        }
        Intent intent = new Intent(this, mame);
        switch (v.getId()){
//            case R.id.iv_type_1:
//                intent.putExtra("chapter",1);
//                startActivity(intent);
//                break;
//            case R.id.iv_type_2:
//                intent.putExtra("chapter",2);
//                startActivity(intent);
//                break;
//            case R.id.iv_type_3:
//                intent.putExtra("chapter",3);
//                startActivity(intent);
//                break;
//            case R.id.iv_type_4:
//                intent.putExtra("chapter",4);
//                startActivity(intent);
//                break;
            case R.id.iv_type_5:
                intent.putExtra("chapter",5);
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
