package com.autoever.apay_user_app.ui.payment.scanner;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.ActivityCustomScannerBinding;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.main.MainViewModel;
import com.autoever.apay_user_app.ui.payment.PaymentActivity;
import com.autoever.apay_user_app.ui.payment.PaymentNavigator;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import javax.inject.Inject;

//public class CustomScannerActivity extends BaseActivity<ActivityCustomScannerBinding, CustomScannerViewModel> {
//    private ActivityCustomScannerBinding mActivityCustomScannerBinding;
//
//    public static IntentIntegrator newIntentIntegrator(Activity activity) {
//        IntentIntegrator intentIntegrator = new IntentIntegrator(activity);
//        intentIntegrator.setOrientationLocked(false);
//        intentIntegrator.setCaptureActivity();
//    }
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mActivityCustomScannerBinding = getViewDataBinding();
//        VirtualCaptureActivity mVirtualCaptureActivity = new VirtualCaptureActivity();
//        mVirtualCaptureActivity.initializeContent();
//    }
//
//    @Override
//    public int getBindingVariable() {
//        return BR.viewModel;
//    }
//
//    @Override
//    public int getLayoutId() {
//        return R.layout.activity_custom_scanner;
//    }
//
//    @Override
//    public CustomScannerViewModel getViewModel() {
//        return null;
//    }
//
//    class VirtualCaptureActivity extends CaptureActivity {
//        @Override
//        protected DecoratedBarcodeView initializeContent() {
//            return (DecoratedBarcodeView) mActivityCustomScannerBinding.zxingBarcodeScanner;
//        }
//    }
//}

public class CustomScannerActivity extends BaseActivity<ActivityCustomScannerBinding, CustomScannerViewModel> implements CustomScannerNavigator {

    @Inject
    ViewModelProviderFactory factory;

    private CustomScannerViewModel mCustomScannerViewModel;
    private ActivityCustomScannerBinding mActivityCustomScannerBinding;


    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_custom_scanner;
    }

    @Override
    public CustomScannerViewModel getViewModel() {
        mCustomScannerViewModel = ViewModelProviders.of(this, factory).get(CustomScannerViewModel.class);
        return mCustomScannerViewModel;
    }

    public ActivityCustomScannerBinding getViewDataBinding() {
        return mActivityCustomScannerBinding;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityCustomScannerBinding = getViewDataBinding();
        mCustomScannerViewModel.setNavigator(this);
        openScanner();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void openScanner() {
        IntentIntegrator integrator = new IntentIntegrator(CustomScannerActivity.this);
        integrator.setOrientationLocked(false);
        integrator.setCaptureActivity(VirtualCaptureActivity.class);
        integrator.initiateScan();
    }
}