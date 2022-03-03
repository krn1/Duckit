package com.duckit.repository;

import com.duckit.repository.local.PreferencesHelper;

import java.io.IOException;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {
    private PreferencesHelper session;

    @Inject
    public TokenInterceptor(PreferencesHelper session) {
        this.session = session;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Response response = chain.proceed(chain.request());

        // if 'x-auth-token' is available into the response header
        // save the new token into session.The header key can be
        // different upon implementation of backend.
        String newToken = response.header("x-auth-token");
        if (newToken != null) {
           // session.saveToken(newToken);
        }

        return response;
    }
}