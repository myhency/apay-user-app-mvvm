package com.autoever.apay_user_app.ui.card.use.receipt;

import com.autoever.apay_user_app.ui.card.use.history.CardUseHistoryFragment;
import com.autoever.apay_user_app.ui.card.use.history.CardUseHistoryFragmentModule;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PaymentRefundReadyReceiptProvider {

    @ContributesAndroidInjector
    abstract PaymentRefundReadyReceiptFragment providerPaymentRefundReadyReceiptFragmentFactory();
}
