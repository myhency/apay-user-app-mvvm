package com.autoever.apay_user_app.ui.user.register.form;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class RegisterFormFragmentProvider {

    @ContributesAndroidInjector
    abstract RegisterFormFragment provideRegisterFormFragmentFactory();
}
