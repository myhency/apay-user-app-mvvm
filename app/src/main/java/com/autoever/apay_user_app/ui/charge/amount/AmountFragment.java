package com.autoever.apay_user_app.ui.charge.amount;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentAmountBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import javax.inject.Inject;

public class AmountFragment extends BaseFragment<FragmentAmountBinding, AmountViewModel> implements AmountNavigator {

    public static final String TAG = AmountFragment.class.getSimpleName();

    private FragmentAmountBinding mFragmentAmountBinding;

    @Inject
    ViewModelProviderFactory factory;

    private AmountViewModel mAmountViewModel;

    public static AmountFragment newInstance() {
        Bundle args = new Bundle();
        AmountFragment fragment = new AmountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_amount;
    }

    @Override
    public AmountViewModel getViewModel() {
        mAmountViewModel = ViewModelProviders.of(this, factory)
                .get(AmountViewModel.class);
        return mAmountViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentAmountBinding = getViewDataBinding();
        setup();
    }

    private void setup() {
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(mFragmentAmountBinding.fixedAmountBottomSheet.bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior.setPeekHeight(340);
        bottomSheetBehavior.setHideable(false);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });
    }

    @Override
    public void handleError(Throwable throwable) {

    }
}