package cn.ucai.superwechar.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import cn.ucai.superwechar.R;
import cn.ucai.superwechar.ui.GuideActivity;
import cn.ucai.superwechar.ui.LoginActivity;
import cn.ucai.superwechar.ui.MainActivity;
import cn.ucai.superwechar.ui.RegisterActivity;
import cn.ucai.superwechar.ui.fragment.SettingsActivity;

/**
 * Created by Administrator on 2017/5/19.
 */

public class MFGT {
    private static void startActivity(Context context,Class clazz){
        context.startActivity(new Intent(context, clazz));
        ((Activity)context).overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }
    private static void startActivity(Context context,Intent intent){
        context.startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }

    public static void gotoLogin(Activity activity) {
        startActivity(activity, LoginActivity.class);
    }
    public static void gotoMain(Activity activity){
        startActivity(activity, MainActivity.class);
    }
    public static void gotoGuide(Activity activity){
        startActivity(activity, GuideActivity.class);
    }

    public static void gotoRegister(Activity activity) {
        startActivity(activity, RegisterActivity.class);
    }

    public static void finish(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.push_right_in,R.anim.push_right_out);
    }

    public static void gotoSetting(Activity activity) {
        startActivity(activity, SettingsActivity.class);
    }
    public static void logout(Activity activity){
        startActivity(activity,new Intent(activity,LoginActivity.class)
        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP));
    }
}
