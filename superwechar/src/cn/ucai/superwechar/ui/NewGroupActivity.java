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

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMGroup;
import com.hyphenate.chat.EMGroupManager.EMGroupOptions;
import com.hyphenate.chat.EMGroupManager.EMGroupStyle;

import cn.ucai.easeui.domain.Group;
import cn.ucai.easeui.widget.EaseAlertDialog;
import cn.ucai.superwechar.I;
import cn.ucai.superwechar.data.Result;
import cn.ucai.superwechar.data.net.IUserModel;
import cn.ucai.superwechar.data.net.OnCompleteListener;
import cn.ucai.superwechar.data.net.UserModel;
import cn.ucai.superwechar.utils.CommonUtils;
import cn.ucai.superwechar.utils.L;
import cn.ucai.superwechar.utils.ResultUtils;

import com.hyphenate.exceptions.HyphenateException;

import java.io.File;

public class NewGroupActivity extends BaseActivity {
    private static final String TAG = "NewGroupActivity";
    private EditText groupNameEditText;
    private ProgressDialog progressDialog;
    private EditText introductionEditText;
    private CheckBox publibCheckBox;
    private CheckBox memberCheckbox;
    private TextView secondTextView;
    IUserModel model;
    File file = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(cn.ucai.superwechar.R.layout.em_activity_new_group);
        super.onCreate(savedInstanceState);
        showLeftBack();
        groupNameEditText = (EditText) findViewById(cn.ucai.superwechar.R.id.edit_group_name);
        introductionEditText = (EditText) findViewById(cn.ucai.superwechar.R.id.edit_group_introduction);
        publibCheckBox = (CheckBox) findViewById(cn.ucai.superwechar.R.id.cb_public);
        memberCheckbox = (CheckBox) findViewById(cn.ucai.superwechar.R.id.cb_member_inviter);
        secondTextView = (TextView) findViewById(cn.ucai.superwechar.R.id.second_desc);

        publibCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    secondTextView.setText(cn.ucai.superwechar.R.string.join_need_owner_approval);
                } else {
                    secondTextView.setText(cn.ucai.superwechar.R.string.Open_group_members_invited);
                }
            }
        });
        setListener();
        model = new UserModel();
    }

    private void setListener() {
        titleBar.getRightLayout().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });
    }


    public void save() {
        String name = groupNameEditText.getText().toString();
        if (TextUtils.isEmpty(name)) {
            new EaseAlertDialog(this, cn.ucai.superwechar.R.string.Group_name_cannot_be_empty).show();
        } else {
            // select from contact list
            startActivityForResult(new Intent(this, GroupPickContactsActivity.class).putExtra("groupName", name), 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        String st1 = getResources().getString(cn.ucai.superwechar.R.string.Is_to_create_a_group_chat);

        if (resultCode == Activity.RESULT_OK) {
            //new group
            progressDialog = new ProgressDialog(this);
            progressDialog.setMessage(st1);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    final String groupName = groupNameEditText.getText().toString().trim();
                    String desc = introductionEditText.getText().toString();
                    String[] members = data.getStringArrayExtra("newmembers");
                    try {
                        EMGroupOptions option = new EMGroupOptions();
                        option.maxUsers = 200;
                        option.inviteNeedConfirm = true;

                        String reason = NewGroupActivity.this.getString(cn.ucai.superwechar.R.string.invite_join_group);
                        reason = EMClient.getInstance().getCurrentUser() + reason + groupName;

                        if (publibCheckBox.isChecked()) {
                            option.style = memberCheckbox.isChecked() ? EMGroupStyle.EMGroupStylePublicJoinNeedApproval : EMGroupStyle.EMGroupStylePublicOpenJoin;
                        } else {
                            option.style = memberCheckbox.isChecked() ? EMGroupStyle.EMGroupStylePrivateMemberCanInvite : EMGroupStyle.EMGroupStylePrivateOnlyOwnerInvite;
                        }
                        EMGroup group = EMClient.getInstance().groupManager().createGroup(groupName, desc, members, reason, option);
                        String hxid = group.getGroupId();
                        createAppGroup(group);

                    } catch (final HyphenateException e) {
                        createFail(e);
                    }

                }
            }).start();
        }
    }
    private void createSuccess(){
        runOnUiThread(new Runnable() {
            public void run() {
                progressDialog.dismiss();
                setResult(Activity.RESULT_OK);
                finish();
            }
        });
    }
    private void createFail(final HyphenateException e){
        final String st2 = getResources().getString(cn.ucai.superwechar.R.string.Failed_to_create_groups);
        runOnUiThread(new Runnable() {
            public void run() {
                progressDialog.dismiss();
                if(e!=null){
                    Toast.makeText(NewGroupActivity.this, st2 + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }else {
                    CommonUtils.showLongToast(st2);
                }
            }
        });
    }

    private void createAppGroup(EMGroup group) {
        model.createGroup(NewGroupActivity.this, group.getGroupId(), group.getGroupName(), group.getDescription(),
                group.getOwner(), group.isPublic(), group.isMemberAllowToInvite(), file,
                new OnCompleteListener<String>() {
                    @Override
                    public void onSuccess(String s) {
                        L.e(TAG,"createAppGroup,onSuccess");
                        boolean isSuccess = false;
                        if(s!=null){
                            Result<Group> result = ResultUtils.getResultFromJson(s, Group.class);
                            if(result!=null&&result.isRetMsg()){
                                isSuccess = true;
                                createSuccess();
                            }
                        }
                        if(!isSuccess){
                            createFail(null);
                        }
                    }

                    @Override
                    public void onError(String error) {
                        createFail(null);
                    }
                });
    }

}
