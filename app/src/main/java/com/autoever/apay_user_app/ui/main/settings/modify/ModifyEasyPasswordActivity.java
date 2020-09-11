package com.autoever.apay_user_app.ui.main.settings.modify;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.ActivityModifyEasyPasswordBinding;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.main.settings.register.RegisterEasyPasswordActivity;
import com.autoever.apay_user_app.ui.user.register.password.PasswordFragment;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class ModifyEasyPasswordActivity extends BaseActivity<ActivityModifyEasyPasswordBinding, ModifyEasyPasswordViewModel>
        implements ModifyEasyPasswordNavigator, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    ViewModelProviderFactory factory;

    private ActivityModifyEasyPasswordBinding mActivityModifyEasyPasswordBinding;
    private ModifyEasyPasswordViewModel mModifyEasyPasswordViewModel;
    private FragmentManager mFragmentManager;
    private JSONObject password;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, ModifyEasyPasswordActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_modify_easy_password;
    }

    @Override
    public ModifyEasyPasswordViewModel getViewModel() {
        mModifyEasyPasswordViewModel = ViewModelProviders.of(this, factory)
                .get(ModifyEasyPasswordViewModel.class);
        return mModifyEasyPasswordViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityModifyEasyPasswordBinding = getViewDataBinding();

        setup();
    }

    private void setup() {
        mFragmentManager = getSupportFragmentManager();
        setSupportActionBar(mActivityModifyEasyPasswordBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public void openPasswordFragment() {
        Log.d("debug", "openPasswordFragment");
        mActivityModifyEasyPasswordBinding.toolbarTitle.setText("기존 간편비밀번호 입력");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, PasswordFragment.newInstance("check"), PasswordFragment.TAG)
                .addToBackStack(PasswordFragment.TAG)
                .commit();
    }

    @Override
    public void onReceivedMessageFromFragment(String tag, JSONObject message) {
        super.onReceivedMessageFromFragment(tag, message);
        switch (tag) {
            case "PasswordFragment":
                //건너뛰기 버튼을 눌렀을 때는 종료한다.
                if(message.has("whatToDo")) {
                    //여기서 다이얼로그를 띄워준다.
                    // custom dialog
                    final Dialog dialog = new Dialog(this);
                    dialog.setContentView(R.layout.finish_register_easypassword_dialog);

                    Button okButton = dialog.findViewById(R.id.ok_button);

                    okButton.setOnClickListener(v1 -> {
                        dialog.dismiss();
                        finish();
                    });

                    dialog.show();
                }

                //password 가 null 일 때는 확인을 띄우고 password 에 message 를 저장한다.
                if (password == null) {
                    mActivityModifyEasyPasswordBinding.toolbarTitle.setText("간편비밀번호 확인");
                    mFragmentManager
                            .beginTransaction()
                            .replace(R.id.clRootView, PasswordFragment.newInstance("check"), PasswordFragment.TAG)
                            .addToBackStack(PasswordFragment.TAG)
                            .commitAllowingStateLoss();
                    password = message;
                    return;
                }

                //password 가 메세지랑 같을 때는 간편비번을 preps 에 저장하고 종료한다.
                if (isPasswordValid(message) && password != null) {
                    try {
                        mModifyEasyPasswordViewModel.setEasyPassword((password.getString("password")));
                        //여기서 다이얼로그를 띄워준다.
                        // custom dialog
                        final Dialog dialog = new Dialog(this);
                        dialog.setContentView(R.layout.complete_register_easypassword_dialog);

                        Button okButton = dialog.findViewById(R.id.ok_button);

                        okButton.setOnClickListener(v1 -> {
                            dialog.dismiss();
                            finish();
                        });

                        dialog.show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(
                            ModifyEasyPasswordActivity.this,
                            "패스워드가 일치하지 않습니다.",
                            Toast.LENGTH_SHORT
                    ).show();
                    mActivityModifyEasyPasswordBinding.toolbarTitle.setText("간편비밀번호 등록");
                    mFragmentManager
                            .beginTransaction()
                            .replace(R.id.clRootView, PasswordFragment.newInstance("register_new"), PasswordFragment.TAG)
                            .addToBackStack(PasswordFragment.TAG)
                            .commitAllowingStateLoss();
                    password = null;
                }
                break;
        }
    }

    private boolean isPasswordValid(JSONObject message) {
        try {
            if (password.getString("password").equals(message.getString("password"))) {
                Log.d("debug", "패스워드 등록:" + password.getString("password"));
                Log.d("debug", "패스워드 확인:" + message.getString("password"));
                return true;
            } else {
                Log.d("debug", "패스워드 등록:" + password.getString("password"));
                Log.d("debug", "패스워드 확인:" + message.getString("password"));
                return false;
            }
        } catch (JSONException e) {
            return false;
//            e.printStackTrace();
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}