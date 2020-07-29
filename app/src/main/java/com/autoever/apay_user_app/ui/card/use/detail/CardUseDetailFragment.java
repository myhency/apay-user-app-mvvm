package com.autoever.apay_user_app.ui.card.use.detail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentCardUseDetailBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.autoever.apay_user_app.ui.card.use.history.CardUseHistoryFragment;
import com.autoever.apay_user_app.ui.card.use.history.CardUseHistoryViewModel;

import javax.inject.Inject;


public class CardUseDetailFragment extends BaseFragment<FragmentCardUseDetailBinding, CardUseDetailViewModel>
        implements CardUseDetailNavigator {

    public static final String TAG = CardUseDetailFragment.class.getSimpleName();

    private FragmentCardUseDetailBinding mFragmentCardUseDetailBinding;

    @Inject
    ViewModelProviderFactory factory;

    private CardUseDetailViewModel mCardUseDetailViewModel;

    public static CardUseDetailFragment newInstance(Long paymentHistoryId) {
        Bundle args = new Bundle();
        args.putLong("paymentHistoryId", paymentHistoryId);
        CardUseDetailFragment fragment = new CardUseDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_card_use_detail;
    }

    @Override
    public CardUseDetailViewModel getViewModel() {
        mCardUseDetailViewModel = ViewModelProviders.of(this, factory)
                .get(CardUseDetailViewModel.class);
        return mCardUseDetailViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCardUseDetailViewModel.setNavigator(this);
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentCardUseDetailBinding = getViewDataBinding();

        setup();
    }

    private void setup() {
        mCardUseDetailViewModel.fetchUseHistoryContentsDetail(
                getArguments().getLong("paymentHistoryId"));
    }

    @Override
    public void handleError(Throwable throwable) {

    }
}