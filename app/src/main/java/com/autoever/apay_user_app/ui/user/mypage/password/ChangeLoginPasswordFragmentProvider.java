package com.autoever.apay_user_app.ui.user.mypage.password;

import com.autoever.apay_user_app.ui.refund.receipt.RefundReceiptFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ChangeLoginPasswordFragmentProvider {
    @ContributesAndroidInjector
    abstract ChangeLoginPasswordFragment provideChangeLoginPasswordFragmentFactory();
}