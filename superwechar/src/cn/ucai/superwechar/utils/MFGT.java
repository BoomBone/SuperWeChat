package cn.ucai.superwechar.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;

import cn.ucai.easeui.domain.User;
import cn.ucai.superwechar.I;
import cn.ucai.superwechar.R;
import cn.ucai.superwechar.ui.AddContactActivity;
import cn.ucai.superwechar.ui.ChatActivity;
import cn.ucai.superwechar.ui.GuideActivity;
import cn.ucai.superwechar.ui.LoginActivity;
import cn.ucai.superwechar.ui.MainActivity;
import cn.ucai.superwechar.ui.PersonalDetailsActivity;
import cn.ucai.superwechar.ui.RegisterActivity;
import cn.ucai.superwechar.ui.SendAddContactActivity;
import cn.ucai.superwechar.ui.SettingsActivity;
import cn.ucai.superwechar.ui.VideoCallActivity;

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
    public static void gotoMain(Activity activity, boolean isChat) {
        startActivity(activity,new Intent(activity,MainActivity.class)
                .putExtra(I.REQUEST_CODE_IS_CHAT,isChat));
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

    public static void gotoProfile(Activity activity, User user) {
        startActivity(activity,new Intent(activity, PersonalDetailsActivity.class)
                .putExtra(I.User.TABLE_NAME,user));
    }
    public static void gotoProfile(Activity activity, String username) {
        startActivity(activity,new Intent(activity, PersonalDetailsActivity.class)
                .putExtra(I.User.USER_NAME,username));
    }

    public static void gotoSendMessage(Activity activity, String username) {
        startActivity(activity,new Intent(activity, SendAddContactActivity.class)
                .putExtra(I.User.USER_NAME,username));
    }

    public static void gotoChat(Activity activity, String username) {
        startActivity(activity,new Intent(activity, ChatActivity.class)
                .putExtra("userId",username));
    }
    public static void gotoVedio(Activity activity, String username) {
        if (!EMClient.getInstance().isConnected())
            Toast.makeText(activity, R.string.not_connect_to_server, Toast.LENGTH_SHORT).show();
        else {
            startActivity(activity,new Intent(activity, VideoCallActivity.class)
                    .putExtra("username", username)
                    .putExtra("isComingCall", false));

        }

    }


}
