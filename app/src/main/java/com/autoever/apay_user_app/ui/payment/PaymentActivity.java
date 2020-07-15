package com.autoever.apay_user_app.ui.payment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.NavUtils;
import androidx.core.app.TaskStackBuilder;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.ActivityPaymentBinding;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.payment.price.PriceFragment;
import com.autoever.apay_user_app.ui.payment.scanner.CustomScannerActivity;
import com.autoever.apay_user_app.utils.CommonUtils;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class PaymentActivity extends BaseActivity<ActivityPaymentBinding, PaymentViewModel> implements PaymentNavigator, HasSupportFragmentInjector {

    public static final String TAG = PaymentActivity.class.getSimpleName();
    private final static int QR_CODE_SCANNED = 1;
    private FragmentManager mFragmentManager;
    private int price = 0;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    ViewModelProviderFactory factory;

    private ActivityPaymentBinding mActivityPaymentBinding;
    private PaymentViewModel mPaymentViewModel;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, PaymentActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    public PaymentViewModel getViewModel() {
        mPaymentViewModel = ViewModelProviders.of(this, factory)
                .get(PaymentViewModel.class);
        return mPaymentViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("debug", "PaymentActivity onCreate");
        mActivityPaymentBinding = getViewDataBinding();
        mPaymentViewModel.setNavigator(this);

        setup();

        openCustomScannerActivity();
    }

    private void setup() {
        this.mFragmentManager = getSupportFragmentManager();
        setSupportActionBar(mActivityPaymentBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mActivityPaymentBinding.toolbarTitle.setText("결제");
        }
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("debug", "onOptionsItemSelected:" + item.toString());
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                List<Fragment> fragments = mFragmentManager.getFragments();
                String fragmentTag = fragments.get(fragments.size() - 1).getTag();
                switch (fragmentTag) {
                    case "PriceFragment":
                        finish();
                }
//                Log.d("debug", "menu home");
//                Intent upIntent = NavUtils.getParentActivityIntent(this);
//                upIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                if (NavUtils.shouldUpRecreateTask(this, upIntent)) {
//                    Log.d("debug", "shouldUpRecreateTask true");
//                    // This activity is NOT part of this app's task, so create a new task
//                    // when navigating up, with a synthesized back stack.
//                    TaskStackBuilder.create(this)
//                            // Add all of this activity's parents to the back stack
//                            .addNextIntentWithParentStack(upIntent)
//                            // Navigate up to the closest parent
//                            .startActivities();
//                } else {
//                    Log.d("debug", "shouldUpRecreateTask false");
//                    // This activity is part of this app's task, so simply
//                    // navigate up to the logical parent activity.
//                    NavUtils.navigateUpTo(this, upIntent);
//                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void openCustomScannerActivity() {
        Log.d("debug", "openCustomScannerActivity");
        Intent intent = CustomScannerActivity.newIntent(PaymentActivity.this);
        startActivityForResult(intent, QR_CODE_SCANNED);
    }

    @Override
    public void showPaymentFragment(String shopCode) {
        //금액입력화면으로 이동.
        Log.d("debug", "showPaymentFragment");
//        PriceFragment.newInstance(shopCode);
        mFragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.slide_left, R.anim.slide_right)
                .add(R.id.clRootView, PriceFragment.newInstance(shopCode), PriceFragment.TAG)
                .addToBackStack(PriceFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);

        Log.d("debug", "requestCode: " + requestCode);

        //여기서부터는 QR리더기를 위한 부분.
        if (requestCode != QR_CODE_SCANNED) {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
            return;
        }

        switch (requestCode) {
            case QR_CODE_SCANNED: {
                switch (resultCode) {
                    case RESULT_OK:
                        String shopCode = data.getExtras().getString("shopCode");
                        Log.d("debug", "shopCode: " + shopCode);
                        showPaymentFragment(shopCode);
                        break;
                    default:
                        break;
                }
                break;
            }
            default:
                break;
        }
    }

    @Override
    public void onFragmentDetached(String tag) {
        Log.d("debug", "onFragmentDetached: " + tag);
        Fragment fragment = mFragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            mFragmentManager
                    .beginTransaction()
                    .disallowAddToBackStack()
                    .setCustomAnimations(R.anim.slide_right,R.anim.slide_left)
                    .remove(fragment)
                    .commitNow();
        }
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void onReceivedMessageFromFragment(String tag, String message) {
        Log.d("debug", message);
        switch (tag) {
            case "PriceFragment":
                price = CommonUtils.parseToInt(message);
        }
    }
}
