package com.duckit.home;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.duckit.R;
import com.duckit.app.DuckitApp;
import com.duckit.databinding.ActivityPostListBinding;
import com.duckit.model.PostInfo;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

public class PostListActivity extends AppCompatActivity implements PostListContract.View {

    @Inject
    PostListPresenter presenter;
    private ActivityPostListBinding binding;
    private PostListAdapter adapter;

    // region Override
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_list);

        getComponent().inject(this);

        setupToolbar();
        setupRecycleView();
    }

    @Override
    public void onStart() {
        super.onStart();
        fetchPosts();
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.stop();
    }

    // endregion override

    // region EmployeeList Business Contract

    @Override
    public void showSpinner() {
        if (!isRefreshing()) {
            binding.progressBar.Container.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void hideSpinner() {
        if (isLoading()) {
            binding.progressBar.Container.setVisibility(View.GONE);
        }
    }

    @Override
    public void showPosts(List<PostInfo> postInfoList) {
        binding.emptyView.setVisibility(View.GONE);
        binding.list.setVisibility(View.VISIBLE);
        binding.pullToRefresh.setRefreshing(false);
        adapter.display(postInfoList);
    }

    @Override
    public void UpdatePostList(List<PostInfo> postInfoList) {
        binding.emptyView.setVisibility(View.GONE);
        binding.list.setVisibility(View.VISIBLE);
        binding.pullToRefresh.setRefreshing(false);
        adapter.update(postInfoList);
    }

    @Override
    public boolean isRefreshing() {
        return binding.pullToRefresh.isRefreshing();
    }

    @Override
    public void showError(String message) {
        Timber.e("ERR: %s", message);
        binding.emptyView.setVisibility(View.VISIBLE);
        binding.list.setVisibility(View.GONE);
        stopRefreshing();
        hideSpinner();
        if (TextUtils.isEmpty(message)) {
            message = getResources().getString(R.string.nw_error_msg);
        }

        if (message.contains("Unable to resolve host")) {
            message = getResources().getString(R.string.nw_host_error);
        }

        if (message.contains("emptyList")) {
            message = getResources().getString(R.string.nw_empty_list);
        }
        if (message.contains("Missing field in JSON")) {
            message = getResources().getString(R.string.nw_malformed_msg);
        }
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    // endregion

    // region private
    private PostListComponent getComponent() {
        return DaggerPostListComponent.builder()
                .duckitAppComponent(((DuckitApp) getApplication()).getComponent())
                .postListModule(new PostListModule(this))
                .build();
    }

    private void setupToolbar() {
        setSupportActionBar(binding.toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowTitleEnabled(false);
        }
    }

    private void setupRecycleView() {
        binding.list.setVisibility(View.VISIBLE);
        binding.emptyView.setVisibility(View.GONE);
        adapter = new PostListAdapter();
        binding.list.setAdapter(adapter);
        binding.pullToRefresh.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorAccent));
        binding.pullToRefresh.setOnRefreshListener(this::refreshFeed);
    }

    private boolean isLoading() {
        return binding.progressBar.Container.getVisibility() == View.VISIBLE;
    }

    private void stopRefreshing() {
        binding.pullToRefresh.setRefreshing(false);
    }

    private void refreshFeed() {
        binding.pullToRefresh.setRefreshing(true);
        binding.pullToRefresh.postDelayed(this::fetchPosts, 500);
    }

    private void fetchPosts() {
        presenter.start();
    }
}