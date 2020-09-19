package com.autoever.apay_user_app.ui.payment.fail;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PaymentFailFragmentProvider {

    @ContributesAndroidInjector
    abstract PaymentFailFragment providePaymentFailFragmentFactory();
}
