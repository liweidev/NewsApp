package liwei.com.newsapp.utils;

import android.util.Log;

/**
 * Created by liwei on 2017/7/11.
 */

public class LogUtils {
    private static final boolean ISDEBUG=true;
    public static void d(String msg){
        if(ISDEBUG){
            Log.d("TAG",msg);
        }
    }
}
