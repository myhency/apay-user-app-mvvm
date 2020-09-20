package com.autoever.apay_user_app.ui.user.mypage;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.ActivityMyPageBinding;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.user.mypage.password.ChangeLoginPasswordActivity;

import javax.inject.Inject;

public class MyPageActivity extends BaseActivity<ActivityMyPageBinding, MyPageViewModel> implements MyPageNavigator {

    @Inject
    ViewModelProviderFactory factory;

    private ActivityMyPageBinding mActivityMyPageBinding;
    private MyPageViewModel mMyPageViewModel;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MyPageActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_my_page;
    }

    @Override
    public MyPageViewModel getViewModel() {
        mMyPageViewModel = ViewModelProviders.of(this, factory)
                .get(MyPageViewModel.class);
        return mMyPageViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMyPageBinding = getViewDataBinding();
        
        setup();
    }

    private void setup() {
        mActivityMyPageBinding.changeLoginPasswordLayout.setOnClickListener(v -> {
            Log.d("debug", "openChangeLoginPasswordActivity");
            Intent intent = ChangeLoginPasswordActivity.newIntent(MyPageActivity.this);
            startActivity(intent);
        });
    }
}