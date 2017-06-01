package cn.ucai.superwechar.data.net;

import android.content.Context;

import java.io.File;

import cn.ucai.easeui.domain.User;
import cn.ucai.superwechar.data.OkHttpUtils;

/**
 * Created by Administrator on 2017/5/19.
 */

public interface IUserModel  {
    void register(Context context, String username, String usernick, String password,
                  OnCompleteListener<String> listener);

    void unregister(Context context, String username, OnCompleteListener<String> listener);

    void login(Context context, String username, String password, OnCompleteListener<String> listener);

    void loadUserInfo(Context context, String username, OnCompleteListener<String> listener);

    void updateNick(Context context, String username, String usernick, OnCompleteListener<String> listener);

    void updateAvatar(Context context, String username, String avatarType,
                      File file, OnCompleteListener<String> listener);

    void addContact(Context context, String iname, String uname, OnCompleteListener<String> listener);
    void deleteContact(Context context, String iname, String uname, OnCompleteListener<String> listener);


}
