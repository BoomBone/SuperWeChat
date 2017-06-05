package cn.ucai.superwechar.data.net;

import android.content.Context;

import java.io.File;

import cn.ucai.easeui.domain.User;
import cn.ucai.superwechar.I;
import cn.ucai.superwechar.data.OkHttpUtils;

/**
 * Created by Administrator on 2017/5/19.
 */

public class UserModel implements IUserModel {
    @Override
    public void register(Context context, String username, String usernick, String password, OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_REGISTER)
                .addParam(I.User.USER_NAME, username)
                .addParam(I.User.NICK, usernick)
                .addParam(I.User.PASSWORD, password)
                .post()
                .targetClass(String.class)
                .execute(listener);
    }

    @Override
    public void unregister(Context context, String username, OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UNREGISTER)
                .addParam(I.User.USER_NAME, username)
                .targetClass(String.class)
                .execute(listener);
    }

    @Override
    public void login(Context context, String username, String password, OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_LOGIN)
                .addParam(I.User.USER_NAME, username)
                .addParam(I.User.PASSWORD, password)
                .post()
                .targetClass(String.class)
                .execute(listener);
    }

    @Override
    public void loadUserInfo(Context context, String username, OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_FIND_USER)
                .addParam(I.User.USER_NAME, username)
                .targetClass(String.class)
                .execute(listener);
    }

    @Override
    public void updateNick(Context context, String username, String usernick, OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_USER_NICK)
                .addParam(I.User.USER_NAME, username)
                .addParam(I.User.NICK, usernick)
                .targetClass(String.class)
                .execute(listener);
    }

    @Override
    public void updateAvatar(Context context, String username, String avatarType, File file, OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_UPDATE_AVATAR)
                .addParam(I.NAME_OR_HXID, username)
                .addParam(I.AVATAR_TYPE, avatarType)
                .addFile2(file)
                .post()
                .targetClass(String.class)
                .execute(listener);
    }

    @Override
    public void addContact(Context context, String iname, String uname, OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_ADD_CONTACT)
                .addParam(I.Contact.USER_NAME, iname)
                .addParam(I.Contact.CU_NAME, uname)
                .targetClass(String.class)
                .execute(listener);
    }

    @Override
    public void deleteContact(Context context, String iname, String uname, OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DELETE_CONTACT)
                .addParam(I.Contact.USER_NAME, iname)
                .addParam(I.Contact.CU_NAME, uname)
                .targetClass(String.class)
                .execute(listener);
    }

    @Override
    public void loadContact(Context context, String iname, OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_DOWNLOAD_CONTACT_ALL_LIST)
                .addParam(I.Contact.USER_NAME, iname)
                .targetClass(String.class)
                .execute(listener);
    }

    @Override
    public void createGroup(Context context, String hxid, String name, String des, String owner, boolean isPublic, boolean isInviets, File file, OnCompleteListener<String> listener) {
        OkHttpUtils<String> utils = new OkHttpUtils<>(context);
        utils.setRequestUrl(I.REQUEST_CREATE_GROUP)
                .addParam(I.Group.HX_ID, hxid)
                .addParam(I.Group.NAME, name)
                .addParam(I.Group.DESCRIPTION, des)
                .addParam(I.Group.OWNER, owner)
                .addParam(I.Group.IS_PUBLIC, String.valueOf(isPublic))
                .addParam(I.Group.ALLOW_INVITES, String.valueOf(isInviets))
                .addFile2(file)
                .post()
                .targetClass(String.class)
                .execute(listener);
    }
}
