package com.example.airal.paint.activity.study;

import android.content.Intent;
import android.view.View;

import com.example.airal.paint.BaseWebViewActivity;
import com.example.airal.paint.R;
import com.example.airal.paint.activity.BaseActivity;

/**
 * Created by airal on 2018/10/9.
 */

public class GuanShanImgActivity extends BaseActivity {
    @Override
    protected int getContentLayout() {
        return R.layout.activity_jianshan_main;
    }

    @Override
    public void initView() {
        findViewById(R.id.iv_1).setOnClickListener(this);
        findViewById(R.id.iv_2).setOnClickListener(this);
        findViewById(R.id.iv_3).setOnClickListener(this);
        findViewById(R.id.iv_4).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_1:
                Intent intent =new Intent(this, BaseWebViewActivity.class);
                //intent.putExtra("url","https://data-external-dev.oss-cn-shanghai.aliyuncs.com/test_video/2.%E5%85%B3%E4%BB%9D%E3%80%8A%E5%85%B3%E5%B1%B1%E8%A1%8C%E6%97%85%E5%9B%BE%E3%80%8B.mp4?Expires=1539097286&OSSAccessKeyId=TMP.AQH1R0COS2bb0Nh4ARK9x4GGDIkvlq3HsbMEUSZ48WkdUfvAcqA3PjzNyybiADAtAhUA3G0Jrh5Hv1q0-7m91cgZ_7ixZxUCFEnkFjLHJORg1JHy0xP8Ux_1AO-r&Signature=nE8e6cgvrASh5TXQmgb5KDPZWGs%3D");
                intent.putExtra("url","https://m.youku.com/video/id_XMjcxMjI1MjUyMA==.html?spm=a2hzp.8244740.0.0&source=");

                startActivity(intent);
                break;
            case R.id.iv_2:
                startActivity(new Intent(this, JifaActivity.class));
                break;
            case R.id.iv_3:
                startActivity(new Intent(this, GoutuActivity.class));
                break;
            case R.id.iv_4:
                startActivity(new Intent(this, YuansuActivity.class));
                break;
            default:
                break;
        }
    }

    @Override
    public void loadData() {

    }
}
