package com.autoever.apay_user_app.di.builder;

import com.autoever.apay_user_app.ui.auth.AuthFragmentProvider;
import com.autoever.apay_user_app.ui.charge.ChargeActivity;
import com.autoever.apay_user_app.ui.charge.amount.AmountFragmentProvider;
import com.autoever.apay_user_app.ui.home.HomeFragmentProvider;
import com.autoever.apay_user_app.ui.main.MainActivity;
import com.autoever.apay_user_app.ui.payment.PaymentActivity;
import com.autoever.apay_user_app.ui.payment.confirm.PriceConfirmFragmentProvider;
import com.autoever.apay_user_app.ui.payment.price.PriceFragmentProvider;
import com.autoever.apay_user_app.ui.payment.receipt.ReceiptFragment;
import com.autoever.apay_user_app.ui.payment.receipt.ReceiptFragmentProvider;
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
            PriceFragmentProvider.class,
            PriceConfirmFragmentProvider.class,
            AuthFragmentProvider.class,
            ReceiptFragmentProvider.class})
    abstract PaymentActivity bindPaymentActivity();

    @ContributesAndroidInjector
    abstract CustomScannerActivity bindCustomScannerActivity();

    @ContributesAndroidInjector(modules = {
            AmountFragmentProvider.class
    })
    abstract ChargeActivity bindChargeActivity();
}
