/**
 * Copyright (C) 2016 Hyphenate Inc. All rights reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package cn.ucai.superwechar.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.EMError;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.ucai.superwechar.I;
import cn.ucai.superwechar.R;
import cn.ucai.superwechar.SuperWeChatHelper;
import cn.ucai.superwechar.data.Result;
import cn.ucai.superwechar.data.net.IUserModel;
import cn.ucai.superwechar.data.net.OnCompleteListener;
import cn.ucai.superwechar.data.net.UserModel;
import cn.ucai.superwechar.utils.MD5;
import cn.ucai.superwechar.utils.MFGT;
import cn.ucai.superwechar.utils.ResultUtils;

/**
 * register screen
 */
public class RegisterActivity extends BaseActivity {
    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.et_username)
    EditText userNameEditText;
    @BindView(R.id.et_nickname)
    EditText etNickname;
    @BindView(R.id.et_password)
    EditText passwordEditText;
    @BindView(R.id.et_confirm_password)
    EditText confirmPwdEditText;
    Unbinder unbinder;
    String username,nickname,password;
    ProgressDialog pd;
    IUserModel model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.em_activity_register);
        unbinder = ButterKnife.bind(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }


    @OnClick(R.id.img_back)
    public void onBackClicked() {
        MFGT.finish(RegisterActivity.this);
    }

    @OnClick(R.id.btn_register)
    public void onRegisterClicked() {
        initDialog();
        if(checkedInput()){
            registerApp();
        }
    }
    private void initDialog(){
        pd = new ProgressDialog(RegisterActivity.this);
        pd.setMessage(getString(R.string.Is_the_registered));
        pd.show();
    }
    private  void dismissDialog(){
        pd.dismiss();
    }

    private void registerApp() {
        model = new UserModel();
        model.register(RegisterActivity.this, username, nickname, MD5.getMessageDigest(password), new OnCompleteListener<String>() {
            @Override
            public void onSuccess(String s) {
              /*  if(s!=null){
                    Result result = ResultUtils.getResultFromJson(s, User.class);
                    if(result!=null){
                        if(result.getRetCode()==I.MSG_REGISTER_USERNAME_EXISTS){
                            mUsername.requestFocus();
                            mUsername.setError(getString(R.string.register_fail_exists));
                        }
                        else if(result.getRetCode()==I.MSG_REGISTER_FAIL){
                            mUsername.requestFocus();
                            mUsername.setError(getString(R.string.register_fail));
                        }else{
                            registerSuccess();
                        }
                    }

                }else{
                    dismissDialog();
                }*/
                registerEMApp();

            }

            @Override
            public void onError(String error) {
                dismissDialog();
            }
        });
    }
    private void unRegister() {
        model = new UserModel();
        model.unregister(RegisterActivity.this, username, new OnCompleteListener<String>() {
            @Override
            public void onSuccess(String result) {

            }

            @Override
            public void onError(String error) {

            }
        });
    }
    private void registerEMApp(){
        new Thread(new Runnable() {
            public void run() {
                try {
                    // call method in SDK
                    EMClient.getInstance().createAccount(username, password);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            if (!RegisterActivity.this.isFinishing())
                                dismissDialog();
                            // save current user
                            SuperWeChatHelper.getInstance().setCurrentUserName(username);
                            Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registered_successfully), Toast.LENGTH_SHORT).show();
                            MFGT.gotoLogin(RegisterActivity.this);
                            MFGT.finish(RegisterActivity.this);
                        }
                    });
                } catch (final HyphenateException e) {
                    unRegister();
                    runOnUiThread(new Runnable() {
                        public void run() {
                            if (!RegisterActivity.this.isFinishing())
                                dismissDialog();
                            int errorCode = e.getErrorCode();
                            if (errorCode == EMError.NETWORK_ERROR) {
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.network_anomalies), Toast.LENGTH_SHORT).show();
                            } else if (errorCode == EMError.USER_ALREADY_EXIST) {
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.User_already_exists), Toast.LENGTH_SHORT).show();
                            } else if (errorCode == EMError.USER_AUTHENTICATION_FAILED) {
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.registration_failed_without_permission), Toast.LENGTH_SHORT).show();
                            } else if (errorCode == EMError.USER_ILLEGAL_ARGUMENT) {
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.illegal_user_name), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registration_failed), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        }).start();
    }



    private boolean checkedInput() {
        username = userNameEditText.getText().toString().trim();
        nickname = etNickname.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
        String confirm_pwd = confirmPwdEditText.getText().toString().trim();
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, getResources().getString(R.string.User_name_cannot_be_empty), Toast.LENGTH_SHORT).show();
            userNameEditText.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(nickname)) {
            Toast.makeText(this, getResources().getString(R.string.toast_nick_not_isnull), Toast.LENGTH_SHORT).show();
            etNickname.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, getResources().getString(R.string.Password_cannot_be_empty), Toast.LENGTH_SHORT).show();
            passwordEditText.requestFocus();
            return false;
        } else if (TextUtils.isEmpty(confirm_pwd)) {
            Toast.makeText(this, getResources().getString(R.string.Confirm_password_cannot_be_empty), Toast.LENGTH_SHORT).show();
            confirmPwdEditText.requestFocus();
            return false;
        } else if (!password.equals(confirm_pwd)) {
            Toast.makeText(this, getResources().getString(R.string.Two_input_password), Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
