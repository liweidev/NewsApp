package liwei.com.newsapp.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import liwei.com.newsapp.app.NewsApplication;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by liwei on 2017/7/11.
 */

public class HttpUtils {

    public static void sendHttpRequest(final String url, final Callback callback){
        if(!NetUtils.isNetAvailable()){
            ToastUtils.showMessage("当前网络不可用", NewsApplication.getmContext());
            DialogUtils.dismiss();
            return;
        }else{
            ExecutorService service= Executors.newFixedThreadPool(4);
            service.execute(new Runnable() {
                @Override
                public void run() {
                    OkHttpClient client=new OkHttpClient();
                    Request request=new Request.Builder().url(url).method("GET",null).build();
                    client.newCall(request).enqueue(callback);
                }
            });
        }
    }

}
