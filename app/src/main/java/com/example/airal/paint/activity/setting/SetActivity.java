package com.example.airal.paint.activity.setting;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;

import com.example.airal.paint.R;
import com.example.airal.paint.activity.BaseActivity;

/**
 * Created by airal on 2018/4/25.
 */

public class SetActivity extends BaseActivity {

    @Override
    protected int getContentLayout() {
        return R.layout.activity_setting;
    }

    @Override
    public void loadData() {
    }

    @Override
    public void initView(){
        findViewById(R.id.iv_introduce).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetActivity.this, ProjectIntroduceActivity.class));
            }
        });
        findViewById(R.id.iv_team_introduce).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetActivity.this, TeamIntroduceActivity.class));
            }
        });
        findViewById(R.id.iv_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetActivity.this, AboutActivity.class));
            }
        });
        findViewById(R.id.iv_music).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SetActivity.this, ChooseMusicActivity.class));
            }
        });
        SharedPreferences share=getSharedPreferences("music", Activity.MODE_PRIVATE);
        currentPosition=share.getInt("music_index",0);
    }
    int currentPosition=-1;
}
