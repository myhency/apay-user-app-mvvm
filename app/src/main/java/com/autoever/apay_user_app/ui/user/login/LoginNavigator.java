package com.autoever.apay_user_app.ui.user.login;

public interface LoginNavigator {

    void openMainActivity();

    void openRegisterActivity();

    void openFindMyIdActivity();

    void openInitMyPasswordActivity();

    void handleError(Throwable throwable);
}
