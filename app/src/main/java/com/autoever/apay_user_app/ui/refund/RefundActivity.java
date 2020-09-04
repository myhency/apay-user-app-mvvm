package com.autoever.apay_user_app.ui.refund;

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
import com.autoever.apay_user_app.data.model.api.RefundDoResponse;
import com.autoever.apay_user_app.databinding.ActivityRefundBinding;
import com.autoever.apay_user_app.ui.auth.AuthFragment;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.refund.amount.RefundAmountFragment;
import com.autoever.apay_user_app.ui.refund.receipt.RefundReceiptFragment;
import com.autoever.apay_user_app.ui.refund.terms.RefundTermsFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class RefundActivity extends BaseActivity<ActivityRefundBinding, RefundViewModel> implements RefundNavigator, HasSupportFragmentInjector {

    private FragmentManager mFragmentManager;

    private String amount = "";

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    private ActivityRefundBinding mActivityRefundBinding;
    private RefundViewModel mRefundViewModel;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, RefundActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_refund;
    }

    @Override
    public RefundViewModel getViewModel() {
        mRefundViewModel = ViewModelProviders.of(this, factory)
                .get(RefundViewModel.class);
        return mRefundViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityRefundBinding = getViewDataBinding();
        mRefundViewModel.setNavigator(this);

        setup();

        openRefundTermsFragment();
    }

    private void setup() {
        this.mFragmentManager = getSupportFragmentManager();
        setSupportActionBar(mActivityRefundBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mActivityRefundBinding.toolbarTitle.setText("환전");
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void openRefundTermsFragment() {
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, RefundTermsFragment.newInstance(), RefundTermsFragment.TAG)
                .addToBackStack(RefundTermsFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public void openRefundAmountFragment() {
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, RefundAmountFragment.newInstance(), RefundAmountFragment.TAG)
                .addToBackStack(RefundAmountFragment.TAG)
                .commitAllowingStateLoss();
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
    public void doRefundReady() {
        Log.d("debug", "doRefundReady");
        mRefundViewModel.doRefundReadyCall();
    }

    @Override
    public void openRefundReceiptFragment(RefundDoResponse refundDoResponse) {
        mActivityRefundBinding.toolbar.setVisibility(View.INVISIBLE);
        mActivityRefundBinding.appBarLayout.setBackgroundColor(getResources().getColor(R.color.receiptBackgroundColor, null));
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, RefundReceiptFragment.newInstance(
                        refundDoResponse.getData().getAmount(),
                        refundDoResponse.getData().getCreatedDate()), RefundReceiptFragment.TAG)
                .addToBackStack(RefundReceiptFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public void onFragmentDetached(String tag) {
        switch (tag) {
            case "RefundTermsFragment":
                openRefundAmountFragment();
                break;
            case "RefundAmountFragment":
                openAuthFragment();
                break;
            case "AuthFragment":
                doRefundReady();
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

    @Override
    public void onReceivedMessageFromFragment(String tag, JSONObject message) {
        super.onReceivedMessageFromFragment(tag, message);
        try {
            switch (tag) {
                case "RefundAmountFragment":
                    amount = message.getString("price");
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
                    case "RefundTermsFragment":
                        finish();
                    case "RefundAmountFragment":
                    case "RefundReceiptFragment":
                        removeFragment(fragmentTag);
                        break;
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}