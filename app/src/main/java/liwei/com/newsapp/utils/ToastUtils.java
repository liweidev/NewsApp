package liwei.com.newsapp.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by liwei on 2017/7/11.
 */

public class ToastUtils {
    private static Toast toast;

    public static void showMessage(String messag, Context context){
        if(toast==null){
            synchronized (ToastUtils.class){
                if(toast==null){
                    toast=Toast.makeText(context,messag,Toast.LENGTH_SHORT);
                }
            }
        }else{
            toast.setText(messag);
        }
        toast.show();
    }

}
