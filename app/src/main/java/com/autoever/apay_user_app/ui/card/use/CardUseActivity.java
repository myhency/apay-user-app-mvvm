package com.autoever.apay_user_app.ui.card.use;

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
import com.autoever.apay_user_app.data.model.api.PaymentRefundReadyResponse;
import com.autoever.apay_user_app.databinding.ActivityCardUseBinding;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.card.use.detail.CardUseDetailFragment;
import com.autoever.apay_user_app.ui.card.use.fail.PaymentRefundReadyFailFragment;
import com.autoever.apay_user_app.ui.card.use.history.CardUseHistoryFragment;
import com.autoever.apay_user_app.ui.card.use.receipt.PaymentRefundReadyReceiptFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;


public class CardUseActivity extends BaseActivity<ActivityCardUseBinding, CardUseViewModel> implements CardUseNavigator, HasSupportFragmentInjector {

    public static final String TAG = CardUseActivity.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    private ActivityCardUseBinding mActivityCardUseBinding;
    private CardUseViewModel mCardUseViewModel;
    private FragmentManager mFragmentManager;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CardUseActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_card_use;
    }

    @Override
    public CardUseViewModel getViewModel() {
        mCardUseViewModel = ViewModelProviders.of(this, factory)
                .get(CardUseViewModel.class);
        return mCardUseViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityCardUseBinding = getViewDataBinding();
        mCardUseViewModel.setNavigator(this);

        setup();

        openCardUseHistoryFragment();
    }

    private void openCardUseHistoryFragment() {
        //카드정보화면으로 이동.
        Log.d("debug", "openCardUseHistoryFragment");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, CardUseHistoryFragment.newInstance(), CardUseHistoryFragment.TAG)
                .addToBackStack(CardUseHistoryFragment.TAG)
                .commit();
    }

    private void setup() {
        this.mFragmentManager = getSupportFragmentManager();
        setSupportActionBar(mActivityCardUseBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mActivityCardUseBinding.toolbarTitle.setText("사용내역");
        }
    }

    @Override
    public void onReceivedMessageFromFragment(String tag, JSONObject message) {
        super.onReceivedMessageFromFragment(tag, message);
        switch (tag) {
            case "CardUseHistoryFragment":
                try {
                    openCardUseDetailFragment(message.getLong("paymentHistoryId"), message.getString("paymentStatus"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case "CardUseDetailFragment":
                try {
                    if (message.has("fail")) {
                        openPaymentRefundReadyFailFragment(message.getString("fail"));
                    } else {
                        openPaymentRefundReadyReceiptFragment((PaymentRefundReadyResponse) message.get("paymentRefundReadyResponse"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    @Override
    public void onFragmentDetached(String tag) {
        super.onFragmentDetached(tag);
        switch (tag) {
            case "PaymentRefundReadyReceiptFragment":
                removeFragment("CardUseDetailFragment");
                removeFragment(tag);
                mActivityCardUseBinding.toolbar.setVisibility(View.VISIBLE);
                mActivityCardUseBinding.appBarLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite, null));
                mActivityCardUseBinding.toolbarTitle.setText("사용내역");
                break;
            case "CardUseDetailFragment":
                mActivityCardUseBinding.toolbarTitle.setText("사용내역");
                removeFragment(tag);
                break;
            case "PaymentRefundReadyFailFragment":
                removeFragment(tag);
                mActivityCardUseBinding.toolbar.setVisibility(View.VISIBLE);
                mActivityCardUseBinding.appBarLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite, null));
                mActivityCardUseBinding.toolbarTitle.setText("사용내역");
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("debug", "onOptionsItemSelected:" + item.toString());
        List<Fragment> fragments = mFragmentManager.getFragments();
        String fragmentTag = fragments.get(fragments.size() - 1).getTag();
        switch (fragmentTag) {
            case "CardUseHistoryFragment":
                finish();
            case "CardUseDetailFragment":
                mActivityCardUseBinding.toolbarTitle.setText("사용내역");
                removeFragment(fragmentTag);
                break;
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

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void openCardUseDetailFragment(Long paymentHistoryId, String paymentStatus) {
        //사용내역화면으로 이동.
        Log.d("debug", "openCardUseDetailFragment");
        mActivityCardUseBinding.toolbarTitle.setText("상세 내역");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, CardUseDetailFragment.newInstance(paymentHistoryId, paymentStatus), CardUseDetailFragment.TAG)
                .addToBackStack(CardUseDetailFragment.TAG)
                .commit();
    }

    @Override
    public void openPaymentRefundReadyReceiptFragment(PaymentRefundReadyResponse paymentRefundReadyResponse) {
        //결제취소요청 완료 화면으로 이동.
        Log.d("debug", "openPaymentRefundReadyReceiptFragment");
        mActivityCardUseBinding.toolbar.setVisibility(View.INVISIBLE);
        mActivityCardUseBinding.appBarLayout.setBackgroundColor(getResources().getColor(R.color.receiptBackgroundColor, null));
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, PaymentRefundReadyReceiptFragment.newInstance(paymentRefundReadyResponse), PaymentRefundReadyReceiptFragment.TAG)
                .addToBackStack(PaymentRefundReadyReceiptFragment.TAG)
                .commit();
    }

    @Override
    public void openPaymentRefundReadyFailFragment(String state) {
        //결제취소요청 실패 화면으로 이동.
        Log.d("debug", "openPaymentRefundReadyFailFragment");
        mActivityCardUseBinding.toolbar.setVisibility(View.INVISIBLE);
        mActivityCardUseBinding.appBarLayout.setBackgroundColor(getResources().getColor(R.color.receiptBackgroundColor, null));
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, PaymentRefundReadyFailFragment.newInstance(state), PaymentRefundReadyFailFragment.TAG)
                .addToBackStack(PaymentRefundReadyFailFragment.TAG)
                .commit();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
