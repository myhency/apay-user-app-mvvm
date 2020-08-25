package com.autoever.apay_user_app.ui.account.register.account;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentBankAccountNumberBinding;
import com.autoever.apay_user_app.ui.account.register.terms.AccountRegisterTermsFragment;
import com.autoever.apay_user_app.ui.account.register.terms.AccountRegisterTermsViewModel;
import com.autoever.apay_user_app.ui.base.BaseFragment;

import javax.inject.Inject;


public class BankAccountNumberFragment extends BaseFragment<FragmentBankAccountNumberBinding, BankAccountNumberViewModel> implements BankAccountNumberNavigator {

    public static final String TAG = BankAccountNumberFragment.class.getSimpleName();

    private FragmentBankAccountNumberBinding mFragmentBankAccountNumberBinding;

    @Inject
    ViewModelProviderFactory factory;

    private BankAccountNumberViewModel mBankAccountNumberViewModel;

    public static BankAccountNumberFragment newInstance() {
        Bundle args = new Bundle();
        BankAccountNumberFragment fragment = new BankAccountNumberFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_bank_account_number;
    }

    @Override
    public BankAccountNumberViewModel getViewModel() {
        mBankAccountNumberViewModel = ViewModelProviders.of(this, factory)
                .get(BankAccountNumberViewModel.class);
        return mBankAccountNumberViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentBankAccountNumberBinding = getViewDataBinding();
        setup();
    }

    private void setup() {

    }
}