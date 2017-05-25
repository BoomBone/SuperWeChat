package cn.ucai.superwechar.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.ucai.easeui.domain.User;
import cn.ucai.easeui.utils.EaseUserUtils;
import cn.ucai.superwechar.I;
import cn.ucai.superwechar.R;
import cn.ucai.superwechar.SuperWeChatHelper;


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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_personal_details);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
        initData();
        showLeftBack();
    }

    private void initData() {
        user = (User) getIntent().getSerializableExtra(I.User.TABLE_NAME);
        if(user!=null){
            showInfo();
        }else{
            finish();
        }
    }

    private void showInfo() {
        tvUserinfoName.setText(user.getMUserName());
        tvUserinfoNick.setText(user.getMUserNick());
        EaseUserUtils.setAppUserAvatar(PersonalDetailsActivity.this,user,profileImage);
        showButton(SuperWeChatHelper.getInstance().getContactList().containsKey(user.getMUserName()));
    }

    private void showButton(boolean isContact) {
        btnAddContact.setVisibility(isContact ? View.GONE : View.VISIBLE);
        btnSendMsg.setVisibility(isContact ? View.VISIBLE : View.GONE);
        btnSendVideo.setVisibility(isContact?View.VISIBLE:View.GONE);
    }
}