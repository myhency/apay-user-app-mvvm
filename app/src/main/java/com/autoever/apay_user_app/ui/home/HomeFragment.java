package com.autoever.apay_user_app.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.androidnetworking.error.ANError;
import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentHomeBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.autoever.apay_user_app.ui.card.CardInfoActivity;
import com.autoever.apay_user_app.ui.charge.ChargeActivity;
import com.autoever.apay_user_app.ui.payment.PaymentActivity;

import javax.inject.Inject;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements HomeNavigator {

    public static final String TAG = HomeFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;

    private HomeViewModel mHomeViewModel;

    private FragmentHomeBinding mFragmentHomeBinding;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    public HomeViewModel getViewModel() {
        mHomeViewModel = ViewModelProviders.of(this, factory).get(HomeViewModel.class);
        return mHomeViewModel;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentHomeBinding = getViewDataBinding();
        mHomeViewModel.setNavigator(this);
        setup();
    }

    @Override
    public void handleError(Throwable throwable) {
        ANError anError = (ANError) throwable;
        Log.d("debug",anError.getMessage());
    }

    private void setup() {
        mFragmentHomeBinding.paymentButton.setOnClickListener(v -> {
            openPaymentActivity();
        });

        mFragmentHomeBinding.mainCardChargeButton.setOnClickListener(v -> {
            openCardChargeActivity();
        });

        mFragmentHomeBinding.mainCardInfoButton.setOnClickListener(v -> {
            openCardInfoActivity();
        });
    }

    @Override
    public void openLoginActivity() {

    }

    @Override
    public void openPaymentActivity() {
        Log.d("debug", "openPaymentActivity");
        Intent intent = PaymentActivity.newIntent(getActivity());
        startActivity(intent);
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("debug","HomeFragment resume");
        mHomeViewModel.loadUserBalance();
    }

    @Override
    public void openCardChargeActivity() {
        Log.d("debug", "openCardChargeActivity");
        Intent intent = ChargeActivity.newIntent(getActivity());
        startActivity(intent);
    }

    @Override
    public void openCardInfoActivity() {
        Log.d("debug", "openCardInfoActivity");
        Intent intent = CardInfoActivity.newIntent(getActivity());
        startActivity(intent);
    }

    @Override
    public void openBankAccountManagementActivity() {

    }

    @Override
    public void openCardUseHistoryActivity() {

    }

    @Override
    public void openSettingsActivity() {

    }

    @Override
    public void openNotificationActivity() {

    }

    @Override
    public void openNoticeBoardActivity() {

    }

    @Override
    public void openFaqActivity() {

    }

    @Override
    public void openUserProfileActivity() {

    }
}

