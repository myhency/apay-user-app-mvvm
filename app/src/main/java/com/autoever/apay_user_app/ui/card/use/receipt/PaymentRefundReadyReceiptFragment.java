package com.autoever.apay_user_app.ui.card.use.receipt;

import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.data.model.api.PaymentRefundReadyResponse;
import com.autoever.apay_user_app.databinding.FragmentPaymentRefundReadyReceiptBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.autoever.apay_user_app.utils.CommonUtils;

import java.text.SimpleDateFormat;

import javax.inject.Inject;


public class PaymentRefundReadyReceiptFragment extends BaseFragment<FragmentPaymentRefundReadyReceiptBinding, PaymentRefundReadyReceiptViewModel> implements PaymentRefundReadyReceiptNavigator {

    public static final String TAG = PaymentRefundReadyReceiptFragment.class.getSimpleName();

    private FragmentPaymentRefundReadyReceiptBinding mFragmentPaymentRefundReadyReceiptBinding;

    private PaymentRefundReadyResponse mPaymentRefundReadyResponse;

    @Inject
    ViewModelProviderFactory factory;

    private PaymentRefundReadyReceiptViewModel mPaymentRefundReadyReceiptViewModel;

    public static PaymentRefundReadyReceiptFragment newInstance(PaymentRefundReadyResponse paymentRefundReadyResponse) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd(E) HH:mm:ss");
        Bundle args = new Bundle();
        //TODO. 스토어 아이디가 아닌 이름으로 받을 것.
        args.putString("storeId", paymentRefundReadyResponse.getData().getStoreId().toString());
        args.putString("createdDate", simpleDateFormat.format(paymentRefundReadyResponse.getData().getCreatedDate()));
        args.putString("amount", CommonUtils.formatToKRW(String.valueOf(paymentRefundReadyResponse.getData().getAmount())) + " P");
        //TODO. 남은 잔액 받을 것.
        args.putString("balanceLeft", "12,000P");
        PaymentRefundReadyReceiptFragment fragment = new PaymentRefundReadyReceiptFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_payment_refund_ready_receipt;
    }

    @Override
    public PaymentRefundReadyReceiptViewModel getViewModel() {
        mPaymentRefundReadyReceiptViewModel = ViewModelProviders.of(this, factory)
                .get(PaymentRefundReadyReceiptViewModel.class);
        return mPaymentRefundReadyReceiptViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPaymentRefundReadyReceiptViewModel.setNavigator(this);
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
        mFragmentPaymentRefundReadyReceiptBinding = getViewDataBinding();
        
        setup();
    }

    private void setup() {
        mFragmentPaymentRefundReadyReceiptBinding.cancelDate.setText(getArguments().getString("createdDate"));
//        mFragmentPaymentRefundReadyReceiptBinding.expectedBalance.setText(getArguments().getString("balanceLeft"));
        mFragmentPaymentRefundReadyReceiptBinding.purchaseAmount.setText(getArguments().getString("amount"));
        mFragmentPaymentRefundReadyReceiptBinding.shopName.setText(getArguments().getString("storeId"));
        mFragmentPaymentRefundReadyReceiptBinding.finishTextview.setOnClickListener(v -> getBaseActivity().onFragmentDetached(TAG));
    }
}
