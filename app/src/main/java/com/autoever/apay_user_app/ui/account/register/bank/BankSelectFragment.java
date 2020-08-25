package com.autoever.apay_user_app.ui.account.register.bank;

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
import com.autoever.apay_user_app.databinding.FragmentBankSelectBinding;
import com.autoever.apay_user_app.ui.account.register.terms.AccountRegisterTermsFragment;
import com.autoever.apay_user_app.ui.base.BaseFragment;

import javax.inject.Inject;


public class BankSelectFragment extends BaseFragment<FragmentBankSelectBinding, BankSelectViewModel> implements BankSelectNavigator {

    public static final String TAG = BankSelectFragment.class.getSimpleName();

    private FragmentBankSelectBinding mFragmentBankSelectBinding;

    @Inject
    ViewModelProviderFactory factory;
    private BankSelectViewModel mBankSelectViewModel;

    public static BankSelectFragment newInstance() {
        Bundle args = new Bundle();
        BankSelectFragment fragment = new BankSelectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_bank_select;
    }

    @Override
    public BankSelectViewModel getViewModel() {
        mBankSelectViewModel = ViewModelProviders.of(this, factory)
                .get(BankSelectViewModel.class);
        return mBankSelectViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentBankSelectBinding = getViewDataBinding();
        setup();
    }

    private void setup() {
        mFragmentBankSelectBinding.finishTextview.setOnClickListener(v -> {
            getBaseActivity().onFragmentDetached(TAG);
        });
    }
}