package com.autoever.apay_user_app.ui.refund.amount;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RefundAmountFragmentProvider {

    @ContributesAndroidInjector
    abstract RefundAmountFragment provideRefundAmountFragmentFactory();
}
