package com.autoever.apay_user_app.ui.user.register.password;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class PasswordFragmentProvider {

    @ContributesAndroidInjector
    abstract PasswordFragment providePasswordFragmentFactory();
}
