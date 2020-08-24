package com.autoever.apay_user_app.ui.account.register.terms;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AccountRegisterTermsFragmentProvider {
    @ContributesAndroidInjector
    abstract AccountRegisterTermsFragment provideAccountRegisterTermsFragmentFactory();
}
