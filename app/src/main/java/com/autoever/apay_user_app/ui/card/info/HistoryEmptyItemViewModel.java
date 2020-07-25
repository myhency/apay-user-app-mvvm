package com.autoever.apay_user_app.ui.card.info;

public class HistoryEmptyItemViewModel {

    private HistoryEmptyItemViewModelListener mListener;

    public HistoryEmptyItemViewModel(HistoryEmptyItemViewModelListener listener) {
        this.mListener = listener;
    }

    public void onRetryClick() { mListener.onRetryClick(); }

    public interface HistoryEmptyItemViewModelListener {

        void onRetryClick();
    }
}
