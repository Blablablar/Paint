package com.example.airal.paint.activity.create;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.airal.paint.R;
import com.example.airal.paint.activity.BaseActivity;

/**
 * Created by airal on 2018/5/3.
 */

public class ChooseBgActivity extends BaseActivity implements View.OnClickListener{
    int[]images=new int[]{R.mipmap.backgroud1,R.mipmap.backgroud2,R.mipmap.backgroud3};
    ViewPager viewPager;
    ImageView imageViews[];
    int currentBg=0;

    @Override
    protected int getContentLayout() {
        return R.layout.activity_choose_bg;
    }

    @Override
    public void loadData() {

    }

    @Override
    public void initView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        viewPager=findViewById(R.id.view_pager);
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
