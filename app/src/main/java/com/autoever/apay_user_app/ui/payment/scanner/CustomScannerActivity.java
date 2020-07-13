package com.autoever.apay_user_app.ui.payment.scanner;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.databinding.ActivityCustomScannerBinding;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

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

public class CustomScannerActivity extends CaptureActivity {
    private TextView balanceLeft;

    @Override
    protected DecoratedBarcodeView initializeContent() {
        setContentView(R.layout.activity_custom_scanner);

        balanceLeft = findViewById(R.id.balance_left);

        String userBalance = getIntent().getStringExtra("balance");

        balanceLeft.setText(userBalance + " P");

        return (DecoratedBarcodeView) findViewById(R.id.zxing_barcode_scanner);


    }
}