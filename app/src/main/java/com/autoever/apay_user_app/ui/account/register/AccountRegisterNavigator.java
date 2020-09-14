package com.autoever.apay_user_app.ui.account.register;

import com.autoever.apay_user_app.data.model.api.ArsCheckResponse;

public interface AccountRegisterNavigator {

    void openAccountRegisterTermsFragment();

    void openCellPhoneAuthFragment();

    void openBankSelectFragment();

    void openBankAccountNumberFragment(String selectedBankId, String selectedBankName);

    void openArsAuthFragment(ArsCheckResponse arsCheckResponse);

    void openDialog();

    void handleError(Throwable throwable);

    void openRegisterAccountFailFragment();
}
