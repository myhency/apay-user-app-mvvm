package com.autoever.apay_user_app.ui.account.register.ars;

import android.os.Bundle;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentArsAuthBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;


public class ArsAuthFragment extends BaseFragment<FragmentArsAuthBinding, ArsAuthViewModel> implements ArsAuthNavigator {

    public static final String TAG = ArsAuthFragment.class.getSimpleName();

    private FragmentArsAuthBinding mFragmentArsAuthBinding;

    @Inject
    ViewModelProviderFactory factory;

    private ArsAuthViewModel mArsAuthViewModel;

    public static ArsAuthFragment newInstance() {
        Bundle args = new Bundle();
        ArsAuthFragment fragment = new ArsAuthFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_ars_auth;
    }

    @Override
    public ArsAuthViewModel getViewModel() {
        mArsAuthViewModel = ViewModelProviders.of(this, factory)
                .get(ArsAuthViewModel.class);
        return mArsAuthViewModel;
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
        mFragmentArsAuthBinding = getViewDataBinding();
        mArsAuthViewModel.setNavigator(this);
        setup();
    }

    private void setup() {

        mFragmentArsAuthBinding.finishTextview.setOnClickListener(v -> {
            try {
                JSONObject data = new JSONObject();
                data.put("authenticationCode", "123456");
                getBaseActivity().onReceivedMessageFromFragment(TAG, data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getBaseActivity().onFragmentDetached(TAG);
        });
    }
}