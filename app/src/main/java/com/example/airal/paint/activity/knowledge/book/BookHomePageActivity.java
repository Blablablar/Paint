package com.example.airal.paint.activity.knowledge.book;

import android.content.Intent;
import android.view.View;

import com.example.airal.paint.R;
import com.example.airal.paint.activity.BaseActivity;
import com.example.airal.paint.activity.knowledge.tool.bi.BiHomePageActivity;
import com.example.airal.paint.activity.knowledge.tool.bi.BiTypeActivity;
import com.example.airal.paint.activity.knowledge.tool.bi.BiXuangouActivity;
import com.example.airal.paint.activity.knowledge.tool.mo.MoHomePageActivity;
import com.example.airal.paint.activity.knowledge.tool.se.SeHomePageActivity;
import com.example.airal.paint.activity.knowledge.tool.yan.YanHomePageActivity;
import com.example.airal.paint.activity.knowledge.tool.zhi.ZhiHomePageActivity;

public class BookHomePageActivity extends BaseActivity {
    @Override
    protected int getContentLayout() {
        return R.layout.activity_knowledge_book;
    }


    @Override
    public void initView() {
        findViewById(R.id.iv_type_1).setOnClickListener(this);
        findViewById(R.id.iv_type_2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        Intent intent = new Intent(this, BookChooseActivity.class);
        switch (v.getId()){
            case R.id.iv_type_1:
                intent.putExtra("type","baihua");
                startActivity(intent);
                break;
            case R.id.iv_type_2:
                intent.putExtra("type","wenyan");
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
