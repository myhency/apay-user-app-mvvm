package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreHashRequest {

    @Expose
    @SerializedName("hashedStoreId")
    private String hashedStoreId;

    public StoreHashRequest(String hashedStoreId) {
        this.hashedStoreId = hashedStoreId;
    }

    public String getHashedStoreId() {
        return hashedStoreId;
    }
}
