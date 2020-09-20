package com.autoever.apay_user_app.ui.card.use.detail;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProviders;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.data.model.api.PaymentRefundReadyResponse;
import com.autoever.apay_user_app.databinding.FragmentCardUseDetailBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import retrofit2.HttpException;


public class CardUseDetailFragment extends BaseFragment<FragmentCardUseDetailBinding, CardUseDetailViewModel>
        implements CardUseDetailNavigator {

    public static final String TAG = CardUseDetailFragment.class.getSimpleName();

    private FragmentCardUseDetailBinding mFragmentCardUseDetailBinding;

    @Inject
    ViewModelProviderFactory factory;

    private CardUseDetailViewModel mCardUseDetailViewModel;

    public static CardUseDetailFragment newInstance(Long paymentHistoryId, String paymentStatus) {
        Bundle args = new Bundle();
        args.putLong("paymentHistoryId", paymentHistoryId);
        args.putString("paymentStatus", paymentStatus);
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
        //back button 을 눌렀을 경우 Fragment 를 종료한다.
        getBaseActivity().getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                getBaseActivity().onFragmentDetached(TAG);
            }
        });
    }


    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentCardUseDetailBinding = getViewDataBinding();

        setup();
    }

    private void setup() {
        Long paymentHistoryId = getArguments().getLong("paymentHistoryId");
        String paymentStatus = getArguments().getString("paymentStatus");
        mCardUseDetailViewModel.fetchUseHistoryContentsDetail(paymentHistoryId);
//        mFragmentCardUseDetailBinding.confirmButton.setOnClickListener(v -> getBaseActivity().onFragmentDetached(TAG));
//        if (paymentStatus.equals("PAY_COMPLETE")) {
//            mFragmentCardUseDetailBinding.finishTextview.setText("결제 취소");
//            mFragmentCardUseDetailBinding.finishTextview.setOnClickListener(v -> mCardUseDetailViewModel.doPaymentRefundReadyCall());
//        } else {
//            mFragmentCardUseDetailBinding.finishTextview.setText("확인");
//            mFragmentCardUseDetailBinding.finishTextview.setOnClickListener(v -> getBaseActivity().onFragmentDetached(TAG));
//        }
    }

    @Override
    public void handleError(Throwable throwable) {
        HttpException httpException = (HttpException) throwable;

        switch (httpException.code()) {
            default:
                try {
                    JSONObject data = new JSONObject();
                    data.put("fail", mFragmentCardUseDetailBinding.paymentRefundButton.getText().toString());
                    getBaseActivity().onReceivedMessageFromFragment(TAG, data);
                    getBaseActivity().onFragmentDetached(TAG);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

//    @Override
//    public void openPaymentCancelFragment(Long paymentHistoryId) {
//        try {
//            JSONObject data = new JSONObject();
//            data.put("paymentHistoryId", paymentHistoryId);
//            getBaseActivity().onReceivedMessageFromFragment(TAG, data);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    public void userConfirm(View view) {
        Log.d("debug", "userConfirm");
    }

    @Override
    public void openPaymentRefundReadyReceiptFragment(PaymentRefundReadyResponse paymentRefundReadyResponse) {
        try {
            JSONObject data = new JSONObject();
            data.putOpt("paymentRefundReadyResponse", paymentRefundReadyResponse);
            getBaseActivity().onReceivedMessageFromFragment(TAG, data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setBottomButton(String paymentStatus, boolean refundRequested, boolean canceled) {
        switch (paymentStatus) {
            case "PAY_COMPLETE":
                if (!refundRequested && !canceled) {
                    mFragmentCardUseDetailBinding.paymentRefundButton
                            .setBackgroundColor(getResources().getColor(R.color.colorApay));
                    mFragmentCardUseDetailBinding.paymentRefundButton
                            .setText("결제 취소");
                    mFragmentCardUseDetailBinding.paymentRefundButton
                            .setOnClickListener(v -> mCardUseDetailViewModel.doPaymentRefundReadyCall());
                } else if (refundRequested && !canceled) {
                    mFragmentCardUseDetailBinding.paymentRefundButton
                            .setBackgroundColor(getResources().getColor(R.color.colorApay));
                    mFragmentCardUseDetailBinding.paymentRefundButton
                            .setText("결제 취소 요청 취소");
                    mFragmentCardUseDetailBinding.paymentRefundButton
                            .setOnClickListener(v -> {
                                mCardUseDetailViewModel.doPaymentRefundReadyCancelCall();
                                getBaseActivity().onFragmentDetached(TAG);
                            });
                } else if (!refundRequested && canceled) {
                    mFragmentCardUseDetailBinding.paymentRefundButton
                            .setBackgroundColor(getResources().getColor(R.color.colorApay));
                    mFragmentCardUseDetailBinding.paymentRefundButton
                            .setText("확인");
                    mFragmentCardUseDetailBinding.paymentRefundButton
                            .setOnClickListener(v -> getBaseActivity().onFragmentDetached(TAG));
                }
//
//
//                if(refundRequested) {
//                    mFragmentCardUseDetailBinding.paymentRefundButton
//                            .setBackgroundColor(getResources().getColor(R.color.borderColor));
//                    mFragmentCardUseDetailBinding.paymentRefundButton
//                            .setText("결제 취소 요청중");
//                    mFragmentCardUseDetailBinding.paymentRefundButton
//                            .setOnClickListener(v -> getBaseActivity().onFragmentDetached(TAG));
//                } else {
//                    mFragmentCardUseDetailBinding.paymentRefundButton
//                            .setBackgroundColor(getResources().getColor(R.color.colorApay));
//                    mFragmentCardUseDetailBinding.paymentRefundButton
//                            .setText("결제 취소");
//                    mFragmentCardUseDetailBinding.paymentRefundButton
//                            .setOnClickListener(v -> mCardUseDetailViewModel.doPaymentRefundReadyCall());
//                }
                break;

            default:
                mFragmentCardUseDetailBinding.paymentRefundButton
                        .setBackgroundColor(getResources().getColor(R.color.colorApay));
                mFragmentCardUseDetailBinding.paymentRefundButton
                        .setText("확인");
                mFragmentCardUseDetailBinding.paymentRefundButton
                        .setOnClickListener(v -> getBaseActivity().onFragmentDetached(TAG));
                break;
        }
    }
}
