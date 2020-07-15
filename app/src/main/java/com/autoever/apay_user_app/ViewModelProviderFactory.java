package com.autoever.apay_user_app;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.ui.home.HomeViewModel;
import com.autoever.apay_user_app.ui.main.MainViewModel;
import com.autoever.apay_user_app.ui.payment.PaymentViewModel;
import com.autoever.apay_user_app.ui.payment.confirm.PriceConfirmViewModel;
import com.autoever.apay_user_app.ui.payment.price.PriceViewModel;
import com.autoever.apay_user_app.ui.payment.scanner.CustomScannerViewModel;
import com.autoever.apay_user_app.ui.splash.SplashViewModel;
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
        } else if (modelClass.isAssignableFrom(PriceViewModel.class)) {
            return (T) new PriceViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(CustomScannerViewModel.class)) {
            return (T) new CustomScannerViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(PriceViewModel.class)) {
            return (T) new PriceViewModel(dataManager, schedulerProvider);
        } else if (modelClass.isAssignableFrom(PriceConfirmViewModel.class)) {
            return (T) new PriceConfirmViewModel(dataManager, schedulerProvider);
        }

            throw new IllegalArgumentException("Unknown ViewModel class " + modelClass.getName());
    }
}
