package com.autoever.apay_user_app.ui.account.register.auth;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentAccountRegisterTermsBinding;
import com.autoever.apay_user_app.databinding.FragmentCellPhoneAuthBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;


public class CellPhoneAuthFragment extends BaseFragment<FragmentCellPhoneAuthBinding, CellPhoneAuthViewModel> implements CellPhoneAuthNavigator {

    public static final String TAG = CellPhoneAuthFragment.class.getSimpleName();

    private FragmentCellPhoneAuthBinding mFragmentCellPhoneAuthBinding;

    @Inject
    ViewModelProviderFactory factory;
    private CellPhoneAuthViewModel mCellPhoneAuthViewModel;

    public static CellPhoneAuthFragment newInstance() {
        Bundle args = new Bundle();
        CellPhoneAuthFragment fragment = new CellPhoneAuthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_cell_phone_auth;
    }

    @Override
    public CellPhoneAuthViewModel getViewModel() {
        mCellPhoneAuthViewModel = ViewModelProviders.of(this, factory)
                .get(CellPhoneAuthViewModel.class);
        return mCellPhoneAuthViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentCellPhoneAuthBinding = getViewDataBinding();

        setup();
    }

    private void setup() {
        mFragmentCellPhoneAuthBinding.finishTextview.setOnClickListener(v -> {
            try {
                JSONObject data = new JSONObject();
                data.put("authenticationCode", "123456");
                data.put("phoneNumber", mFragmentCellPhoneAuthBinding.cellPhoneInput.getText());
                data.put("subscriberName", mFragmentCellPhoneAuthBinding.nameInput.getText());
                getBaseActivity().onReceivedMessageFromFragment(TAG, data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getBaseActivity().onFragmentDetached(TAG);
        });
    }
}