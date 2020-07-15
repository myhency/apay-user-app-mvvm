package com.autoever.apay_user_app.ui.payment.confirm;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.data.model.api.BalanceResponse;
import com.autoever.apay_user_app.databinding.FragmentPriceConfirmBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.autoever.apay_user_app.ui.payment.price.PriceFragment;
import com.autoever.apay_user_app.utils.CommonUtils;

import javax.inject.Inject;

public class PriceConfirmFragment extends BaseFragment<FragmentPriceConfirmBinding, PriceConfirmViewModel> implements PriceConfirmNavigator {

    public static final String TAG = PriceConfirmFragment.class.getSimpleName();

    private FragmentPriceConfirmBinding mFragmentPriceConfirmBinding;

    @Inject
    ViewModelProviderFactory factory;

    private PriceConfirmViewModel mPriceConfirmViewModel;

    public static PriceConfirmFragment newInstance(String shopName, int price) {
        Bundle args = new Bundle();
        args.putString("shopName", shopName);
        args.putInt("price", price);
        PriceConfirmFragment fragment = new PriceConfirmFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_price_confirm;
    }

    @Override
    public PriceConfirmViewModel getViewModel() {
        mPriceConfirmViewModel = ViewModelProviders.of(this, factory).get(PriceConfirmViewModel.class);
        return mPriceConfirmViewModel;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentPriceConfirmBinding = getViewDataBinding();
        setup();
    }

    private void setup() {
        //사용자가 입력한 가격을 출력.
        mFragmentPriceConfirmBinding.purchaseAmount.setText(
                CommonUtils.formatToKRW(String.valueOf(getArguments().getInt("price"))) + " P"
        );

        //가맹점 명을 출력.
        mFragmentPriceConfirmBinding.shopName.setText(
                getArguments().getString("shopName").substring(0, 7)
        );
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void goNext() {

    }
}