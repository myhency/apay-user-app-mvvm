package com.autoever.apay_user_app.ui.account.register.bank;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentBankSelectBinding;
import com.autoever.apay_user_app.ui.account.register.terms.AccountRegisterTermsFragment;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.autoever.apay_user_app.ui.common.Bank;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;


public class BankSelectFragment extends BaseFragment<FragmentBankSelectBinding, BankSelectViewModel> implements BankSelectNavigator {

    public static final String TAG = BankSelectFragment.class.getSimpleName();

    private String selectedBankId = "";
    private String selectedBankName = "";
    private List<LinearLayout> banks;

    private FragmentBankSelectBinding mFragmentBankSelectBinding;

    @Inject
    ViewModelProviderFactory factory;
    private BankSelectViewModel mBankSelectViewModel;

    public static BankSelectFragment newInstance() {
        Bundle args = new Bundle();
        BankSelectFragment fragment = new BankSelectFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_bank_select;
    }

    @Override
    public BankSelectViewModel getViewModel() {
        mBankSelectViewModel = ViewModelProviders.of(this, factory)
                .get(BankSelectViewModel.class);
        return mBankSelectViewModel;
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
        mFragmentBankSelectBinding = getViewDataBinding();
        setup();
    }

    private void setup() {
        banks = Arrays.asList(
                mFragmentBankSelectBinding.nonghyub,
                mFragmentBankSelectBinding.woori,
                mFragmentBankSelectBinding.shinhan,
                mFragmentBankSelectBinding.kookmin,
                mFragmentBankSelectBinding.hana,
                mFragmentBankSelectBinding.city,
                mFragmentBankSelectBinding.ibk,
                mFragmentBankSelectBinding.kbank,
                mFragmentBankSelectBinding.kakao
        );

        OnClickListener onClickListener = new OnClickListener();
        for (LinearLayout bank : banks) {
            bank.setOnClickListener(onClickListener);
        }

        mFragmentBankSelectBinding.finishTextview.setOnClickListener(v -> {
            try {
                JSONObject data = new JSONObject();
                data.put("selectedBankId", selectedBankId);
                data.put("selectedBankName", selectedBankName);
                getBaseActivity().onReceivedMessageFromFragment(TAG, data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getBaseActivity().onFragmentDetached(TAG);
        });
    }

    class OnClickListener implements View.OnClickListener {


        @Override
        public void onClick(View v) {
            Bundle result = new Bundle();

            for (LinearLayout bank : banks) {
                bank.setBackgroundResource(R.drawable.bg_bank_radius);
            }

            switch (v.getId()) {
                case R.id.nonghyub:
                    v.setBackgroundResource(R.drawable.bg_bank_radius_with_border);
                    selectedBankId = "011";
                    selectedBankName = getBankName(selectedBankId);
                    mFragmentBankSelectBinding.textView4.setText(selectedBankName);
                    break;
                case R.id.woori:
                    v.setBackgroundResource(R.drawable.bg_bank_radius_with_border);
                    selectedBankId = "020";
                    selectedBankName = getBankName(selectedBankId);
                    mFragmentBankSelectBinding.textView4.setText(selectedBankName);
                    break;
                case R.id.shinhan:
                    v.setBackgroundResource(R.drawable.bg_bank_radius_with_border);
                    selectedBankId = "088";
                    selectedBankName = getBankName(selectedBankId);
                    mFragmentBankSelectBinding.textView4.setText(selectedBankName);
                    break;
                case R.id.kookmin:
                    v.setBackgroundResource(R.drawable.bg_bank_radius_with_border);
                    selectedBankId = "004";
                    selectedBankName = getBankName(selectedBankId);
                    mFragmentBankSelectBinding.textView4.setText(selectedBankName);
                    break;
                case R.id.hana:
                    v.setBackgroundResource(R.drawable.bg_bank_radius_with_border);
                    selectedBankId = "081";
                    selectedBankName = getBankName(selectedBankId);
                    mFragmentBankSelectBinding.textView4.setText(selectedBankName);
                    break;
                case R.id.city:
                    v.setBackgroundResource(R.drawable.bg_bank_radius_with_border);
                    selectedBankId = "027";
                    selectedBankName = getBankName(selectedBankId);
                    mFragmentBankSelectBinding.textView4.setText(selectedBankName);
                    break;
                case R.id.ibk:
                    v.setBackgroundResource(R.drawable.bg_bank_radius_with_border);
                    selectedBankId = "003";
                    selectedBankName = getBankName(selectedBankId);
                    mFragmentBankSelectBinding.textView4.setText(selectedBankName);
                    break;
                case R.id.kbank:
                    v.setBackgroundResource(R.drawable.bg_bank_radius_with_border);
                    selectedBankId = "089";
                    selectedBankName = getBankName(selectedBankId);
                    mFragmentBankSelectBinding.textView4.setText(selectedBankName);
                    break;
                case R.id.kakao:
                    v.setBackgroundResource(R.drawable.bg_bank_radius_with_border);
                    selectedBankId = "090";
                    selectedBankName = getBankName(selectedBankId);
                    mFragmentBankSelectBinding.textView4.setText(selectedBankName);
                    break;
                default:
                    break;
            }
        }
    }

    private String getBankName(String selectedBankId) {
        return Bank.find(selectedBankId).getBankName();
    }
}