package cn.ucai.superwechar.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import cn.ucai.superwechar.R;
import cn.ucai.superwechar.runtimepermissions.PermissionsManager;
import cn.ucai.easeui.ui.EaseChatFragment;
import cn.ucai.superwechar.ui.fragment.ChatFragment;
import cn.ucai.superwechar.utils.L;
import cn.ucai.superwechar.utils.MFGT;

import com.hyphenate.util.EasyUtils;

/**
 *
 *
 */
public class ChatActivity extends BaseActivity{
    private static final String TAG = "ChatActivity";
    public static ChatActivity activityInstance;
    private EaseChatFragment chatFragment;
    String toChatUsername;

    @Override
    protected void onCreate(Bundle arg0) {
        super.onCreate(arg0);
        setContentView(R.layout.em_activity_chat);
        activityInstance = this;
        //get user id or group id
        toChatUsername = getIntent().getExtras().getString("userId");
        L.e(TAG,"toChatUsername="+toChatUsername);
        //use EaseChatFratFragment
        chatFragment = new ChatFragment();
        //pass parameters to chat fragment
        chatFragment.setArguments(getIntent().getExtras());
        getSupportFragmentManager().beginTransaction().add(R.id.container, chatFragment).commit();
        
    }
    
    @Override
    protected void onDestroy() {
        super.onDestroy();
        activityInstance = null;
    }
    
    @Override
    protected void onNewIntent(Intent intent) {
    	// make sure only one chat activity is opened
        String username = intent.getStringExtra("userId");
        L.e(TAG,"username="+username);
        if (toChatUsername.equals(username))
            super.onNewIntent(intent);
        else {
            finish();
            startActivity(intent);
        }

    }
    
    @Override
    public void onBackPressed() {
        chatFragment.onBackPressed();
        MFGT.gotoMain(ChatActivity.this,true);
    }
    
    public String getToChatUsername(){
        return toChatUsername;
    }

    @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
        @NonNull int[] grantResults) {
        PermissionsManager.getInstance().notifyPermissionsChange(permissions, grantResults);
    }
}
