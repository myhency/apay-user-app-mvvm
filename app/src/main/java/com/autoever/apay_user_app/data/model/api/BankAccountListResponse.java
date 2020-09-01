package com.autoever.apay_user_app.data.model.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 *   "date": {
 *     "settleBankUniqueId": "uniqueId",
 *     "subscriberId": "4",
 *     "accountCnt": "0",
 *     "accountList": [
 *       {
 *         "registerDatetime": "20200901101000",
 *         "bankCode": "002",
 *         "maskingAccountNumber": "0010******1123",
 *         "settleAccountKey": "settleAccountKey",
 *         "terminationDatetime": "20200901101000"
 *       }
 *     ]
 *   }
 * }
 */
public class BankAccountListResponse {

    @Expose
    @SerializedName("data")
    private AccountList data;

    public AccountList getData() { return data; }

    public static class AccountList {
        @Expose
        @SerializedName("settleBankUniqueId")
        private String settleBankUniqueId;

        @Expose
        @SerializedName("subscriberId")
        private Long subscriberId;

        @Expose
        @SerializedName("accountCnt")
        private Long accountCnt;

        @Expose
        @SerializedName("accountList")
        List<Account> accountList;

        public List<Account> getAccountList() { return accountList; }

        public static class Account {

            @Expose
            @SerializedName("registerDatetime")
            private String registerDatetime;

            @Expose
            @SerializedName("bankCode")
            private String bankCode;

            @Expose
            @SerializedName("maskingAccountNumber")
            private String maskingAccountNumber;

            @Expose
            @SerializedName("settleAccountKey")
            private String settleAccountKey;

            @Expose
            @SerializedName("terminationDatetime")
            private String terminationDatetime;

            public String getRegisterDatetime() {
                return registerDatetime;
            }

            public String getBankCode() {
                return bankCode;
            }

            public String getMaskingAccountNumber() {
                return maskingAccountNumber;
            }

            public String getSettleAccountKey() {
                return settleAccountKey;
            }

            public String getTerminationDatetime() {
                return terminationDatetime;
            }
        }

        public AccountList(String settleBankUniqueId, Long subscriberId, Long accountCnt, List<Account> accountList) {
            this.settleBankUniqueId = settleBankUniqueId;
            this.subscriberId = subscriberId;
            this.accountCnt = accountCnt;
            this.accountList = accountList;
        }

        public String getSettleBankUniqueId() {
            return settleBankUniqueId;
        }

        public Long getSubscriberId() {
            return subscriberId;
        }

        public Long getAccountCnt() {
            return accountCnt;
        }
    }
}
