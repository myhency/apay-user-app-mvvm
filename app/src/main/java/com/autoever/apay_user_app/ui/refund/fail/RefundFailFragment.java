package com.autoever.apay_user_app.ui.refund.fail;

import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentRefundFailBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;

import javax.inject.Inject;


public class RefundFailFragment extends BaseFragment<FragmentRefundFailBinding, RefundFailViewModel> {

    public static final String TAG = RefundFailFragment.class.getSimpleName();

    private FragmentRefundFailBinding mFragmentRefundFailBinding;

    @Inject
    ViewModelProviderFactory factory;

    private RefundFailViewModel mRefundFailViewModel;

    public static RefundFailFragment newInstance() {
        Bundle args = new Bundle();
        RefundFailFragment fragment = new RefundFailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_refund_fail;
    }

    @Override
    public RefundFailViewModel getViewModel() {
        mRefundFailViewModel = ViewModelProviders.of(this, factory)
                .get(RefundFailViewModel.class);
        return mRefundFailViewModel;
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
        mFragmentRefundFailBinding = getViewDataBinding();

        setup();
    }

    private void setup() {

        mFragmentRefundFailBinding.finishTextview.setOnClickListener(v -> getBaseActivity().onFragmentDetached(TAG));
    }
}