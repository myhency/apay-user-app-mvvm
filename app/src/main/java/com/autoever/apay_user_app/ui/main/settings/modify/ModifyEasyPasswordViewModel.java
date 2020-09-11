package com.autoever.apay_user_app.ui.main.settings.modify;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class ModifyEasyPasswordViewModel extends BaseViewModel<ModifyEasyPasswordNavigator> {

    public ModifyEasyPasswordViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }

    public void setEasyPassword(String password) {
        getDataManager().setEasyPassword(password);
    }

    public String getEasyPassword() {
        return getDataManager().getEasyPassword();
    }
}
