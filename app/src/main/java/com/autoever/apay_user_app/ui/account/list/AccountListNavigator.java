package com.autoever.apay_user_app.ui.account.list;

import com.autoever.apay_user_app.data.model.api.BankAccountListResponse;

public interface AccountListNavigator {

    void openAccountRegisterActivity();

    void setAccountInfo(BankAccountListResponse bankAccountListResponse);

    void handleError(Throwable throwable);

    void resetScreen();
}
