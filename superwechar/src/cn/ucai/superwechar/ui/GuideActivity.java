package cn.ucai.superwechar.ui;

import android.os.Bundle;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.ucai.superwechar.R;
import cn.ucai.superwechar.utils.MFGT;

/**
 * Created by Administrator on 2017/5/19.
 */

public class GuideActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.welcome_activity);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login, R.id.btn_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                MFGT.gotoLogin(GuideActivity.this);
                break;
            case R.id.btn_register:
                MFGT.gotoRegister(GuideActivity.this);
                break;
        }
    }
}
