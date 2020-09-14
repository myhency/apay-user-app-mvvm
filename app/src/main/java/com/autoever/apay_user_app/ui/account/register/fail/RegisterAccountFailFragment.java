package com.autoever.apay_user_app.ui.account.register.fail;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentRegisterAccountFailBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.autoever.apay_user_app.ui.charge.fail.ChargeFailFragment;

import javax.inject.Inject;


public class RegisterAccountFailFragment extends BaseFragment<FragmentRegisterAccountFailBinding, RegisterAccountFailViewModel> implements RegisterAccountFailNavigator{

    public static final String TAG = RegisterAccountFailFragment.class.getSimpleName();

    private FragmentRegisterAccountFailBinding mFragmentRegisterAccountFailBinding;
    private RegisterAccountFailViewModel mRegisterAccountFailViewModel;

    @Inject
    ViewModelProviderFactory factory;

    public static RegisterAccountFailFragment newInstance() {
        Bundle args = new Bundle();
        RegisterAccountFailFragment fragment = new RegisterAccountFailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_register_account_fail;
    }

    @Override
    public RegisterAccountFailViewModel getViewModel() {
        mRegisterAccountFailViewModel = ViewModelProviders.of(this, factory)
                .get(RegisterAccountFailViewModel.class);
        return mRegisterAccountFailViewModel;
    }

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
        mFragmentRegisterAccountFailBinding = getViewDataBinding();

        setup();
    }

    private void setup() {

        mFragmentRegisterAccountFailBinding.finishTextview.setOnClickListener(v -> getBaseActivity().onFragmentDetached(TAG));
    }
}