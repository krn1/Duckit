package com.duckit.repository.local;

import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferencesHelper {

    private final SharedPreferences preferences;

    @Inject
    PreferencesHelper(SharedPreferences sharedPreferences) {
        preferences = sharedPreferences;
    }

    public void putString(@NonNull String key, @NonNull String value) {
        preferences.edit().putString(key, value).apply();
    }

    public String getString(@NonNull String key) {
        return preferences.getString(key, "");
    }

    public void putBoolean(@NonNull String key, @NonNull boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }

    public boolean getBoolean(@NonNull String key) {
        return preferences.getBoolean(key, false);
    }

    public void putInt(@NonNull String key, @NonNull boolean value) {
        preferences.edit().putBoolean(key, value).apply();
    }

    public int getInt(@NonNull String key) {
        return preferences.getInt(key, -1);
    }

    public void clear() {
        preferences.edit().clear().apply();
    }
}