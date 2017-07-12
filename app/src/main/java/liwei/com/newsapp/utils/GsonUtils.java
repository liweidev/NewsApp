package liwei.com.newsapp.utils;

import com.google.gson.Gson;

/**
 * Created by liwei on 2017/7/11.
 */

public class GsonUtils {
    private static Gson gson;

    public  static <T> T fromJson(String json,Class<T> clazz){

        if(gson==null){
            synchronized (GsonUtils.class){
                if(gson==null){
                    gson=new Gson();
                }
            }
        }
        return gson.fromJson(json,clazz);
    }



}
