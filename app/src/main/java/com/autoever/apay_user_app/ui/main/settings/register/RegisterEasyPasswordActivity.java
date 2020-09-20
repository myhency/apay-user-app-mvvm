package com.autoever.apay_user_app.ui.main.settings.register;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.ActivityRegisterEasyPasswordBinding;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.user.register.password.PasswordFragment;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class RegisterEasyPasswordActivity extends BaseActivity<ActivityRegisterEasyPasswordBinding, RegisterEasyPasswordViewModel>
        implements RegisterEasyPasswordNavigator, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    ViewModelProviderFactory factory;

    private ActivityRegisterEasyPasswordBinding mActivityRegisterEasyPasswordBinding;
    private RegisterEasyPasswordViewModel mRegisterEasyPasswordViewModel;
    private FragmentManager mFragmentManager;
    private JSONObject password;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, RegisterEasyPasswordActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register_easy_password;
    }

    @Override
    public RegisterEasyPasswordViewModel getViewModel() {
        mRegisterEasyPasswordViewModel = ViewModelProviders.of(this, factory)
                .get(RegisterEasyPasswordViewModel.class);
        return mRegisterEasyPasswordViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityRegisterEasyPasswordBinding = getViewDataBinding();

        setup();

        Log.d("debug", "easyPass: " + mRegisterEasyPasswordViewModel.getEasyPassword());

        if(mRegisterEasyPasswordViewModel.getEasyPassword() == null) {
            openPasswordFragment();
        } else { //등록된 간편비밀번호가 존재하면 다이얼로그를 띄워 간편비밀번호 재등록 화면으로 이동하게 한다.
            //여기서 다이얼로그를 띄워준다.
            // custom dialog
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.easy_password_registered_dialog);

            Button okButton = dialog.findViewById(R.id.ok_button);

            okButton.setOnClickListener(v1 -> {
                dialog.dismiss();
                finish();
            });

            dialog.show();
        }
    }

    private void setup() {
        mFragmentManager = getSupportFragmentManager();
        setSupportActionBar(mActivityRegisterEasyPasswordBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public void openPasswordFragment() {
        Log.d("debug", "openPasswordFragment");
        mActivityRegisterEasyPasswordBinding.toolbarTitle.setText("간편비밀번호 등록");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, PasswordFragment.newInstance("register"), PasswordFragment.TAG)
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
                    mActivityRegisterEasyPasswordBinding.toolbarTitle.setText("간편비밀번호 확인");
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
                        mRegisterEasyPasswordViewModel.setEasyPassword((password.getString("password")));
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
                            RegisterEasyPasswordActivity.this,
                            "패스워드가 일치하지 않습니다.",
                            Toast.LENGTH_SHORT
                    ).show();
                    mActivityRegisterEasyPasswordBinding.toolbarTitle.setText("간편비밀번호 등록");
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