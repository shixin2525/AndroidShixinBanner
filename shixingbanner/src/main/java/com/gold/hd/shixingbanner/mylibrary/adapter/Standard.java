package com.gold.hd.shixingbanner.mylibrary.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cbbc on 2017/1/19.
 */
public abstract class Standard extends PagerAdapter {


    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View v = getView(position);
        container.addView(v);
        return v;
    }

    public abstract View getView(int position);
}
