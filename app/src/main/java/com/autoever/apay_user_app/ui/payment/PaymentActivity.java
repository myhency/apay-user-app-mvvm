package com.autoever.apay_user_app.ui.payment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.ActivityPaymentBinding;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.payment.scanner.CustomScannerActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import javax.inject.Inject;

public class PaymentActivity extends BaseActivity<ActivityPaymentBinding, PaymentViewModel> implements PaymentNavigator{

    public static final String TAG = PaymentActivity.class.getSimpleName();

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
        mActivityPaymentBinding = getViewDataBinding();
        mPaymentViewModel.setNavigator(this);
        IntentIntegrator integrator = new IntentIntegrator(PaymentActivity.this);
        integrator.setOrientationLocked(false);
        integrator.setCaptureActivity(CustomScannerActivity.class);
        integrator.initiateScan();
//        VirtualCaptureActivity mVirtualCaptureActivity = new VirtualCaptureActivity();
//        mVirtualCaptureActivity.initializeContent();
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    class VirtualCaptureActivity extends CaptureActivity {
        @Override
        protected DecoratedBarcodeView initializeContent() {
            return (DecoratedBarcodeView) mActivityPaymentBinding.zxingBarcodeScanner;
        }
    }
}
