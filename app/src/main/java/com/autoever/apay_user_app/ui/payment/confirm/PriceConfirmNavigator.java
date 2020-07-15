package com.autoever.apay_user_app.ui.payment.confirm;

public interface PriceConfirmNavigator {

    void handleError(Throwable throwable);

    void goNext();
}
