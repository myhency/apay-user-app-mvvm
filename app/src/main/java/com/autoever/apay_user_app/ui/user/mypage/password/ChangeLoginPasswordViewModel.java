package com.autoever.apay_user_app.ui.user.mypage.password;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.data.model.api.ModifyPasswordRequest;
import com.autoever.apay_user_app.ui.base.BaseViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

public class ChangeLoginPasswordViewModel extends BaseViewModel<ChangeLoginPasswordNavigator> {

    public ChangeLoginPasswordViewModel(DataManager mDataManager, SchedulerProvider schedulerProvider) {
        super(mDataManager, schedulerProvider);
    }

    public void changeLoginPasswordCall(String newPassword) {
        setIsLoading(true);
        getCompositeDisposable().add(getDataManager()
        .doModifyPasswordCall(
                getDataManager().getCurrentUserId(),
                new ModifyPasswordRequest(newPassword))
        .subscribeOn(getSchedulerProvider().io())
        .observeOn(getSchedulerProvider().ui())
        .subscribe(modifyPasswordResponse -> {
            setIsLoading(false);
        }, throwable -> {
            setIsLoading(false);
            getNavigator().handleChangeLoginPassword(throwable);
        }));
    }
}
