package liwei.com.newsapp.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by liwei on 2017/7/11.
 */

public class DialogUtils {
    private static ProgressDialog dialog;

    /**
     * 显示Dialog
     * @param message
     */
    public static void showDialog(String message, Context context){
        dialog=new ProgressDialog(context);
        dialog.setMessage(message);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    public static void dismiss(){
        if(dialog!=null){
            dialog.dismiss();
            dialog=null;
        }
    }



}
