package com.autoever.apay_user_app.ui.card.use.history;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.data.model.api.CardUseHistoryResponse;
import com.autoever.apay_user_app.databinding.FragmentCardUseHistoryBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.autoever.apay_user_app.ui.card.info.CardInfoAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;


public class CardUseHistoryFragment extends BaseFragment<FragmentCardUseHistoryBinding, CardUseHistoryViewModel>
        implements CardUseHistoryNavigator, CardInfoAdapter.CardUseHistoryListener {

    public static final String TAG = CardUseHistoryFragment.class.getSimpleName();

    private static int PAGE_NO = 0;
    private final static int PAGE_SIZE = 20;

    private FragmentCardUseHistoryBinding mFragmentCardUseHistoryBinding;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy년 MM월");
    private Date date = new Date();

    @Inject
    ViewModelProviderFactory factory;
    @Inject
    CardInfoAdapter mCardUseHistoryAdapter;
    @Inject
    LinearLayoutManager mLayoutManager;

    private CardUseHistoryViewModel mCardUseHistoryViewModel;

    public static CardUseHistoryFragment newInstance() {
        Bundle args = new Bundle();
        CardUseHistoryFragment fragment = new CardUseHistoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_card_use_history;
    }

    @Override
    public CardUseHistoryViewModel getViewModel() {
        mCardUseHistoryViewModel = ViewModelProviders.of(this, factory)
                .get(CardUseHistoryViewModel.class);
        return mCardUseHistoryViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCardUseHistoryViewModel.setNavigator(this);
        mCardUseHistoryAdapter.setListener(this);
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
        mFragmentCardUseHistoryBinding = getViewDataBinding();

        setup();

        callCardUseHistoryContentsByFilterAndMonth(PAGE_NO, date, null);
    }

    private void callCardUseHistoryContents(int pageNo) {
        mCardUseHistoryViewModel.fetchUseHistoryContents(
                1,
                4,
                pageNo,
                PAGE_SIZE,
                null,
                null
        );
    }

    private void setup() {
        //통신사 선택 dropdown 박스 세팅
        String[] FILTERS = new String[]{"전체","충전", "결제", "결제취소"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                getContext(),
                R.layout.dropdown_menu_popup_item,
                FILTERS
        );
        mFragmentCardUseHistoryBinding.filledExposedDropdown.setAdapter(adapter);

        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mFragmentCardUseHistoryBinding.useHistoryList.setLayoutManager(mLayoutManager);
        mFragmentCardUseHistoryBinding.useHistoryList.setAdapter(mCardUseHistoryAdapter);

        mCardUseHistoryAdapter.onItemClick((paymentHistoryId, paymentStatus) -> {
            try {
                JSONObject data = new JSONObject();
                data.put("paymentHistoryId", paymentHistoryId);
                data.put("paymentStatus", paymentStatus);
                getBaseActivity().onReceivedMessageFromFragment(TAG, data);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        mFragmentCardUseHistoryBinding.useHistoryList.addOnScrollListener(new RecyclerView.OnScrollListener() {

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
                    callCardUseHistoryContentsByFilterAndMonth(PAGE_NO, date, null);
                }
            }
        });

        mFragmentCardUseHistoryBinding.swipeContainer.setOnRefreshListener(() -> {
            Log.i("debug", "onRefresh called from SwipeRefreshLayout");
            PAGE_NO = 0;
//            callCardUseHistoryContents(PAGE_NO);
            callCardUseHistoryContentsByFilterAndMonth(PAGE_NO, date, null);
        });

        mFragmentCardUseHistoryBinding.currentMonth.setText(
                simpleDateFormat.format(date)
        );

        mFragmentCardUseHistoryBinding.prevMonth.setOnClickListener(v -> {
            date = Date.from(
                    date.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                            .minusMonths(1)
                            .atStartOfDay(ZoneId.systemDefault())
                            .toInstant());

            mFragmentCardUseHistoryBinding.currentMonth.setText(simpleDateFormat.format(date));
            PAGE_NO = 0;
            callCardUseHistoryContentsByFilterAndMonth(PAGE_NO, date, null);
        });

        mFragmentCardUseHistoryBinding.postMonth.setOnClickListener(v -> {
            date = Date.from(
                    date.toInstant()
                            .atZone(ZoneId.systemDefault())
                            .toLocalDate()
                            .plusMonths(1)
                            .atStartOfDay(ZoneId.systemDefault())
                            .toInstant());

            Date now = new Date();
            if (now.compareTo(date) < 0) {
                date = now;
                return;
            }

            mFragmentCardUseHistoryBinding.currentMonth.setText(simpleDateFormat.format(date));
            PAGE_NO = 0;
            callCardUseHistoryContentsByFilterAndMonth(PAGE_NO, date, null);
        });
    }

    private void callCardUseHistoryContentsByFilterAndMonth(int pageNo, Date date, String filter) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
        String selectedMonth = simpleDateFormat.format(date);

        mCardUseHistoryViewModel.fetchUseHistoryContents(
                1,
                4,
                pageNo,
                PAGE_SIZE,
                selectedMonth,
                filter
        );
    }

    @Override
    public void handleError(Throwable throwable) {

    }

    @Override
    public void onCompleteUpdatePaymentHistoryList() {

        if (mFragmentCardUseHistoryBinding.swipeContainer.isRefreshing()) {
            mFragmentCardUseHistoryBinding.swipeContainer.setRefreshing(false);
        }
    }

    @Override
    public void noDataFound() {

    }

    @Override
    public void updateCardUseHistoryContent(List<CardUseHistoryResponse.CardUseHistory.Content> contentList) {
        mCardUseHistoryAdapter.addItems(contentList);
    }

    @Override
    public void onRetryClick() {

    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("debug", "CardUseHistoryFragment onDetach");
        PAGE_NO = 0;
    }
}