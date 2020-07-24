package com.autoever.apay_user_app.ui.card.info;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CardInfoFragmentProvider {

    @ContributesAndroidInjector(modules = {
            CardInfoFragmentModule.class
    })
    abstract CardInfoFragment provideCardInfoFragmentFactory();
}
