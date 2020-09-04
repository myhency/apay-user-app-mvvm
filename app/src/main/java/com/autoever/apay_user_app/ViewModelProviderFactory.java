package com.autoever.apay_user_app;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.account.AccountViewModel;
import com.autoever.apay_user_app.ui.account.list.AccountListViewModel;
import com.autoever.apay_user_app.ui.account.register.AccountRegisterViewModel;
import com.autoever.apay_user_app.ui.account.register.account.BankAccountNumberViewModel;
import com.autoever.apay_user_app.ui.account.register.ars.ArsAuthViewModel;
import com.autoever.apay_user_app.ui.account.register.auth.CellPhoneAuthViewModel;
import com.autoever.apay_user_app.ui.account.register.bank.BankSelectViewModel;
import com.autoever.apay_user_app.ui.account.register.terms.AccountRegisterTermsViewModel;
import com.autoever.apay_user_app.ui.auth.AuthViewModel;
import com.autoever.apay_user_app.ui.card.CardInfoViewModel;
import com.autoever.apay_user_app.ui.card.info.CardInfoFragmentViewModel;
import com.autoever.apay_user_app.ui.card.use.CardUseViewModel;
import com.autoever.apay_user_app.ui.card.use.detail.CardUseDetailViewModel;
import com.autoever.apay_user_app.ui.card.use.history.CardUseHistoryViewModel;
import com.autoever.apay_user_app.ui.card.use.receipt.PaymentRefundReadyReceiptViewModel;
import com.autoever.apay_user_app.ui.charge.ChargeViewModel;
import com.autoever.apay_user_app.ui.charge.amount.AmountViewModel;
import com.autoever.apay_user_app.ui.charge.fail.ChargeFailViewModel;
import com.autoever.apay_user_app.ui.charge.receipt.ChargeReceiptViewModel;
import com.autoever.apay_user_app.ui.home.HomeViewModel;
import com.autoever.apay_user_app.ui.main.MainViewModel;
import com.autoever.apay_user_app.ui.payment.PaymentViewModel;
import com.autoever.apay_user_app.ui.refund.RefundViewModel;
import com.autoever.apay_user_app.ui.refund.amount.RefundAmountViewModel;
import com.autoever.apay_user_app.ui.refund.fail.RefundFailViewModel;
import com.autoever.apay_user_app.ui.refund.receipt.RefundReceiptViewModel;
import com.autoever.apay_user_app.ui.refund.terms.RefundTermsViewModel;
import com.autoever.apay_user_app.ui.splash.SplashViewModel;
import com.autoever.apay_user_app.ui.user.login.LoginViewModel;
import com.autoever.apay_user_app.ui.user.register.RegisterViewModel;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

import javax.inject.Inject;

public class ViewModelProviderFactory extends ViewModelProvider.NewInstanceFactory {

    private final DataManager dataManager;
    private final SchedulerProvider schedulerProvider;

    @Inject
    public ViewModelProviderFactory(DataManager dataManager, SchedulerProvider schedulerProvider) {
        this.dataManager = dataManager;
        this.schedulerProvider = schedulerProvider;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(SplashViewModel.class)) {
            return (T) new SplashViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(MainViewModel.class)) {
            return (T) new MainViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(HomeViewModel.class)) {
            return (T) new HomeViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(PaymentViewModel.class)) {
            return (T) new PaymentViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(AuthViewModel.class)) {
            return (T) new AuthViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(AmountViewModel.class)) {
            return (T) new AmountViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ChargeViewModel.class)) {
            return (T) new ChargeViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(RegisterViewModel.class)) {
            return (T) new RegisterViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(LoginViewModel.class)) {
            return (T) new LoginViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(CardInfoViewModel.class)) {
            return (T) new CardInfoViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(CardInfoFragmentViewModel.class)) {
            return (T) new CardInfoFragmentViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(CardUseViewModel.class)) {
            return (T) new CardUseViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(CardUseHistoryViewModel.class)) {
            return (T) new CardUseHistoryViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(CardUseDetailViewModel.class)) {
            return (T) new CardUseDetailViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(PaymentRefundReadyReceiptViewModel.class)) {
            return (T) new PaymentRefundReadyReceiptViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(AccountViewModel.class)) {
            return (T) new AccountViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(AccountListViewModel.class)) {
            return (T) new AccountListViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(AccountRegisterViewModel.class)) {
            return (T) new AccountRegisterViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(AccountRegisterTermsViewModel.class)) {
            return (T) new AccountRegisterTermsViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(CellPhoneAuthViewModel.class)) {
            return (T) new CellPhoneAuthViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(BankSelectViewModel.class)) {
            return (T) new BankSelectViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(BankAccountNumberViewModel.class)) {
            return (T) new BankAccountNumberViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ChargeReceiptViewModel.class)) {
            return (T) new ChargeReceiptViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(RefundViewModel.class)) {
            return (T) new RefundViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(RefundTermsViewModel.class)) {
            return (T) new RefundTermsViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(RefundAmountViewModel.class)) {
            return (T) new RefundAmountViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(RefundReceiptViewModel.class)) {
            return (T) new RefundReceiptViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ArsAuthViewModel.class)) {
            return (T) new ArsAuthViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(ChargeFailViewModel.class)) {
            return (T) new ChargeFailViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(RefundFailViewModel.class)) {
            return (T) new RefundFailViewModel(dataManager, schedulerProvider);
        }

        throw new IllegalArgumentException("Unknown ViewModel class " + modelClass.getName());
    }
}
