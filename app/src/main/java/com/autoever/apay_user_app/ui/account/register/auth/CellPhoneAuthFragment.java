package com.autoever.apay_user_app.ui.account.register.auth;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
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
        mFragmentCellPhoneAuthBinding = getViewDataBinding();

        setup();
    }

    private void setup() {
        mFragmentCellPhoneAuthBinding.finishTextview.setOnClickListener(v -> {
            String identificationNumber = mFragmentCellPhoneAuthBinding.ssnf01.getText().toString()
                    + mFragmentCellPhoneAuthBinding.ssnf02.getText().toString()
                    + mFragmentCellPhoneAuthBinding.ssnf03.getText().toString()
                    + mFragmentCellPhoneAuthBinding.ssnf04.getText().toString()
                    + mFragmentCellPhoneAuthBinding.ssnf05.getText().toString()
                    + mFragmentCellPhoneAuthBinding.ssnf06.getText().toString()
                    + mFragmentCellPhoneAuthBinding.ssnf07.getText().toString();
            try {
                JSONObject data = new JSONObject();
                data.put("authenticationCode", "123456");
                data.put("phoneNumber", mFragmentCellPhoneAuthBinding.telephoneNumber.getText());
                data.put("subscriberName", mFragmentCellPhoneAuthBinding.userName.getText());
                data.put("identificationNumber", identificationNumber);
                getBaseActivity().onReceivedMessageFromFragment(TAG, data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getBaseActivity().onFragmentDetached(TAG);
        });

        mFragmentCellPhoneAuthBinding.ssnf02.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.d("debug", "keyCode:" + keyCode);
                if(mFragmentCellPhoneAuthBinding.ssnf02.getText().toString().length() == 0 && keyCode == 67) {
                    mFragmentCellPhoneAuthBinding.ssnf01.requestFocus();
                    mFragmentCellPhoneAuthBinding.ssnf02.clearFocus();
                }

                return false;
            }
        });

        mFragmentCellPhoneAuthBinding.ssnf03.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.d("debug", "keyCode:" + keyCode);
                if(mFragmentCellPhoneAuthBinding.ssnf03.getText().toString().length() == 0 && keyCode == 67) {
                    mFragmentCellPhoneAuthBinding.ssnf02.requestFocus();
                    mFragmentCellPhoneAuthBinding.ssnf03.clearFocus();
                }

                return false;
            }
        });

        mFragmentCellPhoneAuthBinding.ssnf04.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.d("debug", "keyCode:" + keyCode);
                if(mFragmentCellPhoneAuthBinding.ssnf04.getText().toString().length() == 0 && keyCode == 67) {
                    mFragmentCellPhoneAuthBinding.ssnf03.requestFocus();
                    mFragmentCellPhoneAuthBinding.ssnf04.clearFocus();
                }

                return false;
            }
        });

        mFragmentCellPhoneAuthBinding.ssnf05.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.d("debug", "keyCode:" + keyCode);
                if(mFragmentCellPhoneAuthBinding.ssnf05.getText().toString().length() == 0 && keyCode == 67) {
                    mFragmentCellPhoneAuthBinding.ssnf04.requestFocus();
                    mFragmentCellPhoneAuthBinding.ssnf05.clearFocus();
                }

                return false;
            }
        });

        mFragmentCellPhoneAuthBinding.ssnf06.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.d("debug", "keyCode:" + keyCode);
                if(mFragmentCellPhoneAuthBinding.ssnf06.getText().toString().length() == 0 && keyCode == 67) {
                    mFragmentCellPhoneAuthBinding.ssnf05.requestFocus();
                    mFragmentCellPhoneAuthBinding.ssnf06.clearFocus();
                }

                return false;
            }
        });

        mFragmentCellPhoneAuthBinding.ssnf07.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Log.d("debug", "keyCode:" + keyCode);
                if(mFragmentCellPhoneAuthBinding.ssnf07.getText().toString().length() == 0 && keyCode == 67) {
                    mFragmentCellPhoneAuthBinding.ssnf06.requestFocus();
                    mFragmentCellPhoneAuthBinding.ssnf07.clearFocus();
                }

                return false;
            }
        });

        mFragmentCellPhoneAuthBinding.ssnf01.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 1) {
                    mFragmentCellPhoneAuthBinding.ssnf02.requestFocus();
                    mFragmentCellPhoneAuthBinding.ssnf01.clearFocus();
                }
            }
        });

        mFragmentCellPhoneAuthBinding.ssnf02.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 1) {
                    mFragmentCellPhoneAuthBinding.ssnf03.requestFocus();
                    mFragmentCellPhoneAuthBinding.ssnf02.clearFocus();
                }
            }
        });

        mFragmentCellPhoneAuthBinding.ssnf03.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 1) {
                    mFragmentCellPhoneAuthBinding.ssnf04.requestFocus();
                    mFragmentCellPhoneAuthBinding.ssnf03.clearFocus();
                }
            }
        });

        mFragmentCellPhoneAuthBinding.ssnf04.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 1) {
                    mFragmentCellPhoneAuthBinding.ssnf05.requestFocus();
                    mFragmentCellPhoneAuthBinding.ssnf04.clearFocus();
                }
            }
        });

        mFragmentCellPhoneAuthBinding.ssnf05.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 1) {
                    mFragmentCellPhoneAuthBinding.ssnf06.requestFocus();
                    mFragmentCellPhoneAuthBinding.ssnf05.clearFocus();
                }
            }
        });

        mFragmentCellPhoneAuthBinding.ssnf06.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString().length() == 1) {
                    mFragmentCellPhoneAuthBinding.ssnf07.requestFocus();
                    mFragmentCellPhoneAuthBinding.ssnf06.clearFocus();
                }
            }
        });
    }


}