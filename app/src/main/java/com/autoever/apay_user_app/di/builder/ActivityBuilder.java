package com.autoever.apay_user_app.di.builder;

import com.autoever.apay_user_app.ui.account.AccountActivity;
import com.autoever.apay_user_app.ui.account.list.AccountListFragmentProvider;
import com.autoever.apay_user_app.ui.account.register.AccountRegisterActivity;
import com.autoever.apay_user_app.ui.account.register.account.BankAccountNumberFragmentProvider;
import com.autoever.apay_user_app.ui.account.register.ars.ArsAuthFragmentProvider;
import com.autoever.apay_user_app.ui.account.register.auth.CellPhoneAuthFragmentProvider;
import com.autoever.apay_user_app.ui.account.register.bank.BankSelectFragmentProvider;
import com.autoever.apay_user_app.ui.account.register.terms.AccountRegisterTermsFragmentProvider;
import com.autoever.apay_user_app.ui.auth.AuthFragmentProvider;
import com.autoever.apay_user_app.ui.card.CardInfoActivity;
import com.autoever.apay_user_app.ui.card.info.CardInfoFragmentProvider;
import com.autoever.apay_user_app.ui.card.use.CardUseActivity;
import com.autoever.apay_user_app.ui.card.use.detail.CardUseDetailFragmentProvider;
import com.autoever.apay_user_app.ui.card.use.history.CardUseHistoryFragmentProvider;
import com.autoever.apay_user_app.ui.card.use.receipt.PaymentRefundReadyReceiptProvider;
import com.autoever.apay_user_app.ui.charge.ChargeActivity;
import com.autoever.apay_user_app.ui.charge.amount.AmountFragmentProvider;
import com.autoever.apay_user_app.ui.charge.fail.ChargeFailFragmentProvider;
import com.autoever.apay_user_app.ui.charge.receipt.ChargeReceiptFragmentProvider;
import com.autoever.apay_user_app.ui.home.HomeFragmentProvider;
import com.autoever.apay_user_app.ui.main.MainActivity;
import com.autoever.apay_user_app.ui.payment.PaymentActivity;
import com.autoever.apay_user_app.ui.payment.confirm.PriceConfirmFragmentProvider;
import com.autoever.apay_user_app.ui.payment.price.PriceFragmentProvider;
import com.autoever.apay_user_app.ui.payment.receipt.ReceiptFragmentProvider;
import com.autoever.apay_user_app.ui.payment.scanner.CustomScannerActivity;
import com.autoever.apay_user_app.ui.refund.RefundActivity;
import com.autoever.apay_user_app.ui.refund.amount.RefundAmountFragmentProvider;
import com.autoever.apay_user_app.ui.refund.fail.RefundFailFragmentProvider;
import com.autoever.apay_user_app.ui.refund.receipt.RefundReceiptFragmentProvider;
import com.autoever.apay_user_app.ui.refund.terms.RefundTermsFragmentProvider;
import com.autoever.apay_user_app.ui.splash.SplashActivity;
import com.autoever.apay_user_app.ui.user.login.LoginActivity;
import com.autoever.apay_user_app.ui.user.register.RegisterActivity;
import com.autoever.apay_user_app.ui.user.register.form.RegisterFormFragmentProvider;
import com.autoever.apay_user_app.ui.user.register.password.PasswordFragmentProvider;
import com.autoever.apay_user_app.ui.user.register.terms.TermsOfServiceFragmentProvider;

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
            AmountFragmentProvider.class,
            AuthFragmentProvider.class,
            ChargeReceiptFragmentProvider.class,
            ChargeFailFragmentProvider.class
    })
    abstract ChargeActivity bindChargeActivity();

    @ContributesAndroidInjector(modules = {
            TermsOfServiceFragmentProvider.class,
            RegisterFormFragmentProvider.class,
            PasswordFragmentProvider.class
    })
    abstract RegisterActivity bindRegisterActivity();

    @ContributesAndroidInjector
    abstract LoginActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = {
            CardInfoFragmentProvider.class
    })
    abstract CardInfoActivity bindCardInfoActivity();

    @ContributesAndroidInjector(modules = {
            CardUseHistoryFragmentProvider.class,
            CardUseDetailFragmentProvider.class,
            PaymentRefundReadyReceiptProvider.class
    })
    abstract CardUseActivity bindCardUseActivity();

    @ContributesAndroidInjector(modules = {
            AccountListFragmentProvider.class
    })
    abstract AccountActivity bindAccountActivity();

    @ContributesAndroidInjector(modules = {
            AccountRegisterTermsFragmentProvider.class,
            CellPhoneAuthFragmentProvider.class,
            BankSelectFragmentProvider.class,
            BankAccountNumberFragmentProvider.class,
            ArsAuthFragmentProvider.class
    })
    abstract AccountRegisterActivity bindAccountRegisterActivity();

    @ContributesAndroidInjector(modules = {
            RefundTermsFragmentProvider.class,
            RefundAmountFragmentProvider.class,
            AuthFragmentProvider.class,
            RefundReceiptFragmentProvider.class,
            RefundFailFragmentProvider.class
    })
    abstract RefundActivity bindRefundActivity();
}
