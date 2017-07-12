package liwei.com.newsapp.app;

import android.app.Application;
import android.content.Context;

import com.lzy.okhttputils.OkHttpUtils;

/**
 * Created by liwei on 2017/7/11.
 */

public class NewsApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext=getApplicationContext();
        OkHttpUtils.init(this);
    }

    public synchronized static Context getmContext(){
        if(null!=mContext){
            return mContext;
        }
        return null;
    }



}
