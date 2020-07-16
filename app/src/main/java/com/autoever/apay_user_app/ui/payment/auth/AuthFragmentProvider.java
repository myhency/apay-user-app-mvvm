package com.autoever.apay_user_app.ui.payment.auth;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AuthFragmentProvider {

    @ContributesAndroidInjector
    abstract AuthFragment provideAuthFragmentFactory();
}
