package com.autoever.apay_user_app.ui.card.use;

public interface CardUseNavigator {

    void handleError(Throwable throwable);

    void openCardUseDetailFragment(Long paymentHistoryId);
}
