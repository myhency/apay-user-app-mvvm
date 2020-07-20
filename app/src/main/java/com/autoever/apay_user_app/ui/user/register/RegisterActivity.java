package com.autoever.apay_user_app.ui.user.register;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.ActivityRegisterBinding;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.payment.price.PriceFragment;
import com.autoever.apay_user_app.ui.user.register.terms.TermsOfServiceFragment;

import org.json.JSONObject;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding, RegisterViewModel> implements RegisterNavigator, HasSupportFragmentInjector {

    public static final String TAG = RegisterActivity.class.getSimpleName();

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    ViewModelProviderFactory factory;

    private ActivityRegisterBinding mActivityRegisterBinding;
    private RegisterViewModel mRegisterViewModel;
    private FragmentManager mFragmentManager;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public RegisterViewModel getViewModel() {
        mRegisterViewModel = ViewModelProviders.of(this, factory)
                .get(RegisterViewModel.class);
        return mRegisterViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("debug", "RegisterActivity onCreate");
        mActivityRegisterBinding = getViewDataBinding();
        mRegisterViewModel.setNavigator(this);

        setup();

        openTermsOfServiceFragment();
    }

    private void setup() {
        mFragmentManager = getSupportFragmentManager();
        setSupportActionBar(mActivityRegisterBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setDisplayShowHomeEnabled(false);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }
    }

    @Override
    public void openTermsOfServiceFragment() {
        //서비스 이용 약관 동의 화면으로 이동.
        Log.d("debug", "openTermsOfServiceFragment");
        mActivityRegisterBinding.toolbarTitle.setText("서비스 이용약관 동의");
        mFragmentManager
                .beginTransaction()
//                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.clRootView, TermsOfServiceFragment.newInstance(), TermsOfServiceFragment.TAG)
                .addToBackStack(TermsOfServiceFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public void openRegisterFormFragment() {
        Log.d("debug", "openRegisterFormFragment");
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void onReceivedMessageFromFragment(String tag, JSONObject message) {
        super.onReceivedMessageFromFragment(tag, message);
    }

    @Override
    public void onFragmentDetached(String tag) {
        switch (tag) {
            case "TermsOfServiceFragment":
                Log.d("debug", "TermsOfServiceFragment detached");
                openRegisterFormFragment();
                break;
        }
    }
}