package com.gold.hd.myapplication;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.gold.hd.shixingbanner.mylibrary.ShixingBanner;
import com.gold.hd.shixingbanner.mylibrary.adapter.Standard;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ShixingBanner shixin;
    List<Integer> imags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        shixin = (ShixingBanner) findViewById(R.id.shixin);
        initDatas();
        shixin.setAdapter(new Standard() {
            @Override
            public View getView(int position) {
                ImageView iv = new ImageView(MainActivity.this);
                iv.setImageResource(imags.get(position));
                iv.setScaleType(ImageView.ScaleType.FIT_XY);
                return iv;
            }

            @Override
            public int getCount() {
                return imags.size();
            }
        }, imags.size());
        shixin.setDataStyle(R.drawable.wodedingdan_successed,R.drawable.wodedingdan_failed);
        shixin.setPageTransformer(new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float position) {
                int pageWidth = view.getWidth();

                if (position < -1) { // [-Infinity,-1)
                    // This page is way off-screen to the left.
                    view.setAlpha(0);

                } else if (position <= 0) { // [-1,0]
                    // Use the default slide transition when moving to the left page
                    view.setAlpha(1);
                    view.setTranslationX(0);
                    view.setScaleX(1);
                    view.setScaleY(1);

                } else if (position <= 1) { // (0,1]
                    // Fade the page out.
                    view.setAlpha(1 - position);

                    // Counteract the default slide transition
                    view.setTranslationX(pageWidth * -position);

                    // Scale the page down (between MIN_SCALE and 1)
                    float scaleFactor = 10
                            + (1 - 10) * (1 - Math.abs(position));
                    view.setScaleX(scaleFactor);
                    view.setScaleY(scaleFactor);

                } else { // (1,+Infinity]
                    // This page is way off-screen to the right.
                    view.setAlpha(0);
                }
            }
        });
//        shixin.setAdapter(new LoopAdapter() {
//            @Override
//            public View getView(int position) {
//                ImageView iv = new ImageView(MainActivity.this);
//                iv.setImageResource(imags.get(position));
//                iv.setScaleType(ImageView.ScaleType.FIT_XY);
//                return iv;
//            }
//
//            @Override
//            public int all_position() {
//                return imags.size();
//            }
//        }, imags.size());

    }

    private void initDatas() {
        imags = new ArrayList();
        imags.add(R.drawable.img1);
        imags.add(R.drawable.img2);
        imags.add(R.drawable.img3);
    }
}
