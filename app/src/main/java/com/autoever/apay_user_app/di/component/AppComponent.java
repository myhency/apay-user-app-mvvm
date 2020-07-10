package com.autoever.apay_user_app.di.component;

import android.app.Application;

import com.autoever.apay_user_app.ApayUserApp;
import com.autoever.apay_user_app.di.builder.ActivityBuilder;
import com.autoever.apay_user_app.di.module.AppModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, ActivityBuilder.class})
public interface AppComponent {

    void inject(ApayUserApp app);

    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(Application application);

        AppComponent build();
    }
}
