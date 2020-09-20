package com.autoever.apay_user_app.ui.user.mypage.password;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.ActivityChangeLoginPasswordBinding;
import com.autoever.apay_user_app.ui.auth.AuthFragment;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.payment.PaymentActivity;
import com.autoever.apay_user_app.utils.CommonUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class ChangeLoginPasswordActivity extends BaseActivity<ActivityChangeLoginPasswordBinding, ChangeLoginPasswordViewModel>
        implements ChangeLoginPasswordNavigator, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    ViewModelProviderFactory factory;

    private FragmentManager mFragmentManager;
    private ActivityChangeLoginPasswordBinding mActivityChangeLoginPasswordBinding;
    private ChangeLoginPasswordViewModel mChangeLoginPasswordViewModel;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, ChangeLoginPasswordActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_change_login_password;
    }

    @Override
    public ChangeLoginPasswordViewModel getViewModel() {
        mChangeLoginPasswordViewModel = ViewModelProviders.of(this, factory)
                .get(ChangeLoginPasswordViewModel.class);
        return mChangeLoginPasswordViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityChangeLoginPasswordBinding = getViewDataBinding();
        mChangeLoginPasswordViewModel.setNavigator(this);
        this.mFragmentManager = getSupportFragmentManager();

        setup();

        openAuthFragment();
    }

    private void setup() {
        setSupportActionBar(mActivityChangeLoginPasswordBinding.toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mActivityChangeLoginPasswordBinding.toolbarTitle.setText("로그인 비밀번호 변경");
        }
    }

    @Override
    public void openAuthFragment() {
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, AuthFragment.newInstance(), AuthFragment.TAG)
                .addToBackStack(AuthFragment.TAG)
                .commit();
    }

    @Override
    public void handleChangeLoginPassword(Throwable throwable) {

    }


    @Override
    public void onFragmentDetached(String tag) {
        Log.d("debug", "onFragmentDetached: " + tag);

        switch (tag) {
            case "AuthFragment":
                openChangeLoginPasswordFragment();
                break;
            case "ChangeLoginPasswordFragment":
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("debug", "onOptionsItemSelected:" + mFragmentManager.getBackStackEntryCount());
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                List<Fragment> fragments = mFragmentManager.getFragments();
                String fragmentTag = fragments.get(fragments.size() - 1).getTag();
                switch (fragmentTag) {
                    case "ChangeLoginPasswordFragment":
                    case "AuthFragment":
                        finish();
                        break;
                    default:
                        break;
                }
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openChangeLoginPasswordFragment() {
        mFragmentManager
                .beginTransaction()
                .replace(R.id.clRootView, ChangeLoginPasswordFragment.newInstance(), ChangeLoginPasswordFragment.TAG)
                .addToBackStack(ChangeLoginPasswordFragment.TAG)
                .commit();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}