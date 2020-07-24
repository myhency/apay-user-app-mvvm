package com.autoever.apay_user_app.ui.card.info;

import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;

@Module
public class CardInfoFragmentModule {

    @Provides
    CardInfoAdapter provideCardInfoAdapter() {
        return new CardInfoAdapter(new ArrayList<>());
    }

    @Provides
    LinearLayoutManager provideLinearLayoutManager(CardInfoFragment fragment) {
        return new LinearLayoutManager(fragment.getActivity());
    }
}
