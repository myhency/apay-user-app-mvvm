package com.autoever.apay_user_app.ui.account.register.terms;

import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentAccountRegisterTermsBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;

import javax.inject.Inject;


public class AccountRegisterTermsFragment extends BaseFragment<FragmentAccountRegisterTermsBinding, AccountRegisterTermsViewModel> implements AccountRegisterTermsNavigator {

    public static final String TAG = AccountRegisterTermsFragment.class.getSimpleName();
    
    private FragmentAccountRegisterTermsBinding mFragmentAccountRegisterTermsBinding;

    @Inject
    ViewModelProviderFactory factory;
    private AccountRegisterTermsViewModel mAccountRegisterTermsViewModel;
    
    public static AccountRegisterTermsFragment newInstance() {
        Bundle args = new Bundle();
        AccountRegisterTermsFragment fragment = new AccountRegisterTermsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_account_register_terms;
    }

    @Override
    public AccountRegisterTermsViewModel getViewModel() {
        mAccountRegisterTermsViewModel = ViewModelProviders.of(this, factory)
                .get(AccountRegisterTermsViewModel.class);
        return mAccountRegisterTermsViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //back button 을 눌렀을 경우 Activity 를 종료한다.
        getBaseActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getBaseActivity().finish();
            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentAccountRegisterTermsBinding = getViewDataBinding();
        setup();
    }

    private void setup() {
        mFragmentAccountRegisterTermsBinding.finishTextview.setOnClickListener(v -> {
            getBaseActivity().onFragmentDetached(TAG);
        });
    }
}
