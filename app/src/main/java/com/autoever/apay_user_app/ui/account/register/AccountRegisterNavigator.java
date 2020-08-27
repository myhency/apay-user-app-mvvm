package com.autoever.apay_user_app.ui.account.register;

public interface AccountRegisterNavigator {

    void openAccountRegisterTermsFragment();

    void openCellPhoneAuthFragment();

    void openBankSelectFragment();

    void openBankAccountNumberFragment(String selectedBankId, String selectedBankName);
}
