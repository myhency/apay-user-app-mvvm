package com.autoever.apay_user_app.ui.user.mypage.password;

import android.app.Dialog;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentChangeLoginPasswordBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.autoever.apay_user_app.ui.payment.price.PriceFragment;
import com.autoever.apay_user_app.utils.CommonUtils;

import javax.inject.Inject;

import retrofit2.HttpException;


public class ChangeLoginPasswordFragment extends BaseFragment<FragmentChangeLoginPasswordBinding, ChangeLoginPasswordViewModel>
        implements ChangeLoginPasswordNavigator {

    public static final String TAG = ChangeLoginPasswordFragment.class.getSimpleName();

    private FragmentChangeLoginPasswordBinding mFragmentChangeLoginPasswordBinding;
    private ChangeLoginPasswordViewModel mChangeLoginPasswordViewModel;

    public static ChangeLoginPasswordFragment newInstance() {
        Bundle args = new Bundle();
        ChangeLoginPasswordFragment fragment = new ChangeLoginPasswordFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Inject
    ViewModelProviderFactory factory;

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_change_login_password;
    }

    @Override
    public ChangeLoginPasswordViewModel getViewModel() {
        mChangeLoginPasswordViewModel = ViewModelProviders.of(this, factory)
                .get(ChangeLoginPasswordViewModel.class);
        return mChangeLoginPasswordViewModel;
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
        mFragmentChangeLoginPasswordBinding = getViewDataBinding();
        mChangeLoginPasswordViewModel.setNavigator(this);

        setup();
    }

    private void setup() {
        //패스워드 입력 규칙 확인
        mFragmentChangeLoginPasswordBinding.password.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (!CommonUtils.isPasswordValid(mFragmentChangeLoginPasswordBinding.password.getText().toString())) {
                    mFragmentChangeLoginPasswordBinding.passwordTextInputLayout.setErrorEnabled(true);
                    mFragmentChangeLoginPasswordBinding.passwordTextInputLayout.setError("최소 8자리이상의 대소문자, 숫자, 특수문자를 혼용해 주세요.");
                }
            } else {
                mFragmentChangeLoginPasswordBinding.passwordTextInputLayout.setErrorEnabled(false);
            }
        });

        //패스워드 확인
        mFragmentChangeLoginPasswordBinding.passwordConfirm.setOnFocusChangeListener((v, hasFocus) -> {
            if (!hasFocus) {
                if (!mFragmentChangeLoginPasswordBinding.password.getText().toString().equals(mFragmentChangeLoginPasswordBinding.passwordConfirm.getText().toString())) {
                    mFragmentChangeLoginPasswordBinding.passwordConfirmTextInputLayout.setErrorEnabled(true);
                    mFragmentChangeLoginPasswordBinding.passwordConfirmTextInputLayout.setError("입력하신 패스워드와 일치하지 않습니다.");
                }
            } else {
                mFragmentChangeLoginPasswordBinding.passwordConfirmTextInputLayout.setErrorEnabled(false);
            }
        });

        mFragmentChangeLoginPasswordBinding.finishTextview.setOnClickListener(v -> {
            mChangeLoginPasswordViewModel.changeLoginPasswordCall(
                    mFragmentChangeLoginPasswordBinding.passwordConfirm.getText().toString()
            );
        });
    }

    @Override
    public void openAuthFragment() {

    }

    @Override
    public void handleChangeLoginPassword(Throwable throwable) {
        //여기서 다이얼로그를 띄워준다.
        // custom dialog
        final Dialog dialog = new Dialog(getBaseActivity());
        dialog.setContentView(R.layout.change_password_success_dialog);

        Button okButton = dialog.findViewById(R.id.ok_button);

        okButton.setOnClickListener(v1 -> {
            dialog.dismiss();
            getBaseActivity().onFragmentDetached(TAG);
        });

        dialog.show();


        //TODO. backend 수정되면 수정필요.
//        HttpException httpException = (HttpException) throwable;
//
//        switch (httpException.code()) {
//            case 200:
//                //여기서 다이얼로그를 띄워준다.
//                // custom dialog
//                final Dialog dialog = new Dialog(getBaseActivity());
//                dialog.setContentView(R.layout.change_password_success_dialog);
//
//                Button okButton = dialog.findViewById(R.id.ok_button);
//
//                okButton.setOnClickListener(v1 -> {
//                    dialog.dismiss();
//                    getBaseActivity().onFragmentDetached(TAG);
//                });
//
//                dialog.show();
//                break;
//            default:
//                //여기서 다이얼로그를 띄워준다.
//                // custom dialog
//                final Dialog dialog2 = new Dialog(getBaseActivity());
//                dialog2.setContentView(R.layout.change_password_fail_dialog);
//
//                Button okButton2 = dialog2.findViewById(R.id.ok_button);
//
//                okButton2.setOnClickListener(v1 -> {
//                    dialog2.dismiss();
//                });
//
//                dialog2.show();
//                break;
//        }
    }
}