package com.autoever.apay_user_app.ui.card.info;

import androidx.databinding.ObservableField;

import com.autoever.apay_user_app.data.model.api.CardUseHistoryResponse;
import com.autoever.apay_user_app.utils.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HistoryItemViewModel {

    public final ObservableField<String> storeName;

    public final ObservableField<String> amount;

    public final ObservableField<String> date;

    public final ObservableField<String> paymentStatus;

    public final CardUseHistoryListener mListener;

    private final CardUseHistoryResponse.CardUseHistory.Content mHistory;

    public HistoryItemViewModel(CardUseHistoryResponse.CardUseHistory.Content history, CardUseHistoryListener listener) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");

        this.mHistory = history;
        this.mListener = listener;
        storeName = new ObservableField<>(mHistory.getStoreName());
        amount = new ObservableField<>(CommonUtils.formatToKRW(String.valueOf(mHistory.getAmount())) + " P");
        date = new ObservableField<>(simpleDateFormat.format(mHistory.getCreatedDate()));
        paymentStatus = new ObservableField<>(PaymentStatus.find(mHistory.getPaymentStatus()).getDisplayValue());
    }

    public void onItemClick() {

    }

    public interface CardUseHistoryListener {

        void onItemClick();
    }
}
