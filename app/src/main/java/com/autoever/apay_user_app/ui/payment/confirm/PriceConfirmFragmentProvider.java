package com.autoever.apay_user_app.ui.payment.confirm;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PriceConfirmFragmentProvider {

    @ContributesAndroidInjector
    abstract PriceConfirmFragment providerPriceConfirmFactory();
}

