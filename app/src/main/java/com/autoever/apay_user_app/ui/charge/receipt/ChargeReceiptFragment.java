package com.autoever.apay_user_app.ui.charge.receipt;

import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.data.model.api.ChargeDoResponse;
import com.autoever.apay_user_app.databinding.FragmentChargeReceiptBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.autoever.apay_user_app.utils.CommonUtils;

import java.text.SimpleDateFormat;

import javax.inject.Inject;


public class ChargeReceiptFragment extends BaseFragment<FragmentChargeReceiptBinding, ChargeReceiptViewModel> implements ChargeReceiptNavigator {

    public static final String TAG = ChargeReceiptFragment.class.getSimpleName();
    
    private FragmentChargeReceiptBinding mFragmentChargeReceiptBinding;

    @Inject
    ViewModelProviderFactory factory;
    
    private ChargeReceiptViewModel mChargeReceiptViewModel;
    
    public static ChargeReceiptFragment newInstance(ChargeDoResponse chargeDoResponse) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Bundle args = new Bundle();
        args.putLong("amount",chargeDoResponse.getData().getAmount());
        args.putLong("balance", chargeDoResponse.getData().getBalance());
        args.putString("createdDate", simpleDateFormat.format(chargeDoResponse.getData().getCreatedDate()));
        ChargeReceiptFragment fragment = new ChargeReceiptFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_charge_receipt;
    }

    @Override
    public ChargeReceiptViewModel getViewModel() {
        mChargeReceiptViewModel = ViewModelProviders.of(this, factory)
                .get(ChargeReceiptViewModel.class);
        return mChargeReceiptViewModel;
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
        mFragmentChargeReceiptBinding = getViewDataBinding();
        setup();
    }

    private void setup() {
        mFragmentChargeReceiptBinding.finishTextview.setOnClickListener(v -> getBaseActivity().onFragmentDetached(TAG));
        mFragmentChargeReceiptBinding.purchaseAmount.setText(CommonUtils.formatToKRW(String.valueOf(getArguments().getLong("amount"))) + " P");
        mFragmentChargeReceiptBinding.createdDate.setText(getArguments().getString("createdDate"));
        mFragmentChargeReceiptBinding.userBalanceAfter.setText(CommonUtils.formatToKRW(String.valueOf(getArguments().getLong("balance")))+ " P");
    }
}