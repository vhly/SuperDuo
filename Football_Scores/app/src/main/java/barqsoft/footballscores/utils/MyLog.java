package barqsoft.footballscores.utils;

import android.util.Log;
import barqsoft.footballscores.BuildConfig;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/11/5
 * Email: vhly@163.com
 */
public final class MyLog {

    private static final boolean LOG_ON = BuildConfig.DEBUG;

    private MyLog(){

    }

    public static void d(String tag, String msg){
        if(LOG_ON){
            Log.d(tag, msg);
        }
    }

    public static void e(String tag, String msg){
        if(LOG_ON){
            Log.e(tag, msg);
        }
    }

    public static void w(String tag, String msg){
        if(LOG_ON){
            Log.w(tag, msg);
        }
    }

    public static void v(String tag, String msg){
        if(LOG_ON){
            Log.v(tag, msg);
        }
    }

    public static void i(String tag, String msg){
        if(LOG_ON){
            Log.i(tag, msg);
        }
    }

}
