package com.autoever.apay_user_app.ui.refund.receipt;

import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentRefundReceiptBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.autoever.apay_user_app.utils.CommonUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;


public class RefundReceiptFragment extends BaseFragment<FragmentRefundReceiptBinding, RefundReceiptViewModel> implements RefundReceiptNavigator {

    public static final String TAG = RefundReceiptFragment.class.getSimpleName();

    private FragmentRefundReceiptBinding mFragmentRefundReceiptBinding;

    @Inject
    ViewModelProviderFactory factory;

    private RefundReceiptViewModel mRefundReceiptViewModel;

    public static RefundReceiptFragment newInstance(Long amount, Date createdDate) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd(E) HH:mm:ss");
        Bundle args = new Bundle();
        args.putString("createdDate", simpleDateFormat.format(createdDate));
        args.putString("amount", CommonUtils.formatToKRW(String.valueOf(amount)) + " P");
        RefundReceiptFragment fragment = new RefundReceiptFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_refund_receipt;
    }

    @Override
    public RefundReceiptViewModel getViewModel() {
        mRefundReceiptViewModel = ViewModelProviders.of(this, factory)
                .get(RefundReceiptViewModel.class);
        return mRefundReceiptViewModel;
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
        mFragmentRefundReceiptBinding = getViewDataBinding();
        mRefundReceiptViewModel.setNavigator(this);
        setup();
    }

    private void setup() {
        mFragmentRefundReceiptBinding.finishTextview.setOnClickListener(v -> {
            getBaseActivity().finish();
        });

        mFragmentRefundReceiptBinding.refundDate.setText(getArguments().getString("createdDate"));
        mFragmentRefundReceiptBinding.refundAmount.setText(getArguments().getString("amount"));
    }
}