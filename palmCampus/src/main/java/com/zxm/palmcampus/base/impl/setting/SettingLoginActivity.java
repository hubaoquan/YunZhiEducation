package com.zxm.palmcampus.base.impl.setting;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.zxm.palmcampus.Bean.User;
import com.zxm.palmcampus.R;
import com.zxm.palmcampus.activity.MainActivity;
import com.zxm.palmcampus.base.impl.HomePager;
import com.zxm.palmcampus.global.GlobalContants;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class SettingLoginActivity extends Activity implements OnClickListener {

    @BindView(R.id.ivOwner)
    ImageView ivOwner;
    @BindView(R.id.user)
    AutoCompleteTextView user;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.cbrp)
    CheckBox cbrp;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    @BindView(R.id.btn_register)
    Button btnRegister;
    private Button btn_register;
    String userName;
    String passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Bmob.initialize(this, GlobalContants.BMOB_APP_ID);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
        SharedPreferences sharedPreferences = getSharedPreferences("config",MODE_PRIVATE);
        userName = sharedPreferences.getString("phone","");
        passWord = sharedPreferences.getString("password","");
        user.setText(userName);
        password.setText(passWord);
        loginBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_register:
                startActivity(new Intent(this, SettingRegisterActivity.class));
                break;
            case R.id.loginBtn:
                //取得控件内容
                userName = user.getText().toString();
                passWord = password.getText().toString();
                if (cbrp.isChecked())
                {
                    SharedPreferences userCount = getSharedPreferences("config", MODE_PRIVATE);
                    SharedPreferences.Editor editor = userCount.edit();
                    editor.putString("phone",user.getText().toString());
                    editor.putString("password",password.getText().toString());
                    editor.commit();
                }else {
                    SharedPreferences userCount = getSharedPreferences("config", MODE_PRIVATE);
                    SharedPreferences.Editor editor = userCount.edit();
                    editor.putString("phone","");
                    editor.putString("password","");
                    editor.commit();
                }
                User  myuser = new User();
                myuser.setUsername(user.getText().toString());
                myuser.setMobilePhoneNumber(user.getText().toString());
                myuser.setPassword(passWord);
                myuser.login(new SaveListener<User>() {
                    @Override
                    public void done(User user, BmobException e) {
                        if (e==null)
                        {
                            Toast.makeText(SettingLoginActivity.this,"登录成功！",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(SettingLoginActivity.this, MainActivity.class));

                        }
                        else
                        {

                            Log.e("", "done: "+e.toString() );
                            Toast.makeText(SettingLoginActivity.this,"登录失败,请检查用户名密码！"+userName+"  "+passWord,Toast.LENGTH_LONG).show();

                        }
                    }
                });
                break;

            default:
                break;
        }
    }

}
