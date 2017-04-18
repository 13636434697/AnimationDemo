package com.xu.animationdemo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.nineoldandroids.view.ViewHelper;
import com.nineoldandroids.view.ViewPropertyAnimator;

public class MainActivity extends Activity {
    private TextView text;
    int textHeight;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text);

        //获取text高度也可以
//		text.measure(0, 0);

        //全局布局监听
        text.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onGlobalLayout() {
                //取消下全局布局监听
                text.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //在这里获取高度，任何一个控件。。。
                textHeight = text.getHeight();
                Log.e("tag", "textHeight: "+textHeight);

                //一开始需要先让text往上移动它自身的高度
//				ViewPropertyAnimator.animate(text).translationYBy(-textHeight).setDuration(0).start();
                ViewHelper.setTranslationY(text, -textHeight);
                //getTop是获取父view的距离，参考的坐标系是父view的坐标系，属性没有更改还是原来的数值
                Log.e("tag", "top:"+text.getTop());

                //获取到值之后在执行这个方法来设置
                //再以动画的形式慢慢滚动下拉
                //参数：对哪个对象执行动画，缩放到多少，执行多少事件，延迟执行，
                ViewPropertyAnimator.animate(text).translationYBy(textHeight).setDuration(500).setStartDelay(1000).start();

                //view的位置还是没有变
//				new Handler().postDelayed(new Runnable() {
//					@Override
//					public void run() {
//						Log.e("tag", "top:"+text.getTop());
//					}
//				}, 2000);
            }
        });
        //之前做动画的方法
        //参数：对哪个对象执行动画，改变什么属性，改变多少可变参数，view会变宽
        //这样写太麻烦，而且运行在3.0以上，就诞生了nineoldandroids
//		ObjectAnimator animator = ObjectAnimator.ofFloat(text, "scaleX", 1.5f);
//		animator.setDuration(1000);
//		animator.setStartDelay(1000);//延时1秒开启动画
//		animator.start();

        //参数：对哪个对象执行动画，缩放到多少，执行多少事件，延迟执行，
        //使用NineOldAndroid中的ViewPropertyAnimator
//		ViewPropertyAnimator.animate(text).scaleX(1.2f).setDuration(500)
//		.setInterpolator(new OvershootInterpolator())
//		.setStartDelay(1000)
//		.start();
//		ViewPropertyAnimator.animate(text).scaleY(1.2f).setDuration(500)
        //这个是弹性插值器
//		.setInterpolator(new OvershootInterpolator())
//		.setStartDelay(1000)
//		.start();

        //平移动画
//		ViewPropertyAnimator.animate(text).translationX(40).setDuration(500)
        //多样插值器
////		.setInterpolator(new OvershootInterpolator())//超过一点再回来
////		.setInterpolator(new BounceInterpolator())//像球落地一样
//		.setInterpolator(new CycleInterpolator(4))//左右来回抖动
//		.setStartDelay(1000)
//		.start();

    }


}