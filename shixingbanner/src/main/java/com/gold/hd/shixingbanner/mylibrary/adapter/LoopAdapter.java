package com.gold.hd.shixingbanner.mylibrary.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by cbbc on 2017/1/19.
 * 无限循环
 */
public abstract class LoopAdapter extends PagerAdapter {

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = getView(position%all_position());
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    public abstract View getView(int position);

    public abstract int all_position();
}
