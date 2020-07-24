package com.autoever.apay_user_app.ui.payment.receipt;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
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
import com.autoever.apay_user_app.utils.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;


public class ReceiptFragment extends BaseFragment<FragmentReceiptBinding, PaymentViewModel> implements PaymentNavigator {

    public static final String TAG = ReceiptFragment.class.getSimpleName();

    private FragmentReceiptBinding mFragmentReceiptBinding;

    @Inject
    ViewModelProviderFactory factory;
    private PaymentViewModel mReceiptViewModel;

    public static ReceiptFragment newInstance(String storeName, Date createdDate, int amount, int userBalance) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월 dd일 HH:mm:ss");

        Bundle args = new Bundle();
        args.putString("storeName", storeName);
        args.putString("createdDate", simpleDateFormat.format(createdDate));
        args.putInt("amount", amount);
        args.putInt("userBalance", userBalance);
        ReceiptFragment fragment = new ReceiptFragment();
        fragment.setArguments(args);
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
        mFragmentReceiptBinding = getViewDataBinding();
        setup();
    }

    private void setup() {
        mFragmentReceiptBinding.shopName.setText(getArguments().getString("storeName"));
        mFragmentReceiptBinding.createdDate.setText(getArguments().getString("createdDate"));
        mFragmentReceiptBinding.purchaseAmount.setText(CommonUtils.formatToKRW(String.valueOf(getArguments().getInt("amount"))) + " P");
        mFragmentReceiptBinding.userBalance.setText(CommonUtils.formatToKRW(String.valueOf(getArguments().getInt("userBalance"))) + " P");
        mFragmentReceiptBinding.finishTextview.setOnClickListener(v -> {
            getBaseActivity().finish();
        });
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
    public void openAuthFragment() {

    }

    @Override
    public void showReceiptFragment(String storeName, Date createdDate, int amount, int userBalance) {

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