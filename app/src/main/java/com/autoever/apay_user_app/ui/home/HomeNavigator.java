package com.autoever.apay_user_app.ui.home;

public interface HomeNavigator {

    void handleError(Throwable throwable);

    void openLoginActivity();

    void openPaymentActivity();

    void openCardChargeActivity();

    void openCardInfoActivity();

    void openBankAccountManagementActivity();

    void openCardUseHistoryActivity();

    void openSettingsActivity();

    void openNotificationActivity();

    void openNoticeBoardActivity();

    void openFaqActivity();

    void openUserProfileActivity();

    void openAccountActivity();
}
