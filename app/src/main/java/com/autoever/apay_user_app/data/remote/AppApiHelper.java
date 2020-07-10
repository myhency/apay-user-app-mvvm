package com.autoever.apay_user_app.data.remote;

import com.autoever.apay_user_app.data.model.api.BalanceResponse;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Single;

@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper() {

    }

    @Override
    public Single<BalanceResponse> getUserBalance() {
        return null;
    }
}
