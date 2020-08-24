package com.autoever.apay_user_app.ui.account;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.ActivityAccountBinding;
import com.autoever.apay_user_app.ui.account.list.AccountListFragment;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.payment.price.PriceFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class AccountActivity extends BaseActivity<ActivityAccountBinding, AccountViewModel> implements AccountNavigator, HasSupportFragmentInjector {

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;
    
    private ActivityAccountBinding mActivityAccountBinding;
    private AccountViewModel mAccountViewModel;
    private FragmentManager mFragmentManager;
    
    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AccountActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_account;
    }

    @Override
    public AccountViewModel getViewModel() {
        mAccountViewModel = ViewModelProviders.of(this, factory)
                .get(AccountViewModel.class);
        return mAccountViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAccountBinding = getViewDataBinding();
        mAccountViewModel.setNavigator(this);
        
        setup();

        openAccountListFragment();
    }

    private void setup() {
        this.mFragmentManager = getSupportFragmentManager();
        setSupportActionBar(mActivityAccountBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mActivityAccountBinding.toolbarTitle.setText("계좌관리");
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void openAccountListFragment() {
        //금액입력화면으로 이동.
        Log.d("debug", "openAccountListFragment");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, AccountListFragment.newInstance(), AccountListFragment.TAG)
                .addToBackStack(AccountListFragment.TAG)
                .commitAllowingStateLoss();
    }
}