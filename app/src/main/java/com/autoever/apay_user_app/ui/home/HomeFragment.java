package com.autoever.apay_user_app.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.androidnetworking.error.ANError;
import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentHomeBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;

import javax.inject.Inject;

public class HomeFragment extends BaseFragment<FragmentHomeBinding, HomeViewModel> implements HomeNavigator {

    public static final String TAG = HomeFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;

    private HomeViewModel mHomeViewModel;

    FragmentHomeBinding mFragmentHomeBinding;

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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHomeViewModel.setNavigator(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentHomeBinding = getViewDataBinding();

    }

    @Override
    public void handleError(Throwable throwable) {
        ANError anError = (ANError) throwable;
        Log.d("debug",anError.getMessage());
    }

    @Override
    public void openLoginActivity() {

    }

    @Override
    public void openPurchaseActivity() {

    }

    @Override
    public void openCardChargeActivity() {

    }

    @Override
    public void openCardInfoActivity() {

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

