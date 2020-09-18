package com.autoever.apay_user_app.ui.account.register;

import com.autoever.apay_user_app.data.model.api.ArsCheckResponse;
import com.autoever.apay_user_app.data.model.api.ArsRequestResponse;

public interface AccountRegisterNavigator {

    void openAccountRegisterTermsFragment();

    void openCellPhoneAuthFragment();

    void openBankSelectFragment();

    void openBankAccountNumberFragment(String selectedBankId, String selectedBankName);

    void openArsAuthFragment(ArsRequestResponse arsRequestResponse);

    void openDialog();

    void handleError(Throwable throwable);

    void openRegisterAccountFailFragment();
}
