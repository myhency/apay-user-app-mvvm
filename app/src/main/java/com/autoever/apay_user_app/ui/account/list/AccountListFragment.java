package com.autoever.apay_user_app.ui.account.list;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentAccountListBinding;
import com.autoever.apay_user_app.ui.account.register.AccountRegisterActivity;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.autoever.apay_user_app.ui.payment.PaymentActivity;
import com.autoever.apay_user_app.ui.payment.scanner.CustomScannerActivity;

import javax.inject.Inject;

import static android.app.Activity.RESULT_FIRST_USER;
import static android.app.Activity.RESULT_OK;


public class AccountListFragment extends BaseFragment<FragmentAccountListBinding, AccountListViewModel> implements AccountListNavigator {

    public static String TAG = AccountListFragment.class.getSimpleName();

    private static final int ACCOUNT_REGISTER_ACTIVITY_RESULT = 102;

    private FragmentAccountListBinding mFragmentAccountListBinding;

    @Inject
    ViewModelProviderFactory factory;
    private AccountListViewModel mAccountListViewModel;

    public static AccountListFragment newInstance() {
        Bundle args = new Bundle();
        AccountListFragment fragment = new AccountListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_account_list;
    }

    @Override
    public AccountListViewModel getViewModel() {
        mAccountListViewModel = ViewModelProviders.of(this,factory)
                .get(AccountListViewModel.class);
        return mAccountListViewModel;
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
        mFragmentAccountListBinding = getViewDataBinding();
        setup();
    }

    private void setup() {
        mFragmentAccountListBinding.addBankAccountText.setOnClickListener(v -> openAccountRegisterActivity());
    }

    @Override
    public void openAccountRegisterActivity() {
        Log.d("debug", "openAccountRegisterActivity");
        Intent intent = AccountRegisterActivity.newIntent(getBaseActivity());
        startActivityForResult(intent, ACCOUNT_REGISTER_ACTIVITY_RESULT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("debug", "AccountRegisterActivity resultCode: " + resultCode);
        switch (resultCode) {
            case RESULT_FIRST_USER:
                getBaseActivity().setResult(RESULT_FIRST_USER);
                getBaseActivity().finish();
            case RESULT_OK:
                getBaseActivity().setResult(RESULT_OK);
                getBaseActivity().finish();
            default:
                getBaseActivity().finish();
        }
    }
}