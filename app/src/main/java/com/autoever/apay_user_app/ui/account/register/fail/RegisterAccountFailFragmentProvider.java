package com.autoever.apay_user_app.ui.account.register.fail;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RegisterAccountFailFragmentProvider {

    @ContributesAndroidInjector
    abstract RegisterAccountFailFragment provideRegisterAccountFailFragmentFactory();
}
