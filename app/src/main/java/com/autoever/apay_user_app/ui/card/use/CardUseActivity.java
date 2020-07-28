package com.autoever.apay_user_app.ui.card.use;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.ActivityCardUseBinding;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.card.use.history.CardUseHistoryFragment;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class CardUseActivity extends BaseActivity<ActivityCardUseBinding, CardUseViewModel> implements CardUseNavigator, HasSupportFragmentInjector {

    public static final String TAG = CardUseActivity.class.getSimpleName();

    @Inject
    ViewModelProviderFactory factory;
    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    private ActivityCardUseBinding mActivityCardUseBinding;
    private CardUseViewModel mCardUseViewModel;
    private FragmentManager mFragmentManager;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, CardUseActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_card_use;
    }

    @Override
    public CardUseViewModel getViewModel() {
        mCardUseViewModel = ViewModelProviders.of(this, factory)
                .get(CardUseViewModel.class);
        return mCardUseViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityCardUseBinding = getViewDataBinding();
        mCardUseViewModel.setNavigator(this);

        setup();

        openCardUseHistoryFragment();
    }

    private void openCardUseHistoryFragment() {
        //카드정보화면으로 이동.
        Log.d("debug", "openCardUseHistoryFragment");
        mFragmentManager
                .beginTransaction()
                .add(R.id.clRootView, CardUseHistoryFragment.newInstance(), CardUseHistoryFragment.TAG)
                .addToBackStack(CardUseHistoryFragment.TAG)
                .commit();
    }

    private void setup() {
        this.mFragmentManager = getSupportFragmentManager();
        setSupportActionBar(mActivityCardUseBinding.toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            mActivityCardUseBinding.toolbarTitle.setText("사용내역");
        }
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}