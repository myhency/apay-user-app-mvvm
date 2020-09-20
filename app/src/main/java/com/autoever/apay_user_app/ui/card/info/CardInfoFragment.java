package com.autoever.apay_user_app.ui.card.info;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.error.ANError;
import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.data.model.api.CardUseHistoryResponse;
import com.autoever.apay_user_app.databinding.FragmentCardInfoBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.autoever.apay_user_app.ui.card.use.CardUseActivity;
import com.autoever.apay_user_app.ui.charge.ChargeActivity;
import com.autoever.apay_user_app.ui.refund.RefundActivity;

import java.util.List;

import javax.inject.Inject;


public class CardInfoFragment extends BaseFragment<FragmentCardInfoBinding, CardInfoFragmentViewModel>
        implements CardInfoNavigator, CardInfoAdapter.CardUseHistoryListener {

    public static final String TAG = CardInfoFragment.class.getSimpleName();

    private static int PAGE_NO = 0;
    private final static int PAGE_SIZE = 10;

    private FragmentCardInfoBinding mFragmentCardInfoBinding;

    @Inject
    ViewModelProviderFactory factory;
    @Inject
    CardInfoAdapter mCardInfoAdapter;
    @Inject
    LinearLayoutManager mLayoutManager;

    private CardInfoFragmentViewModel mCardInfoFragmentViewModel;

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
    public CardInfoFragmentViewModel getViewModel() {
        mCardInfoFragmentViewModel = ViewModelProviders.of(this, factory)
                .get(CardInfoFragmentViewModel.class);
        return mCardInfoFragmentViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d("debug", "CardInfoFragment onCreate");
        super.onCreate(savedInstanceState);
        mCardInfoFragmentViewModel.setNavigator(this);
        mCardInfoAdapter.setListener(this);
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
        Log.d("debug", "CardInfoFragment onViewCreated");
        mFragmentCardInfoBinding = getViewDataBinding();
        setup();

        callCardUseHistoryContents(PAGE_NO);

    }

    private void callCardUseHistoryContents(int pageNo) {
        mCardInfoFragmentViewModel.fetchCardUseHistoryContents(
                1,
                4,
                pageNo,
                PAGE_SIZE
        );
    }

    private void setup() {
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentCardInfoBinding.paymentHistoryList.setLayoutManager(mLayoutManager);
        mFragmentCardInfoBinding.paymentHistoryList.setAdapter(mCardInfoAdapter);

        mCardInfoAdapter.onItemClick((paymentHistoryId, paymentStatus) -> {
            // Nothing to do.
        });

        mFragmentCardInfoBinding.paymentHistoryList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager())
                        .findLastCompletelyVisibleItemPosition();
                int itemTotalCount = recyclerView.getAdapter().getItemCount();

                int firstVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findFirstCompletelyVisibleItemPosition();

                Log.d("debug", "firstVisibleItemPosition: " + firstVisibleItemPosition);
                Log.d("debug", "lastVisibleItemPosition: " + lastVisibleItemPosition);
                Log.d("debug", "itemTotalCount: " + itemTotalCount);

                if (lastVisibleItemPosition + 1 == itemTotalCount) {
                    PAGE_NO = PAGE_NO + 1;
                    callCardUseHistoryContents(PAGE_NO);
                }
            }
        });

        mFragmentCardInfoBinding.swipeContainer.setOnRefreshListener(() -> {
            Log.i("debug", "onRefresh called from SwipeRefreshLayout");
            PAGE_NO = 0;
            callCardUseHistoryContents(PAGE_NO);
        });

        mFragmentCardInfoBinding.cardChargeButton.setOnClickListener(v -> {
            getBaseActivity().finish();
            openChargeActivity();
        });

        mFragmentCardInfoBinding.paybackButton.setOnClickListener(v -> {
            openRefundActivity();
        });

        mFragmentCardInfoBinding.useListTextview.setOnClickListener(v -> {
            Log.d("debug", "openCardUseHistoryActivity");
            Intent intent = CardUseActivity.newIntent(getBaseActivity());
            startActivity(intent);
        });
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
    public void onCompleteUpdatePaymentHistoryList() {
        if (mFragmentCardInfoBinding.swipeContainer.isRefreshing()) {
            mFragmentCardInfoBinding.swipeContainer.setRefreshing(false);
        }
    }

    @Override
    public void openChargeActivity() {
        Intent intent = ChargeActivity.newIntent(getActivity());
        startActivity(intent);
    }

    @Override
    public void openRefundActivity() {
        Intent intent = RefundActivity.newIntent(getActivity());
        startActivity(intent);
    }

    @Override
    public void onRetryClick() {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("debug", "CardInfoFragment onAttach");
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("debug", "CardInfoFragment onActivityCreated");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("debug", "CardInfoFragment onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("debug", "CardInfoFragment onResume");
        mCardInfoFragmentViewModel.loadUserBalance();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("debug", "CardInfoFragment onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("debug", "CardInfoFragment onStop");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("debug", "CardInfoFragment onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("debug", "CardInfoFragment onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("debug", "CardInfoFragment onDetach");
        PAGE_NO = 0;
    }
}