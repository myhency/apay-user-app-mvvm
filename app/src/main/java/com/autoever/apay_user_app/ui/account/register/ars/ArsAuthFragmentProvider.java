package com.autoever.apay_user_app.ui.account.register.ars;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ArsAuthFragmentProvider {

    @ContributesAndroidInjector
    abstract ArsAuthFragment provideArsAuthFragmentFactory();
}
