package com.autoever.apay_user_app.ui.common;

import android.util.Log;

import com.autoever.apay_user_app.R;

import java.util.Arrays;
import java.util.List;

public enum Bank {

    KDB("산업은행", "002", R.drawable.bank_has_no_image),
    IBK("기업은행", "003", R.drawable.ibk_logo),
    KB("국민은행", "004", R.drawable.kookmin_logo),
    SH("수협, 수협중앙회", "007", R.drawable.bank_has_no_image),
    NH("농협", "011", R.drawable.nonghyub_logo),
    WOORI("우리은행", "020", R.drawable.woori_logo),
    SC("SC제일은행", "023", R.drawable.bank_has_no_image),
    CITI("씨티은행", "027", R.drawable.city_logo),
    DGB("대구은행", "031", R.drawable.bank_has_no_image),
    BNK("부산은행", "032", R.drawable.bank_has_no_image),
    KJB("광주은행", "034", R.drawable.bank_has_no_image),
    JEJU("제주은행", "035", R.drawable.bank_has_no_image),
    JB("전북은행", "037", R.drawable.bank_has_no_image),
    KNB("경남은행", "039", R.drawable.bank_has_no_image),
    KFCC("새마을(금고)", "045", R.drawable.bank_has_no_image),
    CU("신협", "048", R.drawable.bank_has_no_image),
    FSB("저축은행중앙회", "050", R.drawable.bank_has_no_image),
    HSBC("HSBC", "054", R.drawable.bank_has_no_image),
    DB("도이치", "055", R.drawable.bank_has_no_image),
    JPMORGAN("제이피모간체이스","057", R.drawable.bank_has_no_image), // 제이피모간체이스
    BOA("Bank of America","060", R.drawable.bank_has_no_image), // Bank of America
    BNP("비엔피파리바","061", R.drawable.bank_has_no_image), // 비엔피파리바
    ICBC("중국공상","062", R.drawable.bank_has_no_image), // 중국공상
    NFCF("산림조합중앙회","064", R.drawable.bank_has_no_image), //산림조합중앙회
    CCB("중국건설","067", R.drawable.bank_has_no_image), // 중국건설
    POST("우체국","071", R.drawable.bank_has_no_image), // 우체국
    KEB("하나","081", R.drawable.hana_logo), // 하나
    SHINHAN("신한","088", R.drawable.shinhan_logo), // 신한
    KBANK("케이뱅크","089", R.drawable.kbank_logo), // 케이뱅크
    KAKAO("카카오뱅크","090", R.drawable.kakao_bank_logo), // 카카오뱅크
    YUANTA("유안타증권","209", R.drawable.bank_has_no_image), // 유안타증권
    KBSEC("KB 증권","218", R.drawable.bank_has_no_image), // KB 증권
    BNKFN("BNK 투자증권","224", R.drawable.bank_has_no_image), // BNK 투자증권
    KTB("KTB 투자증권","227", R.drawable.bank_has_no_image), // KTB 투자증권
    MIRAE("미래에셋대우","238", R.drawable.bank_has_no_image), // 미래에셋대우
    SAMSUNGPOP("삼성증권","240", R.drawable.bank_has_no_image), // 삼성증권
    BANKIS("한국투자증권","243", R.drawable.bank_has_no_image), // 한국투자증권
    NHQV("NH투자증권","247", R.drawable.bank_has_no_image), // NH투자증권
    IPROVEST("교보증권","261", R.drawable.bank_has_no_image), // 교보증권
    HIIB("하이투자증권","262", R.drawable.bank_has_no_image), // 하이투자증권
    HMSEC("현대차증권","263", R.drawable.bank_has_no_image), // 현대차증권
    KIWOOM("키음증권","264", R.drawable.bank_has_no_image), // 키음증권
    EBEST("키음증권","265", R.drawable.bank_has_no_image), // 이베스트투자증권
    SKS("SK 증권","266", R.drawable.bank_has_no_image), // SK 증권
    DAISHIN("대신증권","267", R.drawable.bank_has_no_image), // 대신증권
    HANHWA("한화투자증권","269", R.drawable.bank_has_no_image), // 한화투자증권
    HANAW("하나금융투자","270", R.drawable.bank_has_no_image), // 하나금융투자
    SHINHANINVEST("신한금융투자","278", R.drawable.bank_has_no_image), // 신한금융투자
    DBFI("DB금융투자","279", R.drawable.bank_has_no_image), // DB금융투자
    EUGENEFN("유진투자증권","280", R.drawable.bank_has_no_image), // 유진투자증권
    IMERITZ("메리츠종합금융증권","287", R.drawable.bank_has_no_image), // 메리츠종합금융증권
    KAKAOSEC("카카오증권","288", R.drawable.bank_has_no_image), // 바로투자증권 -> 카카오증권
    BOOKOOK("부국증권","290", R.drawable.bank_has_no_image), // 부국증권
    SHINYOUNG("신영증권","291", R.drawable.bank_has_no_image), // 신영증권
    CAPEFN("케이프투자증권","292", R.drawable.bank_has_no_image), // 케이프투자증권
    FOSS("한국포스증권","294", R.drawable.bank_has_no_image); // 한국포스증권


    private String bankName;
    private String bankCode;
    private int logo;

    Bank(String bankName, String bankCode, int logo) {
        this.bankName = bankName;
        this.bankCode = bankCode;
        this.logo = logo;

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

    public int getLogo() {
        return logo;
    }
}
