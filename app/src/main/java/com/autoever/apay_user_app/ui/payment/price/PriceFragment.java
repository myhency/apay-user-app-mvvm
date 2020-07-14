package com.autoever.apay_user_app.ui.payment.price;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentPriceBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;

import javax.inject.Inject;

public class PriceFragment extends BaseFragment<FragmentPriceBinding, PriceViewModel> implements PriceNavigator {

    public static final String TAG = PriceFragment.class.getSimpleName();

    private FragmentPriceBinding mFragmentPriceBinding;

    @Inject
    ViewModelProviderFactory factory;
    private PriceViewModel mPriceViewModel;

    public static PriceFragment newInstance(String shopName) {
        Bundle args = new Bundle();
        args.putString("shopName", shopName);
        PriceFragment fragment = new PriceFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_price;
    }

    @Override
    public PriceViewModel getViewModel() {
        mPriceViewModel = ViewModelProviders.of(this, factory).get(PriceViewModel.class);
        return mPriceViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentPriceBinding = getViewDataBinding();
    }

    @Override
    public void handleError(Throwable throwable) {

    }
}