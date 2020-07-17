package com.autoever.apay_user_app.ui.auth;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentAuthBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.autoever.apay_user_app.ui.payment.PaymentNavigator;
import com.autoever.apay_user_app.ui.payment.PaymentViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.inject.Inject;


public class AuthFragment extends BaseFragment<FragmentAuthBinding, AuthViewModel> implements AuthNavigator {

    public static final String TAG = AuthFragment.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;

    private AuthViewModel mAuthViewModel;
    private FragmentAuthBinding mFragmentAuthBinding;

    public static AuthFragment newInstance() {
        Bundle args = new Bundle();
        AuthFragment fragment = new AuthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_auth;
    }

    @Override
    public AuthViewModel getViewModel() {
        mAuthViewModel = ViewModelProviders.of(this, factory).get(AuthViewModel.class);
        return mAuthViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentAuthBinding = getViewDataBinding();
        mAuthViewModel.setNavigator(this);
        setup();
    }

    private void setup() {
        //numeric keypad 리스너 설정
        NumericKeypadListener listener = new NumericKeypadListener();
        final ArrayList<Button> numericButtons = new ArrayList<Button>(Arrays.asList(
                mFragmentAuthBinding.button0,
                mFragmentAuthBinding.button1,
                mFragmentAuthBinding.button2,
                mFragmentAuthBinding.button3,
                mFragmentAuthBinding.button4,
                mFragmentAuthBinding.button5,
                mFragmentAuthBinding.button6,
                mFragmentAuthBinding.button7,
                mFragmentAuthBinding.button8,
                mFragmentAuthBinding.button9
        ));

        for (Button button : numericButtons) {
            button.setOnClickListener(listener);
        }

        final ArrayList<Button> functionButtons = new ArrayList<>(Arrays.asList(
                mFragmentAuthBinding.buttonDelete,
                mFragmentAuthBinding.buttonArrange,
                mFragmentAuthBinding.confirmButton
        ));

        for (Button button : functionButtons) {
            button.setOnClickListener(listener);
        }

        //button별로 랜덤 넘버 할당.
        shuffleNumbers(numericButtons);

        //사용자가 비번을 입력할 때 이벤트 처리
        final ArrayList<ImageView> passwordCharList = new ArrayList<ImageView>(Arrays.asList(
                mFragmentAuthBinding.passwordChar01,
                mFragmentAuthBinding.passwordChar02,
                mFragmentAuthBinding.passwordChar03,
                mFragmentAuthBinding.passwordChar04,
                mFragmentAuthBinding.passwordChar05,
                mFragmentAuthBinding.passwordChar06
        ));

        mFragmentAuthBinding.passwordEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //사용자가 글자를 쓸 때 쓴 부분을 파란색칸으로 채운다.
                int passwordLength = s.toString().length();
                for (int i = 0; i < passwordLength; i++) {
                    passwordCharList.get(i).setImageResource(R.drawable.ic_bluecircle);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                //사용자가 글자를 지울 때 지운 부분을 빈칸으로 채운다.
                int passwordLength = s.length();
                for (int i = passwordLength; i < passwordCharList.size(); i++) {
                    passwordCharList.get(i).setImageResource(R.drawable.ic_greycircle);
                }
            }
        });
    }

    private void shuffleNumbers(ArrayList<Button> numericButtons) {
        Integer[] randomNumbers = new Integer[10];
        for (int i = 0; i < randomNumbers.length; i++) {
            randomNumbers[i] = i;
        }
        Collections.shuffle(Arrays.asList(randomNumbers));

        for (int i = 0; i < numericButtons.size(); i++) {
            numericButtons.get(i).setText(randomNumbers[i].toString());
        }
    }

    private void reArrangeButtons () {
        final ArrayList<Button> numericButtons = new ArrayList<Button>(Arrays.asList(
                mFragmentAuthBinding.button0,
                mFragmentAuthBinding.button1,
                mFragmentAuthBinding.button2,
                mFragmentAuthBinding.button3,
                mFragmentAuthBinding.button4,
                mFragmentAuthBinding.button5,
                mFragmentAuthBinding.button6,
                mFragmentAuthBinding.button7,
                mFragmentAuthBinding.button8,
                mFragmentAuthBinding.button9
        ));
        shuffleNumbers(numericButtons);
    }

    //숫자키패드용 리스너
    class NumericKeypadListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.button_delete:
                    int length = mFragmentAuthBinding.passwordEdit.getText().length();
                    if (length > 0) mFragmentAuthBinding.passwordEdit.getText().delete(length - 1, length);
                    break;
                case R.id.button_arrange:
                    reArrangeButtons();
                    break;
                case R.id.button0:
                case R.id.button1:
                case R.id.button2:
                case R.id.button3:
                case R.id.button4:
                case R.id.button5:
                case R.id.button6:
                case R.id.button7:
                case R.id.button8:
                case R.id.button9:
                    Button button = (Button) v;
                    mFragmentAuthBinding.passwordEdit.append(button.getText().toString());
                    break;
                case R.id.confirm_button:
                    //사용자가 6자리 이하의 숫자를 입력하고 확인버튼을 눌렀을 때 Toast로 알려준다.
                    if (mFragmentAuthBinding.passwordEdit.getText().toString().length() < 6) {
                        Toast.makeText(
                                getBaseActivity(),
                                "숫자 6자리를 입력해야 합니다.",
                                Toast.LENGTH_SHORT
                        ).show();
                    }

                    if (mAuthViewModel.isPasswordValid(mFragmentAuthBinding.passwordEdit.getText().toString())) {
                        getBaseActivity().onFragmentDetached(TAG);
                    }
            }
        }
    }
}