package com.autoever.apay_user_app.ui.charge.amount;

import com.autoever.apay_user_app.ui.common.Bank;

public interface AmountNavigator {

    void handleError(Throwable throwable);

    void goNext();

    void setBankCode(String bankCode);
}
