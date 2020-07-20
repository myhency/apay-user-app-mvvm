package com.autoever.apay_user_app.ui.charge.amount;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

import org.json.JSONException;
import org.json.JSONObject;

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
        mFragmentAmountBinding = getViewDataBinding();
        mAmountViewModel.loadUserBalance();
        setup();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setup() {
        //EditText 세팅.
        mFragmentAmountBinding.clearIcon.setVisibility(View.INVISIBLE);
        mFragmentAmountBinding.balanceCurrency.setVisibility(View.VISIBLE);

        //BottomSheet 세팅.
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

        //리스너 세팅.
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

        mFragmentAmountBinding.clearIcon.setOnClickListener(v -> {
            mFragmentAmountBinding.clearIcon.setVisibility(View.INVISIBLE);
            mFragmentAmountBinding.balanceCurrency.setVisibility(View.VISIBLE);
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
                    mFragmentAmountBinding.balanceCurrency.setVisibility(View.VISIBLE);
                    return;
                }

                //원화화폐단위로 포맷하여 보여줌
                mFragmentAmountBinding.cardChargeEdittext.removeTextChangedListener(this);
                String cleanString = s.toString().replaceAll("[%s,.]", "");
                mFragmentAmountBinding.cardChargeEdittext.setText(CommonUtils.formatToKRW(cleanString));
                mFragmentAmountBinding.cardChargeEdittext.addTextChangedListener(this);

                //전체 삭제 아이콘을 출력.
                mFragmentAmountBinding.clearIcon.setVisibility(View.VISIBLE);
                mFragmentAmountBinding.balanceCurrency.setVisibility(View.INVISIBLE);
            }
        });

        //터치이벤트를 소모해서 키보드를 뜨지 않게 함.
        mFragmentAmountBinding.cardChargeEdittext.setOnTouchListener((v, event) -> true);

        mFragmentAmountBinding.finishTextview.setOnClickListener(v -> goNext());
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void goNext() {
        Log.d("debug", "Amount confirmed");
        JSONObject data = new JSONObject();
        try {
            data.put("amount", mFragmentAmountBinding.cardChargeEdittext.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getBaseActivity().onReceivedMessageFromFragment(TAG, data);
        getBaseActivity().onFragmentDetached(TAG);
    }


}