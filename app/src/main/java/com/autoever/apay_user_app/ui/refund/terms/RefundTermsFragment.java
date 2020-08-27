package com.autoever.apay_user_app.ui.refund.terms;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentRefundTermsBinding;
import com.autoever.apay_user_app.ui.account.register.terms.AccountRegisterTermsFragment;
import com.autoever.apay_user_app.ui.base.BaseFragment;

import javax.inject.Inject;


public class RefundTermsFragment extends BaseFragment<FragmentRefundTermsBinding, RefundTermsViewModel> implements RefundTermsNavigator {

    public static final String TAG = RefundTermsFragment.class.getSimpleName();

    private FragmentRefundTermsBinding mFragmentRefundTermsBinding;

    @Inject
    ViewModelProviderFactory factory;

    private RefundTermsViewModel mRefundTermsViewModel;
    public static RefundTermsFragment newInstance() {
        Bundle args = new Bundle();
        RefundTermsFragment fragment = new RefundTermsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_refund_terms;
    }

    @Override
    public RefundTermsViewModel getViewModel() {
        mRefundTermsViewModel = ViewModelProviders.of(this, factory)
                .get(RefundTermsViewModel.class);
        return mRefundTermsViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        mFragmentRefundTermsBinding = getViewDataBinding();
        setup();
    }

    private void setup() {
        mFragmentRefundTermsBinding.finishTextview.setOnClickListener(v->{
            getBaseActivity().onFragmentDetached(TAG);
        });
    }
}