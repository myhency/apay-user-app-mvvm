package com.autoever.apay_user_app.di.builder;

import com.autoever.apay_user_app.ui.home.HomeFragment;
import com.autoever.apay_user_app.ui.home.HomeFragmentProvider;
import com.autoever.apay_user_app.ui.main.MainActivity;
import com.autoever.apay_user_app.ui.splash.SplashActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract SplashActivity bindSplashActivity();

    @ContributesAndroidInjector(modules = {
            HomeFragmentProvider.class})
    abstract MainActivity bindMainActivity();
}
