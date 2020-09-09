package com.autoever.apay_user_app.ui.user.login.init;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.ActivityInitMyPasswordBinding;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.user.login.find.FindMyIdActivity;

import javax.inject.Inject;

public class InitMyPasswordActivity extends BaseActivity<ActivityInitMyPasswordBinding, InitMyPasswordViewModel> implements InitMyPasswordNavigator {

    private ActivityInitMyPasswordBinding mActivityInitMyPasswordBinding;
    private InitMyPasswordViewModel mInitMyPasswordViewModel;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, InitMyPasswordActivity.class);
        return intent;
    }

    @Inject
    ViewModelProviderFactory factory;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_init_my_password;
    }

    @Override
    public InitMyPasswordViewModel getViewModel() {
        mInitMyPasswordViewModel = ViewModelProviders.of(this, factory)
                .get(InitMyPasswordViewModel.class);
        return mInitMyPasswordViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityInitMyPasswordBinding = getViewDataBinding();
        setup();
    }

    private void setup() {
    }
}