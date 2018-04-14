// Generated code from Butter Knife. Do not modify!
package com.zxm.palmcampus.base.impl.setting;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.zxm.palmcampus.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingRegisterActivity_ViewBinding implements Unbinder {
  private SettingRegisterActivity target;

  @UiThread
  public SettingRegisterActivity_ViewBinding(SettingRegisterActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingRegisterActivity_ViewBinding(SettingRegisterActivity target, View source) {
    this.target = target;

    target.phoneNum = Utils.findRequiredViewAsType(source, R.id.phone_num, "field 'phoneNum'", EditText.class);
    target.btnSendSms = Utils.findRequiredViewAsType(source, R.id.btn_send_sms, "field 'btnSendSms'", Button.class);
    target.smsCode = Utils.findRequiredViewAsType(source, R.id.sms_code, "field 'smsCode'", EditText.class);
    target.editNicknmae = Utils.findRequiredViewAsType(source, R.id.edit_nicknmae, "field 'editNicknmae'", EditText.class);
    target.editPassword = Utils.findRequiredViewAsType(source, R.id.edit_password, "field 'editPassword'", EditText.class);
    target.editPasswordConfirm = Utils.findRequiredViewAsType(source, R.id.edit_password_confirm, "field 'editPasswordConfirm'", EditText.class);
    target.btnregister = Utils.findRequiredViewAsType(source, R.id.btnregister, "field 'btnregister'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SettingRegisterActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.phoneNum = null;
    target.btnSendSms = null;
    target.smsCode = null;
    target.editNicknmae = null;
    target.editPassword = null;
    target.editPasswordConfirm = null;
    target.btnregister = null;
  }
}
