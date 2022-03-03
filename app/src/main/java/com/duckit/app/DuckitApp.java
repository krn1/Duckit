package com.duckit.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import timber.log.Timber;

public class DuckitApp extends Application {

    private DuckitAppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        // dependency injection object graph
        component = DaggerDuckitAppComponent.builder().duckitAppModule(new DuckitAppModule(this)).build();
        component.inject(this);

        Timber.plant(new Timber.DebugTree());
        initializeFresco();
    }

    public DuckitAppComponent getComponent() {
        return component;
    }

    // region private

    private void initializeFresco() {
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setDownsampleEnabled(true)
                .build();
        Fresco.initialize(this, config);
    }

    //endregion

}
