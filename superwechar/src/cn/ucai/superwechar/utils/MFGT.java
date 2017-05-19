package cn.ucai.superwechar.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import cn.ucai.superwechar.R;
import cn.ucai.superwechar.ui.LoginActivity;
import cn.ucai.superwechar.ui.MainActivity;

/**
 * Created by Administrator on 2017/5/19.
 */

public class MFGT {
    private static void startActivity(Context context,Class clazz){
        context.startActivity(new Intent(context, clazz));
        ((Activity)context).overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }

    public static void gotoLogin(Activity activity) {
        startActivity(activity, LoginActivity.class);
    }
    public static void gotoMain(Activity activity){
        startActivity(activity, MainActivity.class);
    }

}
