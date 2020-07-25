package com.autoever.apay_user_app.utils;

import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.autoever.apay_user_app.data.model.api.CardUseHistoryResponse;
import com.autoever.apay_user_app.ui.card.info.CardInfoAdapter;

import java.util.List;

public final class BindingUtils {

    private BindingUtils() {

    }

    @BindingAdapter({"adapter"})
    public static void addCardUseHistoryItems(RecyclerView recyclerView, List<CardUseHistoryResponse.CardUseHistory.Content> contentList) {
        CardInfoAdapter adapter = (CardInfoAdapter) recyclerView.getAdapter();
        if(adapter != null) {
            adapter.clearItems();
            adapter.addItems(contentList);
        }
    }
}
