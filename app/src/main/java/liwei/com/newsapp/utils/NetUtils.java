package liwei.com.newsapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import liwei.com.newsapp.app.NewsApplication;

/**
 * Created by liwei on 2017/7/11.
 */

public class NetUtils {
    public static boolean isNetAvailable(){
        ConnectivityManager manager= (ConnectivityManager) NewsApplication.getmContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo infos = manager.getActiveNetworkInfo();
        if(infos!=null && infos.isAvailable()){
            return true;
        }else{
            return false;
        }
    }
}
