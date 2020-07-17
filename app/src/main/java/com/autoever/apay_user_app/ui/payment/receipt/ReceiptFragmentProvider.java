package com.autoever.apay_user_app.ui.payment.receipt;

import com.autoever.apay_user_app.ui.payment.price.PriceFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ReceiptFragmentProvider {

    @ContributesAndroidInjector
    abstract ReceiptFragment provideReceiptFragmentFactory();
}
