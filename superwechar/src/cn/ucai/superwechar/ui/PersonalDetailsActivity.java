package cn.ucai.superwechar.ui;

import android.app.ProgressDialog;
import android.app.admin.SystemUpdatePolicy;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.easeui.domain.User;
import cn.ucai.easeui.utils.EaseUserUtils;
import cn.ucai.superwechar.I;
import cn.ucai.superwechar.R;
import cn.ucai.superwechar.SuperWeChatHelper;
import cn.ucai.superwechar.data.Result;
import cn.ucai.superwechar.data.net.IUserModel;
import cn.ucai.superwechar.data.net.OnCompleteListener;
import cn.ucai.superwechar.data.net.UserModel;
import cn.ucai.superwechar.utils.MFGT;
import cn.ucai.superwechar.utils.ResultUtils;


public class PersonalDetailsActivity extends BaseActivity {

    @BindView(R.id.profile_image)
    ImageView profileImage;
    @BindView(R.id.tv_userinfo_nick)
    TextView tvUserinfoNick;
    @BindView(R.id.tv_userinfo_name)
    TextView tvUserinfoName;
    @BindView(R.id.btn_add_contact)
    Button btnAddContact;
    @BindView(R.id.btn_send_msg)
    Button btnSendMsg;
    @BindView(R.id.btn_send_video)
    Button btnSendVideo;
    User user = null;
    IUserModel model;
    private ProgressDialog progressDialog;
    boolean isContact=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_personal_details);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
        initData();
        showLeftBack();
        model = new UserModel();
    }

    private void initData() {
        String username = getIntent().getStringExtra(I.User.USER_NAME);
        if (username != null) {
            //根据用户名获取好友
            user = SuperWeChatHelper.getInstance().getAppContactList().get(username);
        }
        if (user == null) {
            user = (User) getIntent().getSerializableExtra(I.User.TABLE_NAME);
        }
        //点击自己的头像获取的自己用户名
        if(user==null&&username.equals(EMClient.getInstance().getCurrentUser())){
            user = SuperWeChatHelper.getInstance().getUserProfileManager().getCurrentAppUserInfo();
        }
        if (user != null) {
            showInfo();
        } else {
            finish();
        }
    }

    private void showInfo() {
        tvUserinfoName.setText(user.getMUserName());
        tvUserinfoNick.setText(user.getMUserNick());
        EaseUserUtils.setAppUserAvatar(PersonalDetailsActivity.this, user, profileImage);
//        showButton(SuperWeChatHelper.getInstance().getAppContactList().containsKey(user.getMUserName()));
        showButton(isContact);
    }

    private void showButton(boolean isContact) {
        if(!user.getMUserName().equals(EMClient.getInstance().getCurrentUser())){
            btnAddContact.setVisibility(isContact ? View.GONE : View.VISIBLE);
            btnSendMsg.setVisibility(isContact ? View.VISIBLE : View.GONE);
            btnSendVideo.setVisibility(isContact ? View.VISIBLE : View.GONE);
        }

    }

    @OnClick(R.id.btn_add_contact)
    public void sendAddContactMsg() {
        MFGT.gotoSendMessage(PersonalDetailsActivity.this, user.getMUserName());
    }

    @OnClick(R.id.btn_send_msg)
    public void onBtnSendMsgClicked() {
        MFGT.gotoChat(PersonalDetailsActivity.this, user.getMUserName());
    }

    @OnClick(R.id.btn_send_video)
    public void onBtnSendVideoClicked() {
        MFGT.gotoVedio(PersonalDetailsActivity.this, user.getMUserName());
    }

//    public void addContact(View view) {
//        if (EMClient.getInstance().getCurrentUser().equals(user.getMUserName())) {
//            new EaseAlertDialog(this, R.string.not_add_myself).show();
//            return;
//        }
//
//        if (SuperWeChatHelper.getInstance().getContactList().containsKey(user.getMUserName())) {
//            //let the user know the contact already in your contact list
//            if (EMClient.getInstance().contactManager().getBlackListUsernames().contains(user.getMUserName())) {
//                new EaseAlertDialog(this, R.string.user_already_in_contactlist).show();
//                return;
//            }
//            new EaseAlertDialog(this, R.string.This_user_is_already_your_friend).show();
//            return;
//        }
//
//        progressDialog = new ProgressDialog(this);
//        String stri = getResources().getString(R.string.Is_sending_a_request);
//        progressDialog.setMessage(stri);
//        progressDialog.setCanceledOnTouchOutside(false);
//        progressDialog.show();
//
//        new Thread(new Runnable() {
//            public void run() {
//
//                try {
//                    //demo use a hardcode reason here, you need let user to input if you like
//                    String s = getResources().getString(R.string.Add_a_friend);
//                    EMClient.getInstance().contactManager().addContact(user.getMUserName(), s);
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            progressDialog.dismiss();
//                            String s1 = getResources().getString(R.string.send_successful);
//                            Toast.makeText(getApplicationContext(), s1, Toast.LENGTH_LONG).show();
//                        }
//                    });
//                } catch (final Exception e) {
//                    runOnUiThread(new Runnable() {
//                        public void run() {
//                            progressDialog.dismiss();
//                            String s2 = getResources().getString(R.string.Request_add_buddy_failure);
//                            Toast.makeText(getApplicationContext(), s2 + e.getMessage(), Toast.LENGTH_LONG).show();
//                        }
//                    });
//                }
//            }
//        }).start();
//    }
}
