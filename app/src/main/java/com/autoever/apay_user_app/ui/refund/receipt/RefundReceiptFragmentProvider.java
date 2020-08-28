package com.autoever.apay_user_app.ui.refund.receipt;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RefundReceiptFragmentProvider {

    @ContributesAndroidInjector
    abstract RefundReceiptFragment provideRefundReceiptFragmentFactory();
}
