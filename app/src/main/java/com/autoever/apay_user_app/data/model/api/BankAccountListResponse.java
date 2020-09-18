package com.autoever.apay_user_app.data.model.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * {
 * "data": [
 * {
 * "registerDatetime": "2020-09-18T04:11:44",
 * "bankCode": "003",
 * "accountNumber": "001000100001002"
 * },
 * {
 * "registerDatetime": "2020-09-18T04:23:41",
 * "bankCode": "002",
 * "accountNumber": "001000100001001"
 * }
 * ]
 * }
 */

public class BankAccountListResponse {

    @Expose
    @SerializedName("data")
    private List<Account> data;

    public List<Account> getData() {
        return data;
    }

    public static class Account {

        @Expose
        @SerializedName("registerDatetime")
        private Date registerDatetime;

        @Expose
        @SerializedName("bankCode")
        private String bankCode;

        @Expose
        @SerializedName("accountNumber")
        private String accountNumber;

        public Account(Date registerDatetime, String bankCode, String accountNumber) {
            this.registerDatetime = registerDatetime;
            this.bankCode = bankCode;
            this.accountNumber = accountNumber;
        }

        public Date getRegisterDatetime() {
            return registerDatetime;
        }

        public String getBankCode() {
            return bankCode;
        }

        public String getAccountNumber() {
            return accountNumber;
        }
    }
}
