package com.autoever.apay_user_app.ui.card;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import com.androidnetworking.error.ANError;
import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.ActivityCardInfoBinding;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.card.info.CardInfoFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class CardInfoActivity extends BaseActivity<ActivityCardInfoBinding, CardInfoViewModel> implements CardInfoNavigator, HasSupportFragmentInjector {

    public static final String TAG = CardInfoActivity.class.getSimpleName();

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    ViewModelProviderFactory factory;

    private ActivityCardInfoBinding mActivityCardInfoBinding;
    private CardInfoViewModel mCardInfoViewModel;
    private FragmentManager mFragmentManager;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CardInfoActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_card_info;
    }

    @Override
    public CardInfoViewModel getViewModel() {
        mCardInfoViewModel = ViewModelProviders.of(this, factory)
                .get(CardInfoViewModel.class);
        return mCardInfoViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityCardInfoBinding = getViewDataBinding();
        mCardInfoViewModel.setNavigator(this);

        setup();

        openCardInfoFragment();
    }

    private void setup() {
        this.mFragmentManager = getSupportFragmentManager();
        setSupportActionBar(mActivityCardInfoBinding.toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mActivityCardInfoBinding.toolbarTitle.setText("카드 정보");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.d("debug", "onOptionsItemSelected:" + item.toString());
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void handleError(Throwable throwable) {
        ANError anError = (ANError) throwable;
        Log.d("debug", "anError.getErrorBody():" + anError.getErrorBody());
        Log.d("debug", "throwable message: " + throwable.getMessage());
    }

    @Override
    public void openCardInfoFragment() {
        //카드정보화면으로 이동.
        Log.d("debug", "openCardInfoFragment");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, CardInfoFragment.newInstance(), CardInfoFragment.TAG)
                .addToBackStack(CardInfoFragment.TAG)
                .commit();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}