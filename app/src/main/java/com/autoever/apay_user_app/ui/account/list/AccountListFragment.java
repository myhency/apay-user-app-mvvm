package com.autoever.apay_user_app.ui.account.list;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.data.model.api.BankAccountListResponse;
import com.autoever.apay_user_app.databinding.FragmentAccountListBinding;
import com.autoever.apay_user_app.ui.account.register.AccountRegisterActivity;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.autoever.apay_user_app.ui.common.Bank;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.inject.Inject;

import static android.app.Activity.RESULT_FIRST_USER;
import static android.app.Activity.RESULT_OK;


public class AccountListFragment extends BaseFragment<FragmentAccountListBinding, AccountListViewModel> implements AccountListNavigator {

    public static String TAG = AccountListFragment.class.getSimpleName();

    private static final int ACCOUNT_REGISTER_ACTIVITY_RESULT = 102;

    private FragmentAccountListBinding mFragmentAccountListBinding;

    private String bankCode;

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
        mAccountListViewModel.setNavigator(this);
        setup();
        mAccountListViewModel.doListAccountCall();
    }

    private void setup() {
        mFragmentAccountListBinding.addBankAccountText.setOnClickListener(v -> openAccountRegisterActivity());
        mFragmentAccountListBinding.delBankAccountButton.setOnClickListener(v -> {
            //여기서 다이얼로그를 띄워준다.
            // custom dialog
            final Dialog dialog = new Dialog(getBaseActivity());
            dialog.setContentView(R.layout.delete_account_dialog);

            Button okButton = dialog.findViewById(R.id.ok_button);
            Button cancelButton = dialog.findViewById(R.id.cancel_button);

            okButton.setOnClickListener(v1 -> {
                dialog.dismiss();
                mAccountListViewModel.deleteBankAccountCall(bankCode);
            });

            cancelButton.setOnClickListener(v2 -> {
                dialog.dismiss();
            });

            dialog.show();

        });
    }

    @Override
    public void openAccountRegisterActivity() {
        Log.d("debug", "openAccountRegisterActivity");
        Intent intent = AccountRegisterActivity.newIntent(getBaseActivity());
        startActivityForResult(intent, ACCOUNT_REGISTER_ACTIVITY_RESULT);
    }

    @Override
    public void setAccountInfo(BankAccountListResponse bankAccountListResponse) {
        if (bankAccountListResponse.getData().size() == 0) { //등록된 계좌가 없을 때
            mFragmentAccountListBinding.addBankAccountText.setVisibility(View.VISIBLE);
            mFragmentAccountListBinding.noAccountLayout.setVisibility(View.VISIBLE);
            mFragmentAccountListBinding.accountInfoLayout.setVisibility(View.GONE);
            mFragmentAccountListBinding.accountDeleteLayout.setVisibility(View.GONE);
        } else {
            //계좌삭제를 위해서 bankCode 를 로컬변수에 저장
            this.bankCode = bankAccountListResponse.getData().get(0).getBankCode();
            //계좌추가하기 텍스트를 가림(계좌를 하나만 추가할 수 있고 추후 버젼에서는 은행당 하나를 등록할 수 있게 해야함.
            mFragmentAccountListBinding.addBankAccountText.setVisibility(View.GONE);
            mFragmentAccountListBinding.noAccountLayout.setVisibility(View.GONE);
            mFragmentAccountListBinding.accountInfoLayout.setVisibility(View.VISIBLE);
            mFragmentAccountListBinding.accountDeleteLayout.setVisibility(View.VISIBLE);
            String bankName = Bank.find(bankAccountListResponse
                    .getData()
                    .get(0)
                    .getBankCode())
                    .getBankName();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            String registerDate = simpleDateFormat
                    .format(bankAccountListResponse
                    .getData()
                    .get(0)
                    .getRegisterDatetime());
            String bankAccountNumber = bankAccountListResponse
                    .getData()
                    .get(0)
                    .getAccountNumber();
            int bankLogo = Bank.find(bankAccountListResponse
            .getData()
            .get(0)
            .getBankCode()).getLogo();
            mFragmentAccountListBinding.bankNameText.setText(bankName);
            mFragmentAccountListBinding.bankAccountAddedDate.setText("등록일시 " + registerDate);
            mFragmentAccountListBinding.bankAccountNumber.setText(
                    "******" +
                    bankAccountNumber.substring(bankAccountNumber.length() - 4));
            mFragmentAccountListBinding.bankLogo.setBackgroundResource(bankLogo);
        }
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void resetScreen() {
        mFragmentAccountListBinding.addBankAccountText.setVisibility(View.VISIBLE);
        mFragmentAccountListBinding.noAccountLayout.setVisibility(View.VISIBLE);
        mFragmentAccountListBinding.accountInfoLayout.setVisibility(View.GONE);
        mFragmentAccountListBinding.accountDeleteLayout.setVisibility(View.GONE);
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