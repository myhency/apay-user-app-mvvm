package com.autoever.apay_user_app.ui.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentAuthBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.autoever.apay_user_app.ui.home.HomeFragment;
import com.autoever.apay_user_app.ui.payment.PaymentActivity;

import javax.inject.Inject;


public class AuthFragment extends BaseFragment<FragmentAuthBinding, AuthViewModel> implements AuthNavigator {

    public static final String TAG = AuthFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;

    private AuthViewModel mAuthViewModel;
    private FragmentAuthBinding mFragmentAuthBinding;

    public static AuthFragment newInstance() {
        Bundle args = new Bundle();
        AuthFragment fragment = new AuthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_auth;
    }

    @Override
    public AuthViewModel getViewModel() {
        mAuthViewModel = ViewModelProviders.of(this, factory).get(AuthViewModel.class);
        return mAuthViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentAuthBinding = getViewDataBinding();
        mAuthViewModel.setNavigator(this);
        setup();
    }

    private void setup() {

    }
}