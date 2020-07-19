package com.autoever.apay_user_app.ui.charge.amount;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentAmountBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.autoever.apay_user_app.utils.CommonUtils;
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

    @SuppressLint("ClickableViewAccessibility")
    private void setup() {
        BottomSheetBehavior bottomSheetBehavior = BottomSheetBehavior.from(mFragmentAmountBinding.fixedAmountBottomSheet.bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
        bottomSheetBehavior.setPeekHeight(120);
        bottomSheetBehavior.setHideable(false);
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });

        mFragmentAmountBinding.fixedAmountBottomSheet.amount100.setOnClickListener(v -> {
            mFragmentAmountBinding.cardChargeEdittext.setText("1000000");
        });

        mFragmentAmountBinding.fixedAmountBottomSheet.amount50.setOnClickListener(v -> {
            mFragmentAmountBinding.cardChargeEdittext.setText("500000");
        });

        mFragmentAmountBinding.fixedAmountBottomSheet.amount30.setOnClickListener(v -> {
            mFragmentAmountBinding.cardChargeEdittext.setText("300000");
        });

        mFragmentAmountBinding.fixedAmountBottomSheet.amount10.setOnClickListener(v -> {
            mFragmentAmountBinding.cardChargeEdittext.setText("100000");
        });

        mFragmentAmountBinding.fixedAmountBottomSheet.amount5.setOnClickListener(v -> {
            mFragmentAmountBinding.cardChargeEdittext.setText("50000");
        });

        mFragmentAmountBinding.fixedAmountBottomSheet.amountMax.setOnClickListener(v -> {
            //TODO. 최대 200만원 까지?
        });

        mFragmentAmountBinding.balanceCurrency.setOnClickListener(v -> {
            if (mFragmentAmountBinding.balanceCurrency.getText().toString().equals("P")) return;

            mFragmentAmountBinding.balanceCurrency.setBackground(getResources().getDrawable(R.color.colorWhite));
            mFragmentAmountBinding.cardChargeEdittext.setText("");
        });

        mFragmentAmountBinding.cardChargeEdittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().isEmpty()) {
                    mFragmentAmountBinding.balanceCurrency.setText("P");
                    return;
                }

                mFragmentAmountBinding.balanceCurrency.setText("");
                mFragmentAmountBinding.balanceCurrency.setBackground(getResources().getDrawable(R.drawable.icon_clear));
//                mFragmentAmountBinding.balanceCurrency.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
                mFragmentAmountBinding.balanceCurrency.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);

                //원화화폐단위로 포맷하여 보여줌
                mFragmentAmountBinding.cardChargeEdittext.removeTextChangedListener(this);
                String cleanString = s.toString().replaceAll("[%s,.]", "");
                mFragmentAmountBinding.cardChargeEdittext.setText(CommonUtils.formatToKRW(cleanString));
                mFragmentAmountBinding.cardChargeEdittext.addTextChangedListener(this);
            }
        });

        //터치이벤트를 소모해서 키보드를 뜨지 않게 함.
        mFragmentAmountBinding.cardChargeEdittext.setOnTouchListener((v, event) -> true);
    }

    @Override
    public void handleError(Throwable throwable) {

    }
}