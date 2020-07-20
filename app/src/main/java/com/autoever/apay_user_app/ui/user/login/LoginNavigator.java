package com.autoever.apay_user_app.ui.user.login;

public interface LoginNavigator {

    void openMainActivity();

    void openRegisterActivity();

    void handleError(Throwable throwable);
}
