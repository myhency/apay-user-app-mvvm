package com.autoever.apay_user_app.ui.card.use.detail;

import androidx.transition.Visibility;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class CardUseDetailFragmentProvider {

    @ContributesAndroidInjector
    abstract CardUseDetailFragment providerCardUseDetailFactory();
}
