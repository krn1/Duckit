package com.duckit.app;

import android.content.Context;
import android.content.SharedPreferences;

import com.duckit.repository.NetworkModule;
import com.duckit.repository.RestApi;
import com.duckit.repository.local.PreferencesHelper;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {DuckitAppModule.class, NetworkModule.class})
public interface DuckitAppComponent {

    void inject(DuckitApp application);

    DuckitApp application();

    Context context();

    RestApi restApi();

    SharedPreferences prefManager();

    PreferencesHelper preferencesHelper();
}
