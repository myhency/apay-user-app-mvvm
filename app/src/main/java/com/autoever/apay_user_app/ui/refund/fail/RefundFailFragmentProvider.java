package com.autoever.apay_user_app.ui.refund.fail;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RefundFailFragmentProvider {

    @ContributesAndroidInjector
    abstract RefundFailFragment provideRefundFailFragmentFactory();
}
