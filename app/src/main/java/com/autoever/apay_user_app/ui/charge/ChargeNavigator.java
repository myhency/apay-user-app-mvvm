package com.autoever.apay_user_app.ui.charge;

import com.autoever.apay_user_app.data.model.api.ChargeDoResponse;
import com.autoever.apay_user_app.data.model.api.ChargeReadyResponse;

public interface ChargeNavigator {

    void openAmountFragment();

    void handleError(Throwable throwable);

    void openAuthFragment();

    void doChargeReady();

    void openChargeReceiptFragment(ChargeDoResponse chargeDoResponse, String bankInfo);

    void openChargeFailFragment();
}
