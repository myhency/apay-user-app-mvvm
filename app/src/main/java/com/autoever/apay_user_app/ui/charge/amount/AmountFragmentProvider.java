package com.autoever.apay_user_app.ui.charge.amount;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AmountFragmentProvider {

    @ContributesAndroidInjector
    abstract AmountFragment provideAmountFragmentFactory();
}
