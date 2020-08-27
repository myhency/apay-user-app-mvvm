package com.autoever.apay_user_app.ui.refund.amount;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentRefundAmountBinding;
import com.autoever.apay_user_app.ui.account.register.terms.AccountRegisterTermsFragment;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.autoever.apay_user_app.utils.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;


public class RefundAmountFragment extends BaseFragment<FragmentRefundAmountBinding, RefundAmountViewModel> implements RefundAmountNavigator {

    public static final String TAG = RefundAmountFragment.class.getSimpleName();
    
    private FragmentRefundAmountBinding mFragmentRefundAmountBinding;

    @Inject
    ViewModelProviderFactory factory;
    
    private RefundAmountViewModel mRefundAmountViewModel;
    
    public static RefundAmountFragment newInstance() {
        Bundle args = new Bundle();
        RefundAmountFragment fragment = new RefundAmountFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_refund_amount;
    }

    @Override
    public RefundAmountViewModel getViewModel() {
        mRefundAmountViewModel = ViewModelProviders.of(this, factory)
                .get(RefundAmountViewModel.class);
        return mRefundAmountViewModel;
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
        mFragmentRefundAmountBinding = getViewDataBinding();
        mRefundAmountViewModel.setNavigator(this);
        setup();
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setup() {
        mFragmentRefundAmountBinding.finishTextview.setOnClickListener(v -> {
            goNext();
        });
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void goNext() {
        Log.d("debug", "Price confirmed");
        JSONObject data = new JSONObject();
        try {
            data.put("price", mFragmentRefundAmountBinding.cardPaymentEdittext.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        getBaseActivity().onReceivedMessageFromFragment(TAG, data);
        getBaseActivity().onFragmentDetached(TAG);
    }
}