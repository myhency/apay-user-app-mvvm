package com.autoever.apay_user_app.ui.card.info;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.autoever.apay_user_app.data.model.api.CardUseHistoryResponse;
import com.autoever.apay_user_app.databinding.ItemCardUseEmptyViewBinding;
import com.autoever.apay_user_app.databinding.ItemCardUseHistoryBinding;
import com.autoever.apay_user_app.ui.base.BaseViewHolder;

import java.util.List;


public class CardInfoAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    public static final int VIEW_TYPE_EMPTY = 0;

    public static final int VIEW_TYPE_NORMAL = 1;

    private List<CardUseHistoryResponse.CardUseHistory.Content> mCardUseHistoryResponseList;

    private CardUseHistoryListener mListener;

    public CardInfoAdapter(List<CardUseHistoryResponse.CardUseHistory.Content> cardUseHistoryResponseList) {
        this.mCardUseHistoryResponseList = cardUseHistoryResponseList;
    }

    @Override
    public int getItemCount() {
        if(mCardUseHistoryResponseList != null && mCardUseHistoryResponseList.size() > 0) {
            return mCardUseHistoryResponseList.size();
        } else {
            return 1;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(mCardUseHistoryResponseList != null && !mCardUseHistoryResponseList.isEmpty()) {
            return VIEW_TYPE_NORMAL;
        } else {
            return VIEW_TYPE_EMPTY;
        }
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.OnBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case VIEW_TYPE_NORMAL:
                ItemCardUseHistoryBinding cardUseHistoryBinding = ItemCardUseHistoryBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false);
                return new CardUseHistoryViewHolder(cardUseHistoryBinding);
            case VIEW_TYPE_EMPTY:
            default:
                ItemCardUseEmptyViewBinding emptyViewBinding = ItemCardUseEmptyViewBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false);
                return new EmptyViewHolder(emptyViewBinding);
        }
    }

    public void addItems(List<CardUseHistoryResponse.CardUseHistory.Content> contentList) {
        mCardUseHistoryResponseList.addAll(contentList);
        notifyDataSetChanged();
    }

    public void clearItems() {
        mCardUseHistoryResponseList.clear();
    }

    public void setListener(CardUseHistoryListener listener) {
        this.mListener = listener;
    }

    public interface CardUseHistoryListener {

        void onRetryClick();
    }

    public class CardUseHistoryViewHolder extends BaseViewHolder implements HistoryItemViewModel.CardUseHistoryListener {

        private ItemCardUseHistoryBinding mBinding;

        private HistoryItemViewModel mHistoryItemViewModel;

        public CardUseHistoryViewHolder(ItemCardUseHistoryBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void OnBind(int position) {
            final CardUseHistoryResponse.CardUseHistory.Content content = mCardUseHistoryResponseList.get(position);
            mHistoryItemViewModel = new HistoryItemViewModel(content, this);
            mBinding.setViewModel(mHistoryItemViewModel);

            mBinding.executePendingBindings();
        }

        @Override
        public void onItemClick() {

        }
    }

    public class EmptyViewHolder extends BaseViewHolder implements HistoryEmptyItemViewModel.HistoryEmptyItemViewModelListener {

        private ItemCardUseEmptyViewBinding mBinding;

        public EmptyViewHolder(ItemCardUseEmptyViewBinding binding) {
            super(binding.getRoot());
            this.mBinding = binding;
        }

        @Override
        public void OnBind(int position) {
            HistoryEmptyItemViewModel emptyItemViewModel = new HistoryEmptyItemViewModel(this);
            mBinding.setViewModel(emptyItemViewModel);
        }

        @Override
        public void onRetryClick() {
            mListener.onRetryClick();
        }
    }
}
