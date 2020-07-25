package com.autoever.apay_user_app.ui.card.info;

import androidx.databinding.ObservableField;

import com.autoever.apay_user_app.data.model.api.CardUseHistoryResponse;

import java.util.Date;

public class HistoryItemViewModel {

    public final ObservableField<String> storeName;

    public final ObservableField<Long> amount;

    public final ObservableField<Date> date;

    public final ObservableField<String> paymentStatus;

    public final CardUseHistoryListener mListener;

    private final CardUseHistoryResponse.CardUseHistory.Content mHistory;

    public HistoryItemViewModel(CardUseHistoryResponse.CardUseHistory.Content history, CardUseHistoryListener listener) {
        this.mHistory = history;
        this.mListener = listener;
        storeName = new ObservableField<>(mHistory.getStoreName());
        amount = new ObservableField<>(mHistory.getAmount());
        date = new ObservableField<>(mHistory.getCreatedDate());
        paymentStatus = new ObservableField<>(mHistory.getPaymentStatus());
    }

    public void onItemClick() {

    }

    public interface CardUseHistoryListener {

        void onItemClick();
    }
}
