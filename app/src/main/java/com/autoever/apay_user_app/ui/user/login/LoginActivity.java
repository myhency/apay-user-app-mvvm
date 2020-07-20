package com.autoever.apay_user_app.ui.user.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.ActivityLoginBinding;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.main.MainActivity;
import com.autoever.apay_user_app.ui.splash.SplashActivity;
import com.autoever.apay_user_app.ui.user.register.RegisterActivity;

import javax.inject.Inject;

public class LoginActivity extends BaseActivity<ActivityLoginBinding, LoginViewModel> implements LoginNavigator {

    public static final String TAG = LoginActivity.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;

    private ActivityLoginBinding mActivityLoginBinding;
    private LoginViewModel mLoginViewModel;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public LoginViewModel getViewModel() {
        mLoginViewModel = ViewModelProviders.of(this, factory)
                .get(LoginViewModel.class);
        return mLoginViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("debug", "LoginActivity onCreate");
        mActivityLoginBinding = getViewDataBinding();
        mLoginViewModel.setNavigator(this);

        setup();
    }

    private void setup() {
        mActivityLoginBinding.apayRegister.setOnClickListener(v -> {
            openRegisterActivity();
        });
    }

    @Override
    public void openMainActivity() {

    }

    @Override
    public void openRegisterActivity() {
        Log.d("debug", "MainActivity Open");
        Intent intent = RegisterActivity.newIntent(LoginActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void handleError(Throwable throwable) {

    }
}