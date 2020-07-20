package com.autoever.apay_user_app.ui.charge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.androidnetworking.error.ANError;
import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.ActivityChargeBinding;
import com.autoever.apay_user_app.ui.auth.AuthFragment;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.charge.amount.AmountFragment;
import com.autoever.apay_user_app.ui.payment.price.PriceFragment;
import com.autoever.apay_user_app.utils.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class ChargeActivity extends BaseActivity<ActivityChargeBinding, ChargeViewModel> implements ChargeNavigator, HasSupportFragmentInjector {
    
    public static final String TAG = ChargeActivity.class.getSimpleName();

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    ViewModelProviderFactory factory;
    
    private ActivityChargeBinding mActivityChargeBinding;
    private ChargeViewModel mChargeViewModel;
    private FragmentManager mFragmentManager;

    private int amount = 0;
    
    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, ChargeActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_charge;
    }

    @Override
    public ChargeViewModel getViewModel() {
        mChargeViewModel = ViewModelProviders.of(this, factory)
                .get(ChargeViewModel.class);
        return mChargeViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("debug", "ChargeActivity onCreate");
        mActivityChargeBinding = getViewDataBinding();
        mChargeViewModel.setNavigator(this);
        
        setup();

        openAmountFragment();
    }

    private void setup() {
        this.mFragmentManager = getSupportFragmentManager();
        setSupportActionBar(mActivityChargeBinding.toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mActivityChargeBinding.toolbarTitle.setText("카드 충전");
        }
    }

    @Override
    public void openAmountFragment() {
        //금액입력화면으로 이동.
        Log.d("debug", "openAmountInput");
        mFragmentManager
                .beginTransaction()
//                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.clRootView, AmountFragment.newInstance(), AmountFragment.TAG)
                .addToBackStack(AmountFragment.TAG)
                .commit();
    }

    @Override
    public void handleError(Throwable throwable) {
        ANError anError = (ANError) throwable;
        Log.d("debug", "anError.getErrorBody():" + anError.getErrorBody());
        Log.d("debug", "throwable message: " + throwable.getMessage());
    }

    @Override
    public void openAuthFragment() {
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, AuthFragment.newInstance(), AuthFragment.TAG)
                .addToBackStack(AuthFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public void doChargeReady() {
        Log.d("debug", "doChargeReady");
        mChargeViewModel.doChargeReady(amount);
    }

    @Override
    public void doChargeDo() {
        Log.d("debug", "doChargeDo");
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void onFragmentDetached(String tag) {
//        super.onFragmentDetached(tag);
        Log.d("debug", "onFragmentDetached: " + tag);

        switch (tag) {
            case "AmountFragment":
                openAuthFragment();
                break;
            case "AuthFragment":
                doChargeReady();
                break;
        }
    }

    @Override
    public void onReceivedMessageFromFragment(String tag, JSONObject message) {
        Log.d("debug", "onReceivedMessageFromFragment: " + message);

        try {
            switch (tag) {
                case "AmountFragment":
                    amount = CommonUtils.parseToInt(message.getString("amount"));
                    break;
                default:
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}