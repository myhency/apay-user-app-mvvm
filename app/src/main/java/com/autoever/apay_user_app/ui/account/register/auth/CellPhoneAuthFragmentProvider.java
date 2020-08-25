package com.autoever.apay_user_app.ui.account.register.auth;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CellPhoneAuthFragmentProvider {
    @ContributesAndroidInjector
    abstract CellPhoneAuthFragment provideCellPhoneAuthFragmentFactory();

}
