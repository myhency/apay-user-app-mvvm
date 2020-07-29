package com.autoever.apay_user_app.ui.card.use.history;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.autoever.apay_user_app.ui.card.info.CardInfoAdapter;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;


@Module
public class CardUseHistoryFragmentModule {

    @Provides
    CardInfoAdapter provideCardUseHistoryAdapter() {
        return new CardInfoAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(CardUseHistoryFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }
}