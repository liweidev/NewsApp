package liwei.com.newsapp.activitys;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.widget.TextView;

import butterknife.BindView;
import liwei.com.newsapp.R;
import liwei.com.newsapp.utils.ActivityUtils;

public class WelcomeActivity extends ParentActivity {


    //新闻APP标题
    @BindView(R.id.textView)
    TextView textView;
    private Activity activity;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case Integer.MAX_VALUE:
                    startAnimator();
                    break;
            }
        }
    };
    @Override
    public int onLayout() {
        return R.layout.activity_welcome;
    }

    @Override
    public void initView() {
        activity=this;
    }

    /**
     * 初始化动画
     */
    private void initAnimator() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessage(Integer.MAX_VALUE);
            }
        },500);
    }

    /**
     * 启动动画
     */
    public void startAnimator(){
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height=displayMetrics.heightPixels;
        ObjectAnimator animator = ObjectAnimator.ofFloat(textView, "translationY", 0f, height/2);
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                startActivity(activity,MainActivity.class,true);
            }
        });
        animator.setInterpolator(new BounceInterpolator());
        animator.setDuration(2000);
        animator.start();
    }
    /**
     * 初始化ActionBar
     */
    private void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (null != actionBar && Build.VERSION.SDK_INT >= 21) {
            actionBar.hide();
            View decorView = getWindow().getDecorView();
            int options = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            getWindow().setStatusBarColor(Color.TRANSPARENT);
            decorView.setSystemUiVisibility(options);
        }
    }

    @Override
    public void initData() {
        initActionBar();
        initAnimator();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityUtils.removeActivity(this);
    }
}
