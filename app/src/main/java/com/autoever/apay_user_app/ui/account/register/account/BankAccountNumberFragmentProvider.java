package com.autoever.apay_user_app.ui.account.register.account;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BankAccountNumberFragmentProvider {
    @ContributesAndroidInjector
    abstract BankAccountNumberFragment provideBankAccountNumberFragmentFactory();
}
