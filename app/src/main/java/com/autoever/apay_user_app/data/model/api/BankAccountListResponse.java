package com.autoever.apay_user_app.data.model.api;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * {
 *   "data": {
 *     "accountCnt": "0",
 *     "historyId": 4359,
 *     "accountList": [
 *     {
 * 			"registerDatetime": "14 : numeric string. 등록 일시. yyyyMMddHHmmss",
 * 		  "withdrawBankCode": "3 : 은행코드. numeric string",
 * 		  "maskingAccountNumber": "15 : 마스킹된 계좌번호. alpha-numeric",
 * 			"settleAccountKey": "50 : alpha-numeric. [/account/register] 에서 리턴으로 받은 값을 사용"
 * 			"terminationDatetime": "14 : numeric string. 해지 일시. yyyyMMddHHmmss"
 *                },
 *      ],
 *     "withdrawBankCode": "004",
 *     "settleBankUniqueId": "uniqueId",
 *     "responseMessage": "0021",
 *     "userId": "4",
 *     "requestStatus": "0021",
 *     "responseCode": "0021"
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
        @SerializedName("accountCnt")
        private String accountCnt;

        @Expose
        @SerializedName("historyId")
        private Long historyId;

        @Expose
        @SerializedName("accountList")
        List<Account> accountList;

        public List<Account> getAccountList() { return accountList; }

        public static class Account {

            @Expose
            @SerializedName("registerDatetime")
            private String registerDatetime;

            @Expose
            @SerializedName("withdrawBankCode")
            private String withdrawBankCode;

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

            public String getWithdrawBankCode() {
                return withdrawBankCode;
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

        @Expose
        @SerializedName("withdrawBankCode")
        private String withdrawBankCode;

        @Expose
        @SerializedName("settleBankUniqueId")
        private String settleBankUniqueId;

        @Expose
        @SerializedName("responseMessage")
        private String responseMessage;

        @Expose
        @SerializedName("userId")
        private String userId;

        @Expose
        @SerializedName("requestStatus")
        private String requestStatus;

        @Expose
        @SerializedName("responseCode")
        private String responseCode;

        public String getAccountCnt() {
            return accountCnt;
        }

        public Long getHistoryId() {
            return historyId;
        }

        public String getWithdrawBankCode() {
            return withdrawBankCode;
        }

        public String getSettleBankUniqueId() {
            return settleBankUniqueId;
        }

        public String getResponseMessage() {
            return responseMessage;
        }

        public String getUserId() {
            return userId;
        }

        public String getRequestStatus() {
            return requestStatus;
        }

        public String getResponseCode() {
            return responseCode;
        }
    }
}
