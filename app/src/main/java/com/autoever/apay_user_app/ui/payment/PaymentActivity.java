package com.autoever.apay_user_app.ui.payment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.androidnetworking.error.ANError;
import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.ActivityPaymentBinding;
import com.autoever.apay_user_app.ui.auth.AuthFragment;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.charge.fail.ChargeFailFragment;
import com.autoever.apay_user_app.ui.payment.confirm.PriceConfirmFragment;
import com.autoever.apay_user_app.ui.payment.fail.PaymentFailFragment;
import com.autoever.apay_user_app.ui.payment.price.PriceFragment;
import com.autoever.apay_user_app.ui.payment.receipt.ReceiptFragment;
import com.autoever.apay_user_app.ui.payment.scanner.CustomScannerActivity;
import com.autoever.apay_user_app.utils.CommonUtils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import retrofit2.HttpException;

public class PaymentActivity extends BaseActivity<ActivityPaymentBinding, PaymentViewModel> implements PaymentNavigator, HasSupportFragmentInjector {

    public static final String TAG = PaymentActivity.class.getSimpleName();
    private final static int QR_CODE_SCANNED = 1;
    private FragmentManager mFragmentManager;
    private String storeName = "";
    private String hashedStoreId;
    private int price = 0;
    private JsonObject staticQrData;

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
        HttpException httpException = (HttpException) throwable;

        switch (httpException.code()) {
            default:
                openPaymentFailFragment();
                break;
        }
    }

    public void openPaymentFailFragment() {
        //충전실패 화면으로 이동.
        Log.d("debug", "openPaymentFailFragment");
        mActivityPaymentBinding.toolbar.setVisibility(View.INVISIBLE);
        mActivityPaymentBinding.appBarLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite, null));
        mFragmentManager
                .beginTransaction()
                .replace(R.id.clRootView, PaymentFailFragment.newInstance(), PaymentFailFragment.TAG)
                .addToBackStack(PaymentFailFragment.TAG)
                .commit();
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
                    case "PriceConfirmFragment":
                    case "AuthFragment":
                        Fragment fragment = mFragmentManager.findFragmentByTag(fragmentTag);
                        mFragmentManager
                                .beginTransaction()
                                .disallowAddToBackStack()
                                .remove(fragment)
                                .commit();
                        break;
                }
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
    public void showPriceFragment(String hashedStoreId) {
        //금액입력화면으로 이동.
        Log.d("debug", "showPaymentFragment");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, PriceFragment.newInstance(hashedStoreId), PriceFragment.TAG)
                .addToBackStack(PriceFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public void showPriceConfirmFragment(String storeName, int price) {
        Log.d("debug", "showPriceConfirmFragment");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, PriceConfirmFragment.newInstance(storeName, price), PriceConfirmFragment.TAG)
                .addToBackStack(PriceConfirmFragment.TAG)
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
    public void showReceiptFragment(String storeName, Date createdDate, int amount, int userBalance) {
        mActivityPaymentBinding.toolbar.setVisibility(View.INVISIBLE);
        mActivityPaymentBinding.appBarLayout.setBackgroundColor(getResources().getColor(R.color.receiptBackgroundColor, null));
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, ReceiptFragment.newInstance(storeName, createdDate, amount, userBalance), ReceiptFragment.TAG)
                .addToBackStack(ReceiptFragment.TAG)
                .commitAllowingStateLoss();
    }

    @Override
    public void goNext() {

    }

    @Override
    public void getQrUserDynamicData(String parsedQrString) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
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
                        Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                        vibrator.vibrate(20);
                        staticQrData = new Gson().fromJson(data.getExtras().getString("shopCode"), JsonObject.class);
                        if (staticQrData.has("storeId")) {
                            hashedStoreId = staticQrData.get("storeId").getAsString();
                        } else if (staticQrData.has("hashedStoreId")) {
                            hashedStoreId = staticQrData.get("hashedStoreId").getAsString();
                        }
                        showPriceFragment(hashedStoreId);
                        break;
                    case RESULT_CANCELED:
                        finish();
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

        switch (tag) {
            case "PriceFragment":
                showPriceConfirmFragment(storeName, price);
                break;
            case "PriceConfirmFragment":
                openAuthFragment();
                break;
            case "AuthFragment":
                doPaymentReady();
                break;
            case "PaymentFailFragment":
                finish();
                break;
        }
    }

    @Override
    public void doPaymentReady() {
        Log.d("debug", "doPaymentReady");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        mPaymentViewModel.loadPaymentId(price, String.valueOf(timestamp.getTime()), staticQrData);
        Log.d("debug", "identifier:" + String.valueOf(timestamp.getTime()));
    }

    @Override
    public void doPaymentDo(String userId, String storeId, String tokenSystemId, int amount, String paymentId, String identifier) {
        Log.d("debug", "doPaymentReady");
        mPaymentViewModel.doPayment(userId, storeId, tokenSystemId, amount, paymentId, identifier);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void onReceivedMessageFromFragment(String tag, JSONObject message) {
        try {
            switch (tag) {
                case "PriceFragment":
                    price = CommonUtils.parseToInt(message.getString("price"));
                    break;
                default:
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
