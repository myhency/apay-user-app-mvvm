package com.autoever.apay_user_app.ui.card.info;

import com.autoever.apay_user_app.data.model.api.CardUseHistoryResponse;

import java.util.List;

public interface CardInfoNavigator {

    void handleError(Throwable throwable);

    void updateCardUseHistoryContent(List<CardUseHistoryResponse.CardUseHistory.Content> contentList);
}
