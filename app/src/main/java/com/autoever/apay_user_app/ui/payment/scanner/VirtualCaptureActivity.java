package com.autoever.apay_user_app.ui.payment.scanner;

import com.autoever.apay_user_app.R;
import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

class VirtualCaptureActivity extends CaptureActivity {
    @Override
    protected DecoratedBarcodeView initializeContent() {
//        setContentView(R.layout.activity_custom_scanner);
        return (DecoratedBarcodeView) findViewById(R.id.zxing_barcode_scanner);
    }
}
