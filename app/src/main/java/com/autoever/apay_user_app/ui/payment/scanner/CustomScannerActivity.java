package com.autoever.apay_user_app.ui.payment.scanner;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelStore;
import androidx.lifecycle.ViewModelStoreOwner;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.ActivityCustomScannerBinding;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.main.MainActivity;
import com.autoever.apay_user_app.ui.main.MainViewModel;
import com.autoever.apay_user_app.ui.payment.PaymentActivity;
import com.autoever.apay_user_app.ui.payment.PaymentNavigator;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.zxing.integration.android.IntentIntegrator;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

import java.io.IOException;

import javax.inject.Inject;

import dagger.android.ContributesAndroidInjector;

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

    private SurfaceView cameraPreview;
    private BarcodeDetector mBarcodeDetector;
    private CameraSource mCameraSource;

    final int RequestCameraPermissionID = 1001;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CustomScannerActivity.class);
        return intent;
    }

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityCustomScannerBinding = getViewDataBinding();
        mCustomScannerViewModel.setNavigator(this);

        mActivityCustomScannerBinding = getViewDataBinding();

        cameraPreview = mActivityCustomScannerBinding.barcodeScanner;

        mBarcodeDetector = new BarcodeDetector.Builder(this)
                .setBarcodeFormats(Barcode.QR_CODE)
                .build();
        mCameraSource = new CameraSource.Builder(this, mBarcodeDetector)
                .build();
        cameraPreview.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(CustomScannerActivity.this,
                            new String[]{Manifest.permission.CAMERA}, RequestCameraPermissionID);
                    return;
                }

                try {
                    mCameraSource.start(cameraPreview.getHolder());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                mCameraSource.stop();
            }
        });

        mBarcodeDetector.setProcessor(new Detector.Processor<Barcode>() {
            @Override
            public void release() {

            }

            @Override
            public void receiveDetections(Detector.Detections<Barcode> detections) {
                final SparseArray<Barcode> qrcodes = detections.getDetectedItems();
                if (qrcodes.size() != 0) {
//                    Log.d("debug", "qr value: " + qrcodes.valueAt(0).displayValue);
                    Vibrator vibrator = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
                    vibrator.vibrate(200);
                    Intent data = new Intent();
                    data.putExtra("shopCode", qrcodes.valueAt(0).displayValue);
                    setResult(RESULT_OK, data);
                    finish();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case RequestCameraPermissionID: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    try {
                        mCameraSource.start(cameraPreview.getHolder());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            break;
        }
    }

    @Override
    public void handleError(Throwable throwable) {

    }
}