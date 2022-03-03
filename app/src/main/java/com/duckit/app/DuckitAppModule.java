package com.duckit.app;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class DuckitAppModule {

    private static final String PREF_FILE_NAME = "/duckit/pref/";
    private final DuckitApp application;

    DuckitAppModule(DuckitApp application) {
        this.application = application;
    }

    @Provides
    public DuckitApp provideApplication() {
        return application;
    }

    @Provides
    Context provideContext() {
        return application;
    }


    @Provides
    @Singleton
    public SharedPreferences provideSharedPreference(Context context) {
        return context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

}
