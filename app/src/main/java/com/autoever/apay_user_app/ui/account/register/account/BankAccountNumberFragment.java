package com.autoever.apay_user_app.ui.account.register.account;

import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentBankAccountNumberBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;


public class BankAccountNumberFragment extends BaseFragment<FragmentBankAccountNumberBinding, BankAccountNumberViewModel> implements BankAccountNumberNavigator {

    public static final String TAG = BankAccountNumberFragment.class.getSimpleName();

    private FragmentBankAccountNumberBinding mFragmentBankAccountNumberBinding;

    @Inject
    ViewModelProviderFactory factory;

    private BankAccountNumberViewModel mBankAccountNumberViewModel;

    public static BankAccountNumberFragment newInstance(String selectedBankId, String selectedBankName) {
        Bundle args = new Bundle();
        args.putString("selectedBankId", selectedBankId);
        args.putString("selectedBankName", selectedBankName);
        BankAccountNumberFragment fragment = new BankAccountNumberFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_bank_account_number;
    }

    @Override
    public BankAccountNumberViewModel getViewModel() {
        mBankAccountNumberViewModel = ViewModelProviders.of(this, factory)
                .get(BankAccountNumberViewModel.class);
        return mBankAccountNumberViewModel;
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
        mFragmentBankAccountNumberBinding = getViewDataBinding();
        setup();
    }

    private void setup() {
        mFragmentBankAccountNumberBinding.bankNameText.setText(getArguments().getString("selectedBankName"));
        mFragmentBankAccountNumberBinding.finishTextview.setOnClickListener(v -> {
            try {
                JSONObject data = new JSONObject();
                data.put("withdrawBankCode", getArguments().getString("selectedBankId"));
                data.put("withdrawAccountNumber", mFragmentBankAccountNumberBinding.accountNumberEdittext.getText().toString());
                getBaseActivity().onReceivedMessageFromFragment(TAG, data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getBaseActivity().onFragmentDetached(TAG);
        });
    }
}