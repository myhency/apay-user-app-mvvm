package com.autoever.apay_user_app.ui.charge.amount;

public interface AmountNavigator {

    void handleError(Throwable throwable);

    void goNext();

    void setBankCode(String bankCode);

    void handleNoAccount();
}
