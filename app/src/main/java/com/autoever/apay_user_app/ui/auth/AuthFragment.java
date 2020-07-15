package com.autoever.apay_user_app.ui.auth;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentAuthBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.autoever.apay_user_app.ui.home.HomeFragment;
import com.autoever.apay_user_app.ui.payment.PaymentActivity;
import com.google.android.gms.iid.InstanceID;

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
            if (v == mFragmentAuthBinding.buttonDelete) { //지우기 버튼을 눌렀을 때.
                int length = mFragmentAuthBinding.passwordEdit.getText().length();
                if (length > 0) mFragmentAuthBinding.passwordEdit.getText().delete(length - 1, length);
                return;
            } else if (v == mFragmentAuthBinding.buttonArrange) { //재배열 버튼을 눌렀을 때.
                Log.d("debug", "arrange button click");
                reArrangeButtons();

            } else if (v == mFragmentAuthBinding.button0 || v == mFragmentAuthBinding.button1 || v == mFragmentAuthBinding.button2
                    || v == mFragmentAuthBinding.button3 || v == mFragmentAuthBinding.button4 || v == mFragmentAuthBinding.button5
                    || v == mFragmentAuthBinding.button6 || v == mFragmentAuthBinding.button7 || v == mFragmentAuthBinding.button8
                    || v == mFragmentAuthBinding.button9) { //숫자버튼들을 눌렀을 때.
                Button button = (Button) v;
                mFragmentAuthBinding.passwordEdit.append(button.getText().toString());
            } else if (v == mFragmentAuthBinding.confirmButton) { //확인버튼을 눌렀을 때.
                //사용자가 6자리 이하의 숫자를 입력하고 확인버튼을 눌렀을 때 Toast로 알려준다.
                if (mFragmentAuthBinding.passwordEdit.getText().toString().length() < 6) {
                    Toast.makeText(
                            getBaseActivity(),
                            "숫자 6자리를 입력해야 합니다.",
                            Toast.LENGTH_SHORT
                    ).show();
                    return;
                }

//                switch (PURPOSE) {
//                    case ActivitiesFor.ENROLLMENT:
//                        password = mFragmentAuthBinding.passwordEdit.getText().toString();
//                        PURPOSE = ActivitiesFor.VALID_CHECK;
//                        initializeFragments(PURPOSE);
//                        return;
//                    case ActivitiesFor.VALID_CHECK:
//                        if (password.equals(mFragmentAuthBinding.passwordEdit.getText().toString())) {
//                            Log.d("debug", "입력한 패스워드가 일치, 패스워드를 저장하고 EasyPasswordActivity 종료");
////                            DatabaseUtil databaseUtil = new DatabaseUtil(EasyPasswordActivity.this);
////                            SQLiteDatabase db = databaseUtil.getWritableDatabase();
////
////                            String sql = "update user set easy_password = ? where instance_id = ?";
////
////                            String[] arg1 = {
////                                    password,
////                                    InstanceID.getInstance(EasyPasswordActivity.this).getId()
////                            };
////
////                            db.execSQL(sql, arg1);
////
////                            db.close();
//
//                            Intent intent = new Intent();
//                            intent.putExtra("password", password);
//                            setResult(ActivityResult.EASY_PASSWORD_ENROLLED, intent);
//                            finish();
//                            return;
//                        } else {
//                            Toast.makeText(
//                                    getApplicationContext(),
//                                    "패스워드가 일치하지 않습니다. \n이전화면으로 돌아가시려면 화면상단의 돌아가기 버튼을 눌러주세요.",
//                                    Toast.LENGTH_SHORT
//                            ).show();
//                            return;
//                        }
//                    case ActivitiesFor.REGISTER_BANK_ACCOUNT:
//                    case ActivitiesFor.APP_LOGIN:
//                        DatabaseUtil databaseUtil = new DatabaseUtil(getApplicationContext());
//                        SQLiteDatabase db = databaseUtil.getWritableDatabase();
//
//                        String sql = "select * " +
//                                "from user" +
//                                " where instance_id = ? " +
//                                "and easy_password = ?";
//
//                        String uniqueID = InstanceID.getInstance(getApplicationContext()).getId();
//
//                        String arg1[] = {
//                                uniqueID,
//                                mFragmentAuthBinding.passwordEdit.getText().toString()
//                        };
//
//                        Cursor cursor = db.rawQuery(sql, arg1);
//
//                        if (cursor.getCount() > 0) {
//                            Log.d("debug", "간편비번 인증 성공");
//                            db.close();
//                            setResult(RESULT_OK);
//                            finish();
//                            return;
//                        } else {
//                            Log.d("debug", "간편비번 인증 실패");
//                            db.close();
//                            Toast.makeText(
//                                    getApplicationContext(),
//                                    "패스워드가 일치하지 않습니다",
//                                    Toast.LENGTH_SHORT
//                            ).show();
//                            return;
//                        }
//                }
            }
        }
    }
}