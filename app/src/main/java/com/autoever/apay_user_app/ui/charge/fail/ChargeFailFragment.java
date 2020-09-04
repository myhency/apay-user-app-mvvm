package com.autoever.apay_user_app.ui.charge.fail;

import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentChargeFailBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;

import javax.inject.Inject;


public class ChargeFailFragment extends BaseFragment<FragmentChargeFailBinding, ChargeFailViewModel> {

    public static final String TAG = ChargeFailFragment.class.getSimpleName();

    private FragmentChargeFailBinding mFragmentChargeFailBinding;

    @Inject
    ViewModelProviderFactory factory;

    private ChargeFailViewModel mChargeFailViewModel;

    public static ChargeFailFragment newInstance() {
        Bundle args = new Bundle();
        ChargeFailFragment fragment = new ChargeFailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_charge_fail;
    }

    @Override
    public ChargeFailViewModel getViewModel() {
        mChargeFailViewModel = ViewModelProviders.of(this, factory)
                .get(ChargeFailViewModel.class);
        return mChargeFailViewModel;
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
        mFragmentChargeFailBinding = getViewDataBinding();

        setup();
    }

    private void setup() {

        mFragmentChargeFailBinding.finishTextview.setOnClickListener(v -> getBaseActivity().onFragmentDetached(TAG));
    }
}