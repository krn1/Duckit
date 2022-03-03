package com.duckit.home;

import com.duckit.app.DuckitAppComponent;
import com.duckit.scope.PerActivity;

import dagger.Component;

@PerActivity
@Component(dependencies = DuckitAppComponent.class, modules = PostListModule.class)
interface PostListComponent {
    void inject(PostListActivity postListActivity);
}
