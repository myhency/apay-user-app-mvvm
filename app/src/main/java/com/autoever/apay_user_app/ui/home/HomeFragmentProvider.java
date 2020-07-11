package com.autoever.apay_user_app.ui.home;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class HomeFragmentProvider {

    @ContributesAndroidInjector
    abstract HomeFragment provideHomeFragmentFactory();
}
