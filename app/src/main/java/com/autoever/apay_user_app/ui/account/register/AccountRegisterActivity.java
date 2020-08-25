package com.autoever.apay_user_app.ui.account.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.ActivityAccountRegisterBinding;
import com.autoever.apay_user_app.ui.account.register.account.BankAccountNumberFragment;
import com.autoever.apay_user_app.ui.account.register.auth.CellPhoneAuthFragment;
import com.autoever.apay_user_app.ui.account.register.bank.BankSelectFragment;
import com.autoever.apay_user_app.ui.account.register.terms.AccountRegisterTermsFragment;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.payment.price.PriceFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class AccountRegisterActivity extends BaseActivity<ActivityAccountRegisterBinding, AccountRegisterViewModel> implements AccountRegisterNavigator, HasSupportFragmentInjector {

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    private ActivityAccountRegisterBinding mActivityAccountRegisterBinding;
    private AccountRegisterViewModel mAccountRegisterViewModel;
    private FragmentManager mFragmentManager;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AccountRegisterActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_account_register;
    }

    @Override
    public AccountRegisterViewModel getViewModel() {
        mAccountRegisterViewModel = ViewModelProviders.of(this, factory)
                .get(AccountRegisterViewModel.class);
        return mAccountRegisterViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAccountRegisterBinding = getViewDataBinding();
        mAccountRegisterViewModel.setNavigator(this);

        setup();

        openAccountRegisterTermsFragment();
    }

    private void setup() {
        this.mFragmentManager = getSupportFragmentManager();
        setSupportActionBar(mActivityAccountRegisterBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mActivityAccountRegisterBinding.toolbarTitle.setText("결제계좌 등록");
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void openAccountRegisterTermsFragment() {
        //약관동의화면으로 이동.
        Log.d("debug", "openAccountRegisterTermsFragment");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, AccountRegisterTermsFragment.newInstance(), AccountRegisterTermsFragment.TAG)
                .addToBackStack(AccountRegisterTermsFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public void openCellPhoneAuthFragment() {
        //휴대전화인증화면으로 이동.
        Log.d("debug", "openCellPhoneAuthFragment");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, CellPhoneAuthFragment.newInstance(), CellPhoneAuthFragment.TAG)
                .addToBackStack(CellPhoneAuthFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public void openBankSelectFragment() {
        //은행선택화면으로 이동.
        Log.d("debug", "openBankSelectFragment");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, BankSelectFragment.newInstance(), BankSelectFragment.TAG)
                .addToBackStack(BankSelectFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public void openBankAccountNumberFragment() {
        //계좌번호입력화면으로 이동.
        Log.d("debug", "openBankAccountNumberFragment");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, BankAccountNumberFragment.newInstance(), BankAccountNumberFragment.TAG)
                .addToBackStack(BankAccountNumberFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public void onFragmentDetached(String tag) {
        super.onFragmentDetached(tag);
        switch (tag) {
            case "AccountRegisterTermsFragment":
                removeFragment(tag);
                openCellPhoneAuthFragment();
                break;
            case "CellPhoneAuthFragment":
                removeFragment(tag);
                openBankSelectFragment();
                break;
            case "BankSelectFragment":
                removeFragment(tag);
                openBankAccountNumberFragment();
                break;
            default:
                break;
        }
    }

    public void removeFragment(String tag) {
        Fragment fragment = mFragmentManager.findFragmentByTag(tag);
        mFragmentManager
                .beginTransaction()
                .disallowAddToBackStack()
                .remove(fragment)
                .commit();
    }
}
