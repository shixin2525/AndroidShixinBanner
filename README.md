# AndroidShixinBanner 

功能包括自动翻页，触摸时停止翻页，可添加viewpager的滑动时的动画，自定义小圆点样式，可设置自己的图片,几行代码轻松实现一个功能完善的轮播图，更多功能后续完善


###API说明
   xml中：
   
   
        shixin:loop="true"   //是否开启自动翻页
        
        
        shixin:tag="true"     //是否开启指示点
        
        
        shixin:fanye_sudu="2000"  //翻页的速度
        
        
   代码中:
   
   
    public void setDataStyle(int option, int nooption);//设置指示器图片，没有则使用自带的默认样式
    
    
    public ShixingBanner setPageTransformer(ViewPager.PageTransformer transformer);//添加viewpager翻页动画
    
    
    public void setAdapter(PagerAdapter adapter, int count) {  //（来自源码）其实也就是再去给源码中的viewpager设置Adapter
        my_vp.setAdapter(adapter);
        this.count = count;
        if (shixin_tag) {
            initData();
        }
    }
    
    
### 使用: 



 依赖导入:
 allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
   
   
   dependencies {
	        compile 'com.github.shixin2525:AndroidShixinBanner:1.0'
	}
          
    <com.gold.hd.shixingbanner.mylibrary.ShixingBanner
        android:id="@+id/shixin"
        shixin:loop="true"
        shixin:tag="true"
        shixin:fanye_sudu="2000"
        android:layout_width="match_parent"
        android:layout_height="200dp" />

  源码中内置里两个适配器:
1.Standard普通的，没有无限循环        
2.LoopAdapter无限循环
           可根据自己的需要设置不同的适配器  


     ShixingBanner shixin;
     shixin = (ShixingBanner) findViewById(R.id.shixin);
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
        
    
    自定义指示点的图片
    
  shixin.setDataStyle(R.drawable.wodedingdan_successed, R.drawable.wodedingdan_failed);      
  
  添加设置viewpager翻动时的动画
  
  shixin.setPageTransformer();
  
        
###效果展示
![Image text](https://raw.githubusercontent.com/shixin2525/AndroidShixinBanner/master/shixingbanner/src/main/res/img-folder/asf.gif)




###博客地址:http://blog.csdn.net/mingtianguohou100
