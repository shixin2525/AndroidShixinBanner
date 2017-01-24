package com.gold.hd.shixingbanner.mylibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.gold.hd.shixingbanner.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cbbc on 2017/1/19.
 */
public class ShixingBanner extends LinearLayout implements ViewPager.OnPageChangeListener {
    boolean shixin_loop;
    boolean shixin_tag;
    View layout;
    ViewPager my_vp;
    LinearLayout ling_layout;
    int count;
    List<ImageView> xiaoyuandian;
    private AdSwitchTask adSwitchTask;

    public ShixingBanner(Context context) {
        super(context);
    }

    public ShixingBanner(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public ShixingBanner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.shixin);
        shixin_loop = typedArray.getBoolean(R.styleable.shixin_loop, false);//自动翻页
        shixin_tag = typedArray.getBoolean(R.styleable.shixin_tag, false);//是否有小圆点
        autoTurningTime = typedArray.getInteger(R.styleable.shixin_fanye_sudu, 0);//翻页速度

        typedArray.recycle();
        layout = LayoutInflater.from(context).inflate(
                R.layout.shixin_layout, this, true);
        ling_layout = (LinearLayout) layout.findViewById(R.id.ling_layout);
        my_vp = (ViewPager) layout.findViewById(R.id.my_vp);
        my_vp.addOnPageChangeListener(this);
        adSwitchTask = new AdSwitchTask(this);
        if (shixin_loop) {
            postDelayed(adSwitchTask, autoTurningTime);
        }
    }

    /**
     * 自定义翻页动画效果
     *
     * @param transformer
     * @return
     */
    public ShixingBanner setPageTransformer(ViewPager.PageTransformer transformer) {
        my_vp.setPageTransformer(true, transformer);
        return this;
    }

    int option=0;
    int nooption=0;

    /**
     * 自定义小圆点的图片
     */
    public void setDataStyle(int option, int nooption) {
        this.option = option;
        this.nooption = nooption;
    }

    public void setyuandian_nooption(ImageView iv) {
        if (nooption == 0) {
            iv.setImageResource(R.drawable.no_option);
        } else {
            iv.setImageResource(nooption);
        }
    }

    public void setyuandian_option(ImageView iv) {
        if (option == 0) {
            iv.setImageResource(R.drawable.option);
        } else {
            iv.setImageResource(option);
        }
    }

    /**
     * 设置默认的小圆点
     */
    private void initData() {
        xiaoyuandian = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ImageView iv = new ImageView(getContext());
            setyuandian_nooption(iv);
            xiaoyuandian.add(iv);
            if (i == 0) {
               setyuandian_option(iv);
            }
            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            lp.width = 30;
            lp.height = 30;
            lp.setMargins(0, 0, 30, 0);
            iv.setLayoutParams(lp);
            ling_layout.addView(iv);
        }
    }

    //触碰控件的时候，翻页应该停止，离开的时候如果之前是开启了翻页的话则重新启动翻页
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_CANCEL || action == MotionEvent.ACTION_OUTSIDE) {
            // 开始翻页
            if (shixin_loop) {
                removeCallbacks(adSwitchTask);
            } else {
                postDelayed(adSwitchTask, autoTurningTime);
            }
            postDelayed(adSwitchTask, autoTurningTime);
        } else if (action == MotionEvent.ACTION_DOWN) {
            if (shixin_loop) {
                removeCallbacks(adSwitchTask);
            }

        }
        return super.dispatchTouchEvent(ev);
    }


    public void setAdapter(PagerAdapter adapter, int count) {
        my_vp.setAdapter(adapter);
        this.count = count;
        if (shixin_tag) {
            initData();
        }
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (shixin_tag) {
            for (int i = 0; i < count; i++) {
                setyuandian_nooption(xiaoyuandian.get(i));
                if (i == position % count) {
                    setyuandian_option(xiaoyuandian.get(i));
                }
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private long autoTurningTime;//翻页的速度

    /**
     * 翻页的配置
     */
    static class AdSwitchTask implements Runnable {

        private final WeakReference<ShixingBanner> reference;

        AdSwitchTask(ShixingBanner convenientBanner) {
            this.reference = new WeakReference<ShixingBanner>(convenientBanner);
        }

        @Override
        public void run() {
            ShixingBanner convenientBanner = reference.get();

            if (convenientBanner != null) {
                if (convenientBanner.my_vp != null && convenientBanner.shixin_loop) {
                    int page = convenientBanner.my_vp.getCurrentItem() + 1;
                    convenientBanner.my_vp.setCurrentItem(page);
                    convenientBanner.postDelayed(convenientBanner.adSwitchTask, convenientBanner.autoTurningTime);
                }
            }
        }
    }

}
