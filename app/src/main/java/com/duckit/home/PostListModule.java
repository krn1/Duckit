package com.duckit.home;

import com.duckit.scope.PerActivity;

import dagger.Module;
import dagger.Provides;
import io.reactivex.disposables.CompositeDisposable;

@Module
class PostListModule {
    private PostListContract.View view;

    PostListModule(PostListContract.View view) {
        this.view = view;
    }

    @PerActivity
    @Provides
    PostListContract.View providesView() {
        return view;
    }

    @PerActivity
    @Provides
    CompositeDisposable providesDisposable() {
        return new CompositeDisposable();
    }
}
