package com.duckit.home;

import com.duckit.model.PostInfo;
import com.duckit.model.PostResponse;
import com.duckit.repository.RestApi;
import com.duckit.scope.PerActivity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.DisposableSubscriber;
import timber.log.Timber;

@PerActivity
class PostListPresenter implements PostListContract.Presenter {

    private PostListContract.View view;
    private CompositeDisposable disposable;
    private RestApi apiService;

    @Inject
    PostListPresenter(PostListContract.View view,
                      RestApi apiService,
                      CompositeDisposable disposable) {
        this.view = view;
        this.apiService = apiService;
        this.disposable = disposable;
    }

    @Override
    public void start() {
        loadPosts();
    }

    @Override
    public void stop() {
        disposable.clear();
    }

    @Override
    public void loadPosts() {
        view.showSpinner();

        Timber.e("Load all posts");
        disposable.add(apiService.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSubscriber<PostResponse>() {
                    @Override
                    public void onNext(PostResponse postResponse) {
                        List<PostInfo> posts = postResponse.getPosts();
                        Timber.e("We got size %d", posts.size());
                         onRetrieveComplete(posts);
                    }

                    @Override
                    public void onError(Throwable throwable) {
                        view.showError(throwable.getMessage());
                    }

                    @Override
                    public void onComplete() {
                    }
                }));
    }

    private void onRetrieveComplete(final List<PostInfo> postInfoList) {
        view.hideSpinner();

        if (postInfoList == null || postInfoList.isEmpty()) {
            view.showError("emptyList");
            return;
        }

        if (view.isRefreshing()) {
            view.UpdatePostList(postInfoList);
        } else {
            view.showPosts(postInfoList);
        }
    }

}
