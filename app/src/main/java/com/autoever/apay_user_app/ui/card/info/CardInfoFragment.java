package com.autoever.apay_user_app.ui.card.info;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.androidnetworking.error.ANError;
import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.data.model.api.CardUseHistoryResponse;
import com.autoever.apay_user_app.databinding.FragmentCardInfoBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;

import java.util.List;

import javax.inject.Inject;


public class CardInfoFragment extends BaseFragment<FragmentCardInfoBinding, CardInfoViewModel>
        implements CardInfoNavigator, CardInfoAdapter.CardUseHistoryListener {

    public static final String TAG = CardInfoFragment.class.getSimpleName();

    private FragmentCardInfoBinding mFragmentCardInfoBinding;

    @Inject
    ViewModelProviderFactory factory;
    @Inject
    CardInfoAdapter mCardInfoAdapter;
    @Inject
    LinearLayoutManager mLayoutManager;

    private CardInfoViewModel mCardInfoViewModel;

    public static CardInfoFragment newInstance() {
        Bundle args = new Bundle();
        CardInfoFragment fragment = new CardInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_card_info;
    }

    @Override
    public CardInfoViewModel getViewModel() {
        mCardInfoViewModel = ViewModelProviders.of(this, factory)
                .get(CardInfoViewModel.class);
        return mCardInfoViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCardInfoViewModel.setNavigator(this);
        mCardInfoAdapter.setListener(this);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentCardInfoBinding = getViewDataBinding();
        setup();
        mCardInfoViewModel.fetchCardUseHistoryContents(
                1,
                4,
                1,
                1
        );
    }

    private void setup() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentCardInfoBinding.paymentHistoryList.setLayoutManager(mLayoutManager);
        mFragmentCardInfoBinding.paymentHistoryList.setAdapter(mCardInfoAdapter);
    }

    @Override
    public void handleError(Throwable throwable) {
        ANError anError = (ANError) throwable;
        Log.d("debug", "anError.getErrorBody():" + anError.getErrorBody());
        Log.d("debug", "throwable message: " + throwable.getMessage());
    }

    @Override
    public void updateCardUseHistoryContent(List<CardUseHistoryResponse.CardUseHistory.Content> contentList) {
        mCardInfoAdapter.addItems(contentList);
    }

    @Override
    public void onRetryClick() {

    }
}