// Generated code from Butter Knife. Do not modify!
package com.zxm.palmcampus.base.impl.setting;

import android.support.annotation.CallSuper;
import android.support.annotation.UiThread;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.zxm.palmcampus.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingLoginActivity_ViewBinding implements Unbinder {
  private SettingLoginActivity target;

  @UiThread
  public SettingLoginActivity_ViewBinding(SettingLoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingLoginActivity_ViewBinding(SettingLoginActivity target, View source) {
    this.target = target;

    target.ivOwner = Utils.findRequiredViewAsType(source, R.id.ivOwner, "field 'ivOwner'", ImageView.class);
    target.user = Utils.findRequiredViewAsType(source, R.id.user, "field 'user'", AutoCompleteTextView.class);
    target.password = Utils.findRequiredViewAsType(source, R.id.password, "field 'password'", EditText.class);
    target.cbrp = Utils.findRequiredViewAsType(source, R.id.cbrp, "field 'cbrp'", CheckBox.class);
    target.loginBtn = Utils.findRequiredViewAsType(source, R.id.loginBtn, "field 'loginBtn'", Button.class);
    target.btnRegister = Utils.findRequiredViewAsType(source, R.id.btn_register, "field 'btnRegister'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    SettingLoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivOwner = null;
    target.user = null;
    target.password = null;
    target.cbrp = null;
    target.loginBtn = null;
    target.btnRegister = null;
  }
}
