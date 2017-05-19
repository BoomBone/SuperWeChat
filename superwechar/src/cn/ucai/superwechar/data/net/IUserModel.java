package cn.ucai.superwechar.data.net;

import android.content.Context;

/**
 * Created by Administrator on 2017/5/19.
 */

public interface IUserModel  {
    void register(Context context, String username, String usernick, String password,
                  OnCompleteListener<String> listener);
    void unregister(Context context, String username, OnCompleteListener<String> listener);

}
