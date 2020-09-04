package com.autoever.apay_user_app.ui.card.use.history;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CardUseHistoryFragmentProvider {

    @ContributesAndroidInjector(modules = {
            CardUseHistoryFragmentModule.class
    })
    abstract CardUseHistoryFragment provideCardUseHistoryFragmentFactory();
}