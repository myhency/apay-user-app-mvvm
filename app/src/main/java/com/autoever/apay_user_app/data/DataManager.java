package com.autoever.apay_user_app.data;


import com.autoever.apay_user_app.data.local.db.DbHelper;
import com.autoever.apay_user_app.data.remote.ApiHelper;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;

public interface DataManager extends DbHelper, ApiHelper {
    Observable<Boolean> seedDatabaseQuestions();

    Observable<Boolean> getAppAccessPermissions();

}
