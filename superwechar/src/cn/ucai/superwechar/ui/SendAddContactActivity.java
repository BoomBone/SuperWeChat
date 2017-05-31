package cn.ucai.superwechar.ui;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.easeui.widget.EaseAlertDialog;
import cn.ucai.superwechar.I;
import cn.ucai.superwechar.R;
import cn.ucai.superwechar.SuperWeChatHelper;

/**
 * Created by Administrator on 2017/5/31.
 */

public class SendAddContactActivity extends BaseActivity {
    @BindView(R.id.etMsg)
    EditText etMsg;
    String username;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.send_addcontact_activity);
        ButterKnife.bind(this);
        showLeftBack();
        username = getIntent().getStringExtra(I.User.USER_NAME);
        initView();
    }

    private void initView() {
        etMsg.setText("我是" + SuperWeChatHelper.getInstance().
                getUserProfileManager().getCurrentAppUserInfo().getMUserNick());
        etMsg.selectAll();
    }

    @OnClick(R.id.btn_send)
    public void onViewClicked() {
        if (EMClient.getInstance().getCurrentUser().equals(username)) {
            new EaseAlertDialog(this, R.string.not_add_myself).show();
            return;
        }

        if (SuperWeChatHelper.getInstance().getContactList().containsKey(username)) {
            //let the user know the contact already in your contact list
//            if (EMClient.getInstance().contactManager().getBlackListUsernames().contains(username)) {
//                new EaseAlertDialog(this, R.string.user_already_in_contactlist).show();
//                return;
//            }
            new EaseAlertDialog(this, R.string.This_user_is_already_your_friend).show();
            return;
        }

        progressDialog = new ProgressDialog(this);
        String stri = getResources().getString(R.string.Is_sending_a_request);
        progressDialog.setMessage(stri);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();

        new Thread(new Runnable() {
            public void run() {

                try {
                    //demo use a hardcode reason here, you need let user to input if you like
                    String s = etMsg.getText().toString().trim();
                    EMClient.getInstance().contactManager().addContact(username, s);
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            String s1 = getResources().getString(R.string.send_successful);
                            Toast.makeText(getApplicationContext(), s1, Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });
                } catch (final Exception e) {
                    runOnUiThread(new Runnable() {
                        public void run() {
                            progressDialog.dismiss();
                            String s2 = getResources().getString(R.string.Request_add_buddy_failure);
                            Toast.makeText(getApplicationContext(), s2 + e.getMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        }).start();
    }
}
