package com.autoever.apay_user_app.ui.common;

import android.util.Log;

import java.util.Arrays;
import java.util.List;

public enum Bank {

    KDB("산업은행", "002"),
    IBK("기업은행", "003"),
    KB("국민은행", "004"),
    SH("수협, 수협중앙회", "007"),
    NH("농협", "011"),
    WOORI("우리은행", "020"),
    SC("SC제일은행", "023"),
    CITI("씨티은행", "027"),
    DGB("대구은행", "031"),
    BNK("부산은행", "032"),
    KJB("광주은행", "034"),
    JEJU("제주은행", "035"),
    JB("전북은행", "037"),
    KNB("경남은행", "039"),
    KFCC("새마을(금고)", "045"),
    CU("신협", "048"),
    FSB("저축은행중앙회", "050"),
    HSBC("HSBC", "054"),
    DB("도이치", "055"),
    JPMORGAN("제이피모간체이스","057"), // 제이피모간체이스
    BOA("Bank of America","060"), // Bank of America
    BNP("비엔피파리바","061"), // 비엔피파리바
    ICBC("중국공상","062"), // 중국공상
    NFCF("산림조합중앙회","064"), //산림조합중앙회
    CCB("중국건설","067"), // 중국건설
    POST("우체국","071"), // 우체국
    KEB("하나","081"), // 하나
    SHINHAN("신한","088"), // 신한
    KBANK("케이뱅크","089"), // 케이뱅크
    KAKAO("카카오뱅크","090"), // 카카오뱅크
    YUANTA("유안타증권","209"), // 유안타증권
    KBSEC("KB 증권","218"), // KB 증권
    BNKFN("BNK 투자증권","224"), // BNK 투자증권
    KTB("KTB 투자증권","227"), // KTB 투자증권
    MIRAE("미래에셋대우","238"), // 미래에셋대우
    SAMSUNGPOP("삼성증권","240"), // 삼성증권
    BANKIS("한국투자증권","243"), // 한국투자증권
    NHQV("NH투자증권","247"), // NH투자증권
    IPROVEST("교보증권","261"), // 교보증권
    HIIB("하이투자증권","262"), // 하이투자증권
    HMSEC("현대차증권","263"), // 현대차증권
    KIWOOM("키음증권","264"), // 키음증권
    EBEST("키음증권","265"), // 이베스트투자증권
    SKS("SK 증권","266"), // SK 증권
    DAISHIN("대신증권","267"), // 대신증권
    HANHWA("한화투자증권","269"), // 한화투자증권
    HANAW("하나금융투자","270"), // 하나금융투자
    SHINHANINVEST("신한금융투자","278"), // 신한금융투자
    DBFI("DB금융투자","279"), // DB금융투자
    EUGENEFN("유진투자증권","280"), // 유진투자증권
    IMERITZ("메리츠종합금융증권","287"), // 메리츠종합금융증권
    KAKAOSEC("카카오증권","288"), // 바로투자증권 -> 카카오증권
    BOOKOOK("부국증권","290"), // 부국증권
    SHINYOUNG("신영증권","291"), // 신영증권
    CAPEFN("케이프투자증권","292"), // 케이프투자증권
    FOSS("한국포스증권","294"); // 한국포스증권


    private String bankName;
    private String bankCode;

    Bank(String bankName, String bankCode) {
        this.bankName = bankName;
        this.bankCode = bankCode;
    }

    public static Bank find(String bankCode) {
        Log.d("debug", "Selected Bank:" + bankCode);
        List<Bank> values = Arrays.asList(Bank.values());

        return values.stream()
                .filter(value -> value.getBankCode().equals(bankCode))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public String getBankName() {
        return bankName;
    }

    public String getBankCode() {
        return bankCode;
    }
}
