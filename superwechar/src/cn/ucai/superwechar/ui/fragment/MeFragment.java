package cn.ucai.superwechar.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.ucai.easeui.ui.EaseBaseFragment;
import cn.ucai.superwechar.R;
import cn.ucai.superwechar.ui.MainActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class MeFragment extends EaseBaseFragment {


    @BindView(R.id.txt_title)
    TextView txtTitle;
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
        txtTitle.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void setUpView() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if(unbinder!=null){
            unbinder.unbind();
        }

    }

    @OnClick(R.id.tv_profile_settings)
    public void onSettingClicked() {
        startActivity(new Intent(getContext(),SettingsActivity.class));
    }
}
