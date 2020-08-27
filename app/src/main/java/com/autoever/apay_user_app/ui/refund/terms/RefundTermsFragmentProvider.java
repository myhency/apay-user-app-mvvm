package com.autoever.apay_user_app.ui.refund.terms;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RefundTermsFragmentProvider {

    @ContributesAndroidInjector
    abstract RefundTermsFragment provideRefundTermsFragmentFactory();
}
