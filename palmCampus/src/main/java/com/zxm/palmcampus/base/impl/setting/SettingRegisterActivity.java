package com.zxm.palmcampus.base.impl.setting;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.zxm.palmcampus.Bean.User;
import com.zxm.palmcampus.R;
import com.zxm.palmcampus.global.GlobalContants;


import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobSMS;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;

public class SettingRegisterActivity extends Activity implements View.OnClickListener{
    @BindView(R.id.phone_num)
    EditText phoneNum;
    @BindView(R.id.btn_send_sms)
    Button btnSendSms;
    @BindView(R.id.sms_code)
    EditText smsCode;
    @BindView(R.id.edit_nicknmae)
    EditText editNicknmae;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.edit_password_confirm)
    EditText editPasswordConfirm;
    @BindView(R.id.btnregister)
    Button btnregister;

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //初始化Bmob
        Bmob.initialize(this, GlobalContants.BMOB_APP_ID);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        btnSendSms.setOnClickListener(this);
        btnregister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_send_sms:
                Log.e("sed sms", "onClick: ");
                BmobSMS.requestSMSCode(phoneNum.getText().toString(), "云智教育", new QueryListener<Integer>() {

                    @Override
                    public void done(Integer smsId, BmobException ex) {
                        //验证码发送成功
                        if (ex == null) {
                            Toast.makeText(SettingRegisterActivity.this, "验证码发送成功！", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(SettingRegisterActivity.this, "验证码发送失败，请确保手机号码正确无误！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
            case R.id.btnregister:
                user = new User();
                user.setMobilePhoneNumber(phoneNum.getText().toString());
                user.setUsername(phoneNum.getText().toString());
                if (editPassword.getText().toString().equals(editPasswordConfirm.getText().toString()))
                {
                    user.setPassword(editPassword.getText().toString());
                }
                else {
                    Toast.makeText(SettingRegisterActivity.this, "密码不一致！", Toast.LENGTH_SHORT).show();
                    break;
                }
                user.signOrLogin(smsCode.getText().toString(), new SaveListener<User>() {

                    @Override
                    public void done(User user, BmobException e) {
                        if(e==null){
                            Toast.makeText(SettingRegisterActivity.this, "注册成功！请登录！", Toast.LENGTH_SHORT).show();
                            SharedPreferences userCount = getSharedPreferences("config", MODE_PRIVATE);
                            SharedPreferences.Editor editor = userCount.edit();
                            editor.putString("phone",phoneNum.getText().toString());
                            editor.putString("password",editPassword.getText().toString());
                            editor.commit();
                            startActivity(new Intent(SettingRegisterActivity.this,SettingLoginActivity.class));
                        }else{
                            Toast.makeText(SettingRegisterActivity.this, "注册失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                break;
            default:
                break;
        }
    }
}
