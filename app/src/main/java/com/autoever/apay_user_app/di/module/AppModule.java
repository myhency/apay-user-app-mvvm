package com.autoever.apay_user_app.di.module;

import android.app.Application;
import android.content.Context;

import androidx.room.Room;

import com.autoever.apay_user_app.R;
import com.autoever.apay_user_app.data.AppDataManager;
import com.autoever.apay_user_app.data.DataManager;
import com.autoever.apay_user_app.data.local.db.AppDatabase;
import com.autoever.apay_user_app.data.local.db.AppDbHelper;
import com.autoever.apay_user_app.data.local.db.DbHelper;
import com.autoever.apay_user_app.data.remote.ApiHelper;
import com.autoever.apay_user_app.data.remote.AppApiHelper;
import com.autoever.apay_user_app.di.DatabaseInfo;
import com.autoever.apay_user_app.utils.AppConstants;
import com.autoever.apay_user_app.utils.rx.AppSchedulerProvider;
import com.autoever.apay_user_app.utils.rx.SchedulerProvider;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.github.inflationx.calligraphy3.CalligraphyConfig;

@Module
public class AppModule {

    @Provides
    @Singleton
    ApiHelper provideApiHelper(AppApiHelper appApiHelper) {
        return appApiHelper;
    }

    @Provides
    @Singleton
    DbHelper provideDbHelper(AppDbHelper appDbHelper) {
        return appDbHelper;
    }

    @Provides
    @Singleton
    DataManager provideDataManager(AppDataManager appDataManager) {
        return appDataManager;
    }

    @Provides
    @Singleton
    AppDatabase provideAppDatabase(@DatabaseInfo String dbName, Context context) {
        return Room.databaseBuilder(context, AppDatabase.class, dbName).fallbackToDestructiveMigration()
                .build();
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName() {
        return AppConstants.DB_NAME;
    }

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    SchedulerProvider provideSchedulerProvider() {
        return new AppSchedulerProvider();
    }

    @Provides
    @Singleton
    CalligraphyConfig provideCalligraphyDefaultConfig() {
        return new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/spoqa_han_sans_regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build();
    }
}
