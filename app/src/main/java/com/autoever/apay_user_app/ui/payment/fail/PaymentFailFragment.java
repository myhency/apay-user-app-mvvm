package com.autoever.apay_user_app.ui.payment.fail;

import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentPaymentFailBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.autoever.apay_user_app.ui.payment.PaymentNavigator;
import com.autoever.apay_user_app.ui.payment.PaymentViewModel;

import java.util.Date;

import javax.inject.Inject;


public class PaymentFailFragment extends BaseFragment<FragmentPaymentFailBinding, PaymentViewModel> implements PaymentNavigator {

    public static final String TAG = PaymentFailFragment.class.getSimpleName();

    private FragmentPaymentFailBinding mFragmentPaymentFailBinding;
    private PaymentViewModel mPaymentFailViewModel;

    @Inject
    ViewModelProviderFactory factory;

    public static PaymentFailFragment newInstance() {
        Bundle args = new Bundle();
        PaymentFailFragment fragment = new PaymentFailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_payment_fail;
    }

    @Override
    public PaymentViewModel getViewModel() {
        mPaymentFailViewModel = ViewModelProviders.of(this, factory)
                .get(PaymentViewModel.class);
        return mPaymentFailViewModel;
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
        mFragmentPaymentFailBinding = getViewDataBinding();
        mPaymentFailViewModel.setNavigator(this);

        setup();
    }

    private void setup() {

        mFragmentPaymentFailBinding.finishTextview.setOnClickListener(v -> getBaseActivity().onFragmentDetached(TAG));
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

    @Override
    public void getQrUserDynamicData(String parsedQrString) {

    }
}