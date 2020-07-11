package com.autoever.apay_user_app.data.remote;

import com.autoever.apay_user_app.data.model.api.BalanceResponse;

import io.reactivex.Single;

public interface ApiHelper {
    Single<BalanceResponse> getUserBalance(String tokenSystenId, String subscriberId);
}
