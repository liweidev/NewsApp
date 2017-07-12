package liwei.com.newsapp.activitys;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import liwei.com.newsapp.utils.ActivityUtils;
import liwei.com.newsapp.utils.LogUtils;

/**
 * Created by liwei on 2017/7/11.
 */
//所有父类都继承ParentActivity
public abstract class ParentActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(onLayout());
        ButterKnife.bind(this);
        initView();
        initData();
        ActivityUtils.addActivity(this);
        LogUtils.d(getClass().getSimpleName());
    }

    public abstract int  onLayout();
    public abstract void initView();
    public abstract void initData();

    /**
     * 启动activity伴随动画效果
     */
    public void startActivityWithAnimation(){
        overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
    }

    /**
     * 所有activity跳转界面都使用此方法
     * @param activity
     * @param isFinish
     */
    public void startActivity(Activity activity,Class<?> activityClass,boolean isFinish){
        Intent intent=new Intent(activity,activityClass);
        startActivity(intent);
        startActivityWithAnimation();
        if(isFinish){
            activity.finish();
            ActivityUtils.removeActivity(activity);
        }
    }

    /**
     * 所有activity跳转界面都使用此方法
     * @param isFinish
     */
    public void startActivity(Activity activity,String action,boolean isFinish){
        Intent intent=new Intent(action);
        startActivity(intent);
        startActivityWithAnimation();
        if(isFinish){
            activity.finish();
            ActivityUtils.removeActivity(activity);
        }
    }

}
