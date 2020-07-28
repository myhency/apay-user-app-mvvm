package com.autoever.apay_user_app.ui.card.use.history;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;


@Module
public class CardUseHistoryFragmentModule {

    @Provides
    CardUseHistoryAdapter provideCardUseHistoryAdapter() {
        return new CardUseHistoryAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(CardUseHistoryFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }
}