package com.autoever.apay_user_app.ui.card.use.history;

import com.autoever.apay_user_app.data.model.api.CardUseHistoryResponse;

import java.util.List;

public interface CardUseHistoryNavigator {

    void handleError(Throwable throwable);

    void onCompleteUpdatePaymentHistoryList();

    void noDataFound();

    void updateCardUseHistoryContent(List<CardUseHistoryResponse.CardUseHistory.Content> contentList);
}
