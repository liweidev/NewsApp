package liwei.com.newsapp.utils;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwei on 2017/7/11.
 */

public class ActivityUtils {

    private static List<Activity> activityList=new ArrayList<>();
    /**
     * 添加activity
     * @param activity
     */
    public synchronized static void addActivity(Activity activity){
        if(!activityList.contains(activity)){
            activityList.add(activity);
        }
    }

    /**
     * 移除一个activity
     * @param activity
     */
    public synchronized static void removeActivity(Activity activity){
        if(activityList.contains(activity)){
            activityList.remove(activity);
            if(!activity.isFinishing()){
                activity.finish();
            }
        }
    }

    /**
     * 移除所有activity
     */
    public synchronized static void removeAllActivity(){
        if(activityList.size()!=0){
            for(Activity activity:activityList){
                activityList.remove(activity);
                if(!activity.isFinishing()){
                    activity.finish();
                }
            }
        }
    }



}
