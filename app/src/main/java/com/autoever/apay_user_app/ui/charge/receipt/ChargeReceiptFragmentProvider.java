package com.autoever.apay_user_app.ui.charge.receipt;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ChargeReceiptFragmentProvider {

    @ContributesAndroidInjector
    abstract ChargeReceiptFragment provideChargeReceiptFragmentFactory();
}
