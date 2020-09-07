package com.autoever.apay_user_app.ui.card.use.fail;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.autoever.apay_user_app.BR;
import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.ViewModelProviderFactory;
import com.autoever.apay_user_app.databinding.FragmentPaymentRefundReadyFailBinding;
import com.autoever.apay_user_app.ui.base.BaseFragment;
import com.autoever.apay_user_app.ui.card.use.history.CardUseHistoryFragment;

import javax.inject.Inject;


public class PaymentRefundReadyFailFragment extends BaseFragment<FragmentPaymentRefundReadyFailBinding, PaymentRefundReadyFailViewModel> implements PaymentRefundReadyFailNavigator {

    public static final String TAG = PaymentRefundReadyFailFragment.class.getSimpleName();

    private FragmentPaymentRefundReadyFailBinding mFragmentPaymentRefundReadyFailBinding;

    @Inject
    ViewModelProviderFactory factory;

    private PaymentRefundReadyFailViewModel mPaymentRefundReadyFailViewModel;

    public static PaymentRefundReadyFailFragment newInstance(String state) {
        Bundle args = new Bundle();
        args.putString("state", state);
        PaymentRefundReadyFailFragment fragment = new PaymentRefundReadyFailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getBindingVariable() {
        return BR.viewModel;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_payment_refund_ready_fail;
    }

    @Override
    public PaymentRefundReadyFailViewModel getViewModel() {
        mPaymentRefundReadyFailViewModel = ViewModelProviders.of(this, factory)
                .get(PaymentRefundReadyFailViewModel.class);
        return mPaymentRefundReadyFailViewModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mFragmentPaymentRefundReadyFailBinding = getViewDataBinding();
        
        setup();
    }

    private void setup() {
        Log.d("debug", getArguments().getString("state"));
        //결제취소 취소 요청이 오류가 난 경우
        if(!getArguments().getString("state").equals("결제 취소")) {
            mFragmentPaymentRefundReadyFailBinding.textview01.setText("결제취소 취소 요청 실패");
            mFragmentPaymentRefundReadyFailBinding.textview02.setText("결제취소 취소 요청 중 오류가 발생하여\n요청하신 처리를 완료할 수 없습니다.");
            mFragmentPaymentRefundReadyFailBinding.textview03.setText("확인버튼을 누르시면 이전화면으로 돌아가며\n결제취소 취소 요청을 완료하시려면 처음부터 다시 시도해 주세요.");
        }
        mFragmentPaymentRefundReadyFailBinding.finishTextview.setOnClickListener(v -> {
            getBaseActivity().onFragmentDetached(TAG);
        });
    }
}