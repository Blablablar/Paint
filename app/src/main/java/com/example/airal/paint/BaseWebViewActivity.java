package com.example.airal.paint;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONObject;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * Created by airal on 2018/10/9.
 */

public class BaseWebViewActivity extends AppCompatActivity{
    protected String url = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(LayoutInflater.from(this).inflate(R.layout.activity_webview, null));
        initView();
    }

    void initView() {
        url = getIntent().getStringExtra("url");
        JzvdStd jzvdStd = (JzvdStd) findViewById(R.id.videoplayer);
        jzvdStd.setUp("https://data-external-dev.oss-cn-shanghai.aliyuncs.com/test_video/2.%E5%85%B3%E4%BB%9D%E3%80%8A%E5%85%B3%E5%B1%B1%E8%A1%8C%E6%97%85%E5%9B%BE%E3%80%8B.mp4?Expires=1539097286&OSSAccessKeyId=TMP.AQH1R0COS2bb0Nh4ARK9x4GGDIkvlq3HsbMEUSZ48WkdUfvAcqA3PjzNyybiADAtAhUA3G0Jrh5Hv1q0-7m91cgZ_7ixZxUCFEnkFjLHJORg1JHy0xP8Ux_1AO-r&Signature=nE8e6cgvrASh5TXQmgb5KDPZWGs%3D"
                ,"关山行旅图"
                ,Jzvd.SCREEN_WINDOW_NORMAL
                );
        //jzvdStd.thumbImageView.setImage("http://p.qpic.cn/videoyun/0/2449_43b6f696980311e59ed467f22794e792_1/640");
    }
}
