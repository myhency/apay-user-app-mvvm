package com.autoever.apay_user_app.ui.charge.fail;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ChargeFailFragmentProvider {

    @ContributesAndroidInjector
    abstract ChargeFailFragment provideChargeFailFragmentFactory();
}
