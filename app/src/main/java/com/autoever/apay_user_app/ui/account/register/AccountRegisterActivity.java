package com.autoever.apay_user_app.ui.account.register;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.data.model.api.ArsCheckResponse;
import com.autoever.apay_user_app.data.model.api.ArsRequestResponse;
import com.autoever.apay_user_app.databinding.ActivityAccountRegisterBinding;
import com.autoever.apay_user_app.ui.account.register.account.BankAccountNumberFragment;
import com.autoever.apay_user_app.ui.account.register.ars.ArsAuthFragment;
import com.autoever.apay_user_app.ui.account.register.auth.CellPhoneAuthFragment;
import com.autoever.apay_user_app.ui.account.register.bank.BankSelectFragment;
import com.autoever.apay_user_app.ui.account.register.fail.RegisterAccountFailFragment;
import com.autoever.apay_user_app.ui.account.register.terms.AccountRegisterTermsFragment;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.charge.fail.ChargeFailFragment;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;
import retrofit2.HttpException;

public class AccountRegisterActivity extends BaseActivity<ActivityAccountRegisterBinding, AccountRegisterViewModel>
        implements AccountRegisterNavigator, HasSupportFragmentInjector {

    @Inject
    ViewModelProviderFactory factory;

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    private ActivityAccountRegisterBinding mActivityAccountRegisterBinding;
    private AccountRegisterViewModel mAccountRegisterViewModel;
    private FragmentManager mFragmentManager;

    //TODO. 확정되면 preference 에서 관리하는 것이 좋을 것 같음.
    private String settleBankUniqueId;
    private String phoneNumber;
    private String subscriberName;
    private String withdrawBankCode;
    private String withdrawAccountNumber;
    private String authenticationCode;
    private String identificationNumber;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, AccountRegisterActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_account_register;
    }

    @Override
    public AccountRegisterViewModel getViewModel() {
        mAccountRegisterViewModel = ViewModelProviders.of(this, factory)
                .get(AccountRegisterViewModel.class);
        return mAccountRegisterViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityAccountRegisterBinding = getViewDataBinding();
        mAccountRegisterViewModel.setNavigator(this);
        this.mFragmentManager = getSupportFragmentManager();

        openAccountRegisterTermsFragment();
        setup();
    }

    private void setup() {

        setSupportActionBar(mActivityAccountRegisterBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mActivityAccountRegisterBinding.toolbarTitle.setText("결제계좌 등록");
        }

        layoutInitiate(1);

    }

    private void layoutInitiate(int step) {
        switch (step) {
            case 1:
                Log.d("debug", "step1");
                mActivityAccountRegisterBinding.step01.setBackgroundResource(R.drawable.ic_bluecircle);
                mActivityAccountRegisterBinding.step01.setText("1");
                mActivityAccountRegisterBinding.step01.setTextColor(Color.parseColor("#ffffff"));
                mActivityAccountRegisterBinding.step02.setBackgroundResource(R.drawable.full_moon);
                mActivityAccountRegisterBinding.step02.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step03.setBackgroundResource(R.drawable.full_moon);
                mActivityAccountRegisterBinding.step03.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step04.setBackgroundResource(R.drawable.full_moon);
                mActivityAccountRegisterBinding.step04.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step05.setBackgroundResource(R.drawable.full_moon);
                mActivityAccountRegisterBinding.step05.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step01Text.setTextColor(Color.parseColor("#000000"));
                mActivityAccountRegisterBinding.step02Text.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step03Text.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step04Text.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step05Text.setTextColor(Color.parseColor("#a5a8b9"));
                break;
            case 2:
                Log.d("debug", "step2");
                mActivityAccountRegisterBinding.step01.setBackgroundResource(R.drawable.step_complete);
                mActivityAccountRegisterBinding.step01.setText("");
                mActivityAccountRegisterBinding.step02.setBackgroundResource(R.drawable.ic_bluecircle);
                mActivityAccountRegisterBinding.step02.setTextColor(Color.parseColor("#ffffff"));
                mActivityAccountRegisterBinding.step03.setBackgroundResource(R.drawable.full_moon);
                mActivityAccountRegisterBinding.step03.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step04.setBackgroundResource(R.drawable.full_moon);
                mActivityAccountRegisterBinding.step04.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step05.setBackgroundResource(R.drawable.full_moon);
                mActivityAccountRegisterBinding.step05.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step01Text.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step02Text.setTextColor(Color.parseColor("#000000"));
                mActivityAccountRegisterBinding.step03Text.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step04Text.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step05Text.setTextColor(Color.parseColor("#a5a8b9"));
                break;
            case 3:
                Log.d("debug", "step3");
                mActivityAccountRegisterBinding.step01.setBackgroundResource(R.drawable.step_complete);
                mActivityAccountRegisterBinding.step01.setText("");
                mActivityAccountRegisterBinding.step02.setBackgroundResource(R.drawable.step_complete);
                mActivityAccountRegisterBinding.step02.setText("");
                mActivityAccountRegisterBinding.step03.setBackgroundResource(R.drawable.ic_bluecircle);
                mActivityAccountRegisterBinding.step03.setTextColor(Color.parseColor("#ffffff"));
                mActivityAccountRegisterBinding.step04.setBackgroundResource(R.drawable.full_moon);
                mActivityAccountRegisterBinding.step04.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step05.setBackgroundResource(R.drawable.full_moon);
                mActivityAccountRegisterBinding.step05.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step01Text.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step02Text.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step03Text.setTextColor(Color.parseColor("#000000"));
                mActivityAccountRegisterBinding.step04Text.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step05Text.setTextColor(Color.parseColor("#a5a8b9"));
                break;
            case 4:
                Log.d("debug", "step4");
                mActivityAccountRegisterBinding.step01.setBackgroundResource(R.drawable.step_complete);
                mActivityAccountRegisterBinding.step01.setText("");
                mActivityAccountRegisterBinding.step02.setBackgroundResource(R.drawable.step_complete);
                mActivityAccountRegisterBinding.step02.setText("");
                mActivityAccountRegisterBinding.step03.setBackgroundResource(R.drawable.step_complete);
                mActivityAccountRegisterBinding.step03.setText("");
                mActivityAccountRegisterBinding.step04.setBackgroundResource(R.drawable.ic_bluecircle);
                mActivityAccountRegisterBinding.step04.setTextColor(Color.parseColor("#ffffff"));
                mActivityAccountRegisterBinding.step05.setBackgroundResource(R.drawable.full_moon);
                mActivityAccountRegisterBinding.step05.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step01Text.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step02Text.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step03Text.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step04Text.setTextColor(Color.parseColor("#000000"));
                mActivityAccountRegisterBinding.step05Text.setTextColor(Color.parseColor("#a5a8b9"));
                break;
            case 5:
                Log.d("debug", "step5");
                mActivityAccountRegisterBinding.step01.setBackgroundResource(R.drawable.step_complete);
                mActivityAccountRegisterBinding.step01.setText("");
                mActivityAccountRegisterBinding.step02.setBackgroundResource(R.drawable.step_complete);
                mActivityAccountRegisterBinding.step02.setText("");
                mActivityAccountRegisterBinding.step03.setBackgroundResource(R.drawable.step_complete);
                mActivityAccountRegisterBinding.step03.setText("");
                mActivityAccountRegisterBinding.step04.setBackgroundResource(R.drawable.step_complete);
                mActivityAccountRegisterBinding.step04.setText("");
                mActivityAccountRegisterBinding.step05.setBackgroundResource(R.drawable.ic_bluecircle);
                mActivityAccountRegisterBinding.step05.setTextColor(Color.parseColor("#ffffff"));
                mActivityAccountRegisterBinding.step01Text.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step02Text.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step03Text.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step04Text.setTextColor(Color.parseColor("#a5a8b9"));
                mActivityAccountRegisterBinding.step05Text.setTextColor(Color.parseColor("#000000"));
                break;
            default:
                Log.d("debug", "default");
                break;
        }

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }

    @Override
    public void openAccountRegisterTermsFragment() {
        //약관동의화면으로 이동.
        Log.d("debug", "openAccountRegisterTermsFragment");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, AccountRegisterTermsFragment.newInstance(), AccountRegisterTermsFragment.TAG)
                .addToBackStack(AccountRegisterTermsFragment.TAG)
                .commit();
    }

    @Override
    public void openCellPhoneAuthFragment() {
        //휴대전화인증화면으로 이동.
        Log.d("debug", "openCellPhoneAuthFragment");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, CellPhoneAuthFragment.newInstance(), CellPhoneAuthFragment.TAG)
                .addToBackStack(CellPhoneAuthFragment.TAG)
                .commit();
        layoutInitiate(2);
    }

    @Override
    public void openBankSelectFragment() {
        //은행선택화면으로 이동.
        Log.d("debug", "openBankSelectFragment");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, BankSelectFragment.newInstance(), BankSelectFragment.TAG)
                .addToBackStack(BankSelectFragment.TAG)
                .commit();
        layoutInitiate(3);
    }

    @Override
    public void openBankAccountNumberFragment(String selectedBankId, String selectedBankName) {
        //계좌번호입력화면으로 이동.
        Log.d("debug", "openBankAccountNumberFragment");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, BankAccountNumberFragment.newInstance(selectedBankId, selectedBankName), BankAccountNumberFragment.TAG)
                .addToBackStack(BankAccountNumberFragment.TAG)
                .commit();
        layoutInitiate(4);
    }

    @Override
    public void openArsAuthFragment(ArsRequestResponse arsRequestResponse) {
        //ARS 인증화면으로 이동.
        Log.d("debug", "openCellPhoneAuthFragment");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, ArsAuthFragment.newInstance(arsRequestResponse), ArsAuthFragment.TAG)
                .addToBackStack(ArsAuthFragment.TAG)
                .commit();
        layoutInitiate(5);
    }

    @Override
    public void openDialog() {
        //여기서 다이얼로그를 띄워준다.
        // custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.go_charge_dialog);

        Button okButton = dialog.findViewById(R.id.ok_button);
        Button cancelButton = dialog.findViewById(R.id.cancel_button);

        okButton.setOnClickListener(v1 -> {
            dialog.dismiss();
            setResult(RESULT_FIRST_USER);
            finish();
        });

        cancelButton.setOnClickListener(v2 -> {
            dialog.dismiss();
            setResult(RESULT_OK);
            finish();
        });

        dialog.show();
    }

    @Override
    public void handleError(Throwable throwable) {
        HttpException httpException = (HttpException) throwable;

        switch (httpException.code()) {
            default:
                openRegisterAccountFailFragment();
                break;
        }
    }

    public void openRegisterAccountFailFragment() {
        //충전실패 화면으로 이동.
        Log.d("debug", "openChargeFailFragment");
        mActivityAccountRegisterBinding.toolbar.setVisibility(View.INVISIBLE);
        mActivityAccountRegisterBinding.appBarLayout.setBackgroundColor(getResources().getColor(R.color.colorWhite, null));
        mFragmentManager
                .beginTransaction()
                .replace(R.id.clRootView, RegisterAccountFailFragment.newInstance(), RegisterAccountFailFragment.TAG)
                .addToBackStack(RegisterAccountFailFragment.TAG)
                .commit();
    }

    @Override
    public void onFragmentDetached(String tag) {
        super.onFragmentDetached(tag);
        switch (tag) {
            case "AccountRegisterTermsFragment":
                removeFragment(tag);
                openCellPhoneAuthFragment();
                break;
            case "CellPhoneAuthFragment":
                removeFragment(tag);
                //TODO. 백엔드 제대로 구현되면 구현해야 함.
                mAccountRegisterViewModel.doIdentityCheckCall();
                break;
            case "BankSelectFragment":
                removeFragment(tag);
                break;
            case "BankAccountNumberFragment":
                removeFragment(tag);
                mAccountRegisterViewModel.doArsRequestCall(
                        phoneNumber,
                        subscriberName,
                        withdrawBankCode,
                        withdrawAccountNumber,
                        identificationNumber,
                        authenticationCode
                );

                break;
            case "ArsAuthFragment":
                removeFragment(tag);
                mAccountRegisterViewModel.doArsCheckCall(
                        settleBankUniqueId,
                        phoneNumber,
                        subscriberName,
                        withdrawBankCode,
                        withdrawAccountNumber,
                        authenticationCode
                );
                break;
            default:
                break;
        }
    }

    @Override
    public void onReceivedMessageFromFragment(String tag, JSONObject message) {
        super.onReceivedMessageFromFragment(tag, message);
        switch (tag) {
            case "CellPhoneAuthFragment":
                try {
                    phoneNumber = message.getString("phoneNumber");
                    subscriberName = message.getString("subscriberName");
                    authenticationCode = message.getString("authenticationCode");
                    identificationNumber = message.getString("identificationNumber");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case "BankSelectFragment":
                try {
                    openBankAccountNumberFragment(message.getString("selectedBankId"), message.getString("selectedBankName"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case "BankAccountNumberFragment":
                try {
                    withdrawBankCode = message.getString("withdrawBankCode");
                    withdrawAccountNumber = message.getString("withdrawAccountNumber");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            case "ArsAuthFragment":
                try {
                    settleBankUniqueId = message.getString("settleBankUniqueId");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
            default:
                break;
        }
    }

    public void removeFragment(String tag) {
        Fragment fragment = mFragmentManager.findFragmentByTag(tag);
        mFragmentManager
                .beginTransaction()
                .disallowAddToBackStack()
                .remove(fragment)
                .commit();
    }
}
