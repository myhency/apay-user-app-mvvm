package com.autoever.apay_user_app.data.model.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class StoreHashResponse {

    @Expose
    @SerializedName("data")
    private StoreHash data;

    public StoreHash getData() {
        return data;
    }

    public static class StoreHash {

        @Expose
        @SerializedName("storeId")
        private Long storeId;

        @Expose
        @SerializedName("storeName")
        private String storeName;

        public StoreHash(Long storeId, String storeName) {
            this.storeId = storeId;
            this.storeName = storeName;
        }

        public Long getStoreId() {
            return storeId;
        }

        public String getStoreName() {
            return storeName;
        }
    }
}
