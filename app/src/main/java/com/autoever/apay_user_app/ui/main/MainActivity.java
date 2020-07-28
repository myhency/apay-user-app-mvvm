package com.autoever.apay_user_app.ui.main;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.androidnetworking.error.ANError;
import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.ActivityMainBinding;
import com.autoever.apay_user_app.databinding.NavHeaderMainBinding;
import com.autoever.apay_user_app.ui.base.BaseActivity;
import com.autoever.apay_user_app.ui.card.use.CardUseActivity;
import com.autoever.apay_user_app.ui.home.HomeFragment;
import com.autoever.apay_user_app.ui.splash.SplashActivity;
import com.autoever.apay_user_app.ui.user.login.LoginActivity;
import com.google.android.material.navigation.NavigationView;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends BaseActivity<ActivityMainBinding, MainViewModel> implements MainNavigator, HasSupportFragmentInjector {

    @Inject
    DispatchingAndroidInjector<Fragment> fragmentDispatchingAndroidInjector;

    @Inject
    ViewModelProviderFactory factory;

    private ActivityMainBinding mActivityMainBinding;
    private MainViewModel mMainViewModel;
    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;
    private Toolbar mToolbar;

    private AppBarConfiguration mAppBarConfiguration;

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public MainViewModel getViewModel() {
        mMainViewModel = ViewModelProviders.of(this, factory).get(MainViewModel.class);
        return mMainViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityMainBinding = getViewDataBinding();
        mMainViewModel.setNavigator(this);

        setup();
    }

    private void setup() {
        mDrawer = mActivityMainBinding.drawerLayout;
        mToolbar = mActivityMainBinding.toolbar;
        mNavigationView = mActivityMainBinding.navView;

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawer,
                mToolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        ) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }
        };

        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        setupNavMenu();

    }

    private void setupNavMenu() {
        NavHeaderMainBinding navHeaderMainBinding = DataBindingUtil.inflate(getLayoutInflater(),
                R.layout.nav_header_main, mActivityMainBinding.navView, false);
        mActivityMainBinding.navView.addHeaderView(navHeaderMainBinding.getRoot());
        navHeaderMainBinding.setViewModel(mMainViewModel);

        navHeaderMainBinding.drawerUseList.setOnClickListener(v -> {
            openCardUseHistoryActivity();
        });

        //menu 우측에 아이콘 삽입.
        for (int i = 0; i < mNavigationView.getMenu().size(); i++) {
            mNavigationView.getMenu().getItem(i).setActionView(R.layout.menu_image);
        }

//        navigationView.setNavigationItemSelectedListener(this);

        //home menu 는 보이지 않게 설정.
        Menu menu = mNavigationView.getMenu();
        MenuItem target = menu.findItem(R.id.nav_home);
        target.setVisible(false);

        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home)
                .setDrawerLayout(mDrawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(mNavigationView, navController);

        mActivityMainBinding.logout.setOnClickListener(v -> {
            //여기서 다이얼로그를 띄워준다.
            // custom dialog
            final Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.logout_dialog);

            Button okButton = dialog.findViewById(R.id.ok_button);
            Button cancelButton = dialog.findViewById(R.id.cancel_button);

            okButton.setOnClickListener(v1 -> {
                dialog.dismiss();
                mMainViewModel.logout();
            });

            cancelButton.setOnClickListener(v2 -> {
                dialog.dismiss();
            });

            dialog.show();
        });

        mNavigationView.setNavigationItemSelectedListener(item -> {
            mDrawer.closeDrawer(GravityCompat.START);
            switch (item.getItemId()) {
                case R.id.nav_payment:
                    openPaymentActivity();
                    return true;
                case R.id.nav_charge:
                    openCardChargeActivity();
                    return true;
                case R.id.nav_account_management:
                    openBankAccountManagementActivity();
                    return true;
                case R.id.nav_find_store:
                    return true;
                case R.id.nav_faq:
                    openFaqActivity();
                    return true;
                default:
                    return false;
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("debug", "MainActivity resume");
    }

    @Override
    public void handleError(Throwable throwable) {
        //TODO. response code 에 따라서 처리해야 함.
        ANError anError = (ANError) throwable;
        Log.d("debug", "anError.getErrorBody():" + anError.getErrorBody());
        Log.d("debug", "throwable message: " + throwable.getMessage());
    }

    @Override
    public void openLoginActivity() {
        Log.d("debug", "LoginActivity Open");
        Intent intent = LoginActivity.newIntent(MainActivity.this);
        startActivity(intent);
        finish();
    }

    @Override
    public void openPaymentActivity() {

    }

    @Override
    public void openCardChargeActivity() {

    }

    @Override
    public void openCardInfoActivity() {

    }

    @Override
    public void openBankAccountManagementActivity() {

    }

    @Override
    public void openCardUseHistoryActivity() {
        Log.d("debug", "openCardUseHistoryActivity");
        Intent intent = CardUseActivity.newIntent(MainActivity.this);
        startActivity(intent);
    }

    @Override
    public void openSettingsActivity() {

    }

    @Override
    public void openNotificationActivity() {

    }

    @Override
    public void openNoticeBoardActivity() {

    }

    @Override
    public void openFaqActivity() {

    }

    @Override
    public void openUserProfileActivity() {

    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return fragmentDispatchingAndroidInjector;
    }
}
