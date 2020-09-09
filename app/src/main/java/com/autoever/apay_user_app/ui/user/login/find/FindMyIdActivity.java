package com.autoever.apay_user_app.ui.user.login.find;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.ActivityFindMyIdBinding;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.payment.PaymentActivity;

import javax.inject.Inject;

public class FindMyIdActivity extends BaseActivity<ActivityFindMyIdBinding, FindMyIdViewModel> implements FindMyIdNavigator {

    private ActivityFindMyIdBinding mActivityFindMyIdBinding;
    private FindMyIdViewModel mFindMyIdViewModel;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, FindMyIdActivity.class);
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
        return R.layout.activity_find_my_id;
    }

    @Override
    public FindMyIdViewModel getViewModel() {
        mFindMyIdViewModel = ViewModelProviders.of(this, factory)
                .get(FindMyIdViewModel.class);
        return mFindMyIdViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityFindMyIdBinding = getViewDataBinding();
        mFindMyIdViewModel.setNavigator(this);

        setup();
    }

    private void setup() {
    }
}