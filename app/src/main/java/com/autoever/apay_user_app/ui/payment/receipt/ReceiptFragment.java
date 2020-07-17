package com.autoever.apay_user_app.ui.payment.receipt;

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
import com.autoever.apay_user_app.databinding.FragmentReceiptBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.autoever.apay_user_app.ui.payment.PaymentNavigator;
import com.autoever.apay_user_app.ui.payment.PaymentViewModel;

import javax.inject.Inject;


public class ReceiptFragment extends BaseFragment<FragmentReceiptBinding, PaymentViewModel> implements PaymentNavigator {

    public static final String TAG = ReceiptFragment.class.getSimpleName();

    private FragmentReceiptBinding mFragmentReceiptBinding;

    @Inject
    ViewModelProviderFactory factory;
    private PaymentViewModel mReceiptViewModel;

    public static ReceiptFragment newInstance(String storeName, String createdDate, int amount, int userBalance) {
        Bundle args = new Bundle();
        args.putString("storeName", storeName);
        args.putString("createdDate", createdDate);
        args.putInt("amount", amount);
        args.putInt("userBalance", userBalance);
        ReceiptFragment fragment = new ReceiptFragment();
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_receipt;
    }

    @Override
    public PaymentViewModel getViewModel() {
        mReceiptViewModel = ViewModelProviders.of(this, factory).get(PaymentViewModel.class);
        return mReceiptViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentReceiptBinding = getViewDataBinding();
        setup();
    }

    private void setup() {
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void openCustomScannerActivity() {

    }

    @Override
    public void showPriceFragment(String shopCode) {

    }

    @Override
    public void showPriceConfirmFragment(String shopCode, int price) {

    }

    @Override
    public void showAuthFragment() {

    }

    @Override
    public void showReceiptFragment(String storeName, String createdDate, int amount, int userBalance) {

    }

    @Override
    public void doPaymentReady() {

    }

    @Override
    public void doPaymentDo(String userId, String storeId, String tokenSystemId, int amount, String paymentId, String identifier) {

    }

    @Override
    public void goNext() {

    }
}