package com.autoever.apay_user_app.ui.charge;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.data.model.api.ChargeDoResponse;
import com.autoever.apay_user_app.data.model.api.ChargeReadyResponse;
import com.autoever.apay_user_app.databinding.ActivityChargeBinding;
import com.autoever.apay_user_app.ui.auth.AuthFragment;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.charge.amount.AmountFragment;
import com.autoever.apay_user_app.ui.charge.fail.ChargeFailFragment;
import com.autoever.apay_user_app.ui.charge.receipt.ChargeReceiptFragment;
import com.autoever.apay_user_app.utils.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import retrofit2.HttpException;

public class ChargeActivity extends BaseActivity<ActivityChargeBinding, ChargeViewModel> implements ChargeNavigator, HasSupportFragmentInjector {

    public static final String TAG = ChargeActivity.class.getSimpleName();

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    ViewModelProviderFactory factory;

    private ActivityChargeBinding mActivityChargeBinding;
    private ChargeViewModel mChargeViewModel;
    private FragmentManager mFragmentManager;

    private Long amount = 0L;

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
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mActivityChargeBinding.toolbarTitle.setText("머니 충전");
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
        HttpException httpException = (HttpException) throwable;

        switch (httpException.code()) {
            default:
                openChargeFailFragment();
                break;
        }
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
    public void doChargeDo(ChargeReadyResponse chargeReadyResponse) {
        Log.d("debug", "doChargeDo");
        mChargeViewModel.doChargeDo(chargeReadyResponse);
    }

    @Override
    public void openChargeReceiptFragment(ChargeDoResponse chargeDoResponse) {
        //충전금액 영수증 화면으로 이동.
        Log.d("debug", "openChargeReceiptFragment");
        mActivityChargeBinding.toolbar.setVisibility(View.INVISIBLE);
        mActivityChargeBinding.appBarLayout.setBackgroundColor(getResources().getColor(R.color.receiptBackgroundColor, null));
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, ChargeReceiptFragment.newInstance(chargeDoResponse), ChargeReceiptFragment.TAG)
                .addToBackStack(ChargeReceiptFragment.TAG)
                .commit();
    }

    @Override
    public void openChargeFailFragment() {
        //충전실패 화면으로 이동.
        Log.d("debug", "openChargeFailFragment");
        mActivityChargeBinding.toolbar.setVisibility(View.INVISIBLE);
        mActivityChargeBinding.appBarLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite, null));
        mFragmentManager
                .beginTransaction()
                .replace(R.id.clRootView, ChargeFailFragment.newInstance(), ChargeFailFragment.TAG)
                .addToBackStack(ChargeFailFragment.TAG)
                .commit();
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
            case "ChargeReceiptFragment":
            case "ChargeFailFragment":
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onReceivedMessageFromFragment(String tag, JSONObject message) {
        Log.d("debug", "onReceivedMessageFromFragment: " + message);

        try {
            switch (tag) {
                case "AmountFragment":
                    amount = (long) CommonUtils.parseToInt(message.getString("amount"));
                    break;
                default:
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("debug", "onOptionsItemSelected:" + mFragmentManager.getBackStackEntryCount());
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                List<Fragment> fragments = mFragmentManager.getFragments();
                String fragmentTag = fragments.get(fragments.size() - 1).getTag();
                switch (fragmentTag) {
                    case "AmountFragment":
                        finish();
                    case "AuthFragment":
                        removeFragment(fragmentTag);
                        break;
                    default:
                        break;
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
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