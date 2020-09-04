package com.autoever.apay_user_app.ui.card.use.receipt;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PaymentRefundReadyReceiptProvider {

    @ContributesAndroidInjector
    abstract PaymentRefundReadyReceiptFragment providerPaymentRefundReadyReceiptFragmentFactory();
}
