package com.autoever.apay_user_app.ui.account.register.bank;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BankSelectFragmentProvider {
    @ContributesAndroidInjector
    abstract BankSelectFragment provideBankSelectFragmentFactory();
}
