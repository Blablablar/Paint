package com.example.airal.paint.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.airal.paint.R;

/**
 * Created by airal on 2018/5/3.
 */

public class ChooseBgActivity extends MyActivity implements View.OnClickListener{
    int[]images=new int[]{R.mipmap.backgroud1,R.mipmap.backgroud2,R.mipmap.backgroud3};
    ViewPager viewPager;
    ImageView imageViews[];
    int currentBg=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_bg);
        viewPager=findViewById(R.id.view_pager);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        initView();
    }

    @Override
    protected void initView() {
        findViewById(R.id.iv_back).setOnClickListener(this);
        PagerAdapter adapter=new PagerAdapter() {
            @Override
            public int getCount() {
                return images.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public void destroyItem(ViewGroup view, int position, Object object) {
                //view.removeView(imageViews[position]);
            }

            @Override
            public Object instantiateItem(ViewGroup view, final int position){
                final ImageView im=new ImageView(getApplicationContext());
                im.setImageResource(images[position]);
                view.addView(im);
                currentBg=position;
                im.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getApplicationContext(),CreatePaintActivity.class);
                        intent.putExtra("position",position);
                        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(ChooseBgActivity.this, im, "bg");
                        startActivity(intent, options.toBundle());

                    }
                });
                return im;
            }
        };
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_back:
                finish();
                break;
            default:
                break;
        }
    }
}