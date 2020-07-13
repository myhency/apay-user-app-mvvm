package com.autoever.apay_user_app.di.builder;

import com.autoever.apay_user_app.ui.home.HomeFragmentProvider;
import com.autoever.apay_user_app.ui.main.MainActivity;
import com.autoever.apay_user_app.ui.payment.PaymentActivity;
import com.autoever.apay_user_app.ui.payment.price.PriceFragmentProvider;
import com.autoever.apay_user_app.ui.payment.scanner.CustomScannerActivity;
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

    @ContributesAndroidInjector(modules = {
            PriceFragmentProvider.class})
    abstract PaymentActivity bindPaymentActivity();

    @ContributesAndroidInjector
    abstract CustomScannerActivity bindCustomScannerActivity();
}
