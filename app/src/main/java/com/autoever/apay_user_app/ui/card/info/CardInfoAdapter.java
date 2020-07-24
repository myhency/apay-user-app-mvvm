package com.autoever.apay_user_app.ui.card.info;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.autoever.apay_user_app.data.model.api.CardUseHistoryResponse;
import com.autoever.apay_user_app.ui.base.BaseViewHolder;

import java.util.List;


public class CardInfoAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private List<CardUseHistoryResponse> mCardUseHistoryResponseList;

    public CardInfoAdapter(List<CardUseHistoryResponse> cardUseHistoryResponseList) {
        this.mCardUseHistoryResponseList = cardUseHistoryResponseList;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
