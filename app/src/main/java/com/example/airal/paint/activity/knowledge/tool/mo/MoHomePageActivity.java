package com.example.airal.paint.activity.knowledge.tool.mo;

import android.content.Intent;
import android.view.View;

import com.example.airal.paint.R;
import com.example.airal.paint.activity.BaseActivity;

public class MoHomePageActivity extends BaseActivity {
    @Override
    protected int getContentLayout() {
        return R.layout.activity_knowledge_tool_mo;
    }


    @Override
    public void initView() {
        findViewById(R.id.iv_type_1).setOnClickListener(this);
        findViewById(R.id.iv_type_2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_type_1:
                startActivity(new Intent(this, MoXuangouActivity.class));
                break;
            case R.id.iv_type_2:
                startActivity(new Intent(this, MoTypeActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void loadData() {

    }
}
