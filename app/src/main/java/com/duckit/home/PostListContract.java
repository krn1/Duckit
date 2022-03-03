package com.duckit.home;

import com.duckit.model.PostInfo;

import java.util.List;

interface PostListContract {
    interface View {
        void showSpinner();

        void hideSpinner();

        void showPosts(List<PostInfo> postInfoList);

        void UpdatePostList(List<PostInfo> postInfoList);

        boolean isRefreshing();

        void showError(String message);
    }

    interface Presenter {

        void start();

        void stop();

        void loadPosts();
    }
}
