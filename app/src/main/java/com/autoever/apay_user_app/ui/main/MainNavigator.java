package com.autoever.apay_user_app.ui.main;

public interface MainNavigator {

    void handleError(Throwable throwable);

    void openLoginActivity();

    void openPaymentActivity();

    void openCardChargeActivity();

    void openCardInfoActivity();

    void openAccountActivity();

    void openCardUseHistoryActivity();

    void openSettingsActivity();

    void openNotificationActivity();

    void openNoticeBoardActivity();

    void openFaqActivity();

    void openUserProfileActivity();
}
