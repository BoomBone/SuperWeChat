package cn.ucai.superwechar.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.ucai.easeui.domain.User;
import cn.ucai.easeui.ui.EaseBaseFragment;
import cn.ucai.superwechar.R;
import cn.ucai.superwechar.SuperWeChatHelper;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends EaseBaseFragment {


    @BindView(R.id.iv_profile_avatar)
    ImageView ivProfileAvatar;
    @BindView(R.id.tv_profile_nickname)
    TextView tvProfileNickname;
    @BindView(R.id.tv_profile_username)
    TextView tvProfileUsername;
    Unbinder unbinder;
    public MeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View layout = inflater.inflate(R.layout.fragment_me, container, false);
        unbinder = ButterKnife.bind(this, layout);
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setUpView() {
        titleBar.setRightImageResource(R.drawable.em_add);
        titleBar.setTitle(getString(R.string.me));
        User user = SuperWeChatHelper.getInstance().getUserProfileManager().getCurrentAppUserInfo();
        if (user!=null){
            tvProfileNickname.setText(user.getMUserNick());
            tvProfileUsername.setText("微信号: " +user.getMUserName());
            if(!TextUtils.isEmpty(user.getAvatar())){
                Glide.with(getContext()).load(user.getAvatar()).placeholder(R.drawable.em_default_avatar).into(ivProfileAvatar);
            }else{
                Glide.with(getContext()).load(R.drawable.em_default_avatar).into(ivProfileAvatar);
            }
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) {
            unbinder.unbind();
        }

    }


    @OnClick({R.id.layout_profile_view, R.id.tv_profile_money, R.id.tv_profile_settings})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.layout_profile_view:
                break;
            case R.id.tv_profile_money:
                break;
            case R.id.tv_profile_settings:
                startActivity(new Intent(getContext(), SettingsActivity.class));
                break;
        }
    }
}