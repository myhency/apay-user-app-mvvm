package com.autoever.apay_user_app.data.remote;

import com.autoever.apay_user_app.BuildConfig;

public final class ApiEndPoint {

    public final static String ENDPOINT_USER_BALANCE = BuildConfig.BASE_URL + "/tokenSystem/{tokenSystemId}/balance";
    public final static String ENDPOINT_PAYMENT_READY = BuildConfig.BASE_URL + "/payment/ready";
}
