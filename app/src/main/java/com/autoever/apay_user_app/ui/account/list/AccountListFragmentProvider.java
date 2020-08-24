package com.autoever.apay_user_app.ui.account.list;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class AccountListFragmentProvider {
    @ContributesAndroidInjector
    abstract AccountListFragment provideAccountListFragmentFactory();
}
