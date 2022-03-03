package com.duckit.home;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.duckit.databinding.ViewPostItemInfoBinding;
import com.duckit.model.PostInfo;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

public class PostListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<PostInfo> postInfoList = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final ViewPostItemInfoBinding itemBinding =
                ViewPostItemInfoBinding.inflate(layoutInflater, parent, false);
        return new ItemViewHolder(itemBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        viewHolder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return postInfoList.size();
    }

    // region Helper Methods

    void update(List<PostInfo> postInfoList) {
        this.postInfoList.clear();
        display(postInfoList);
    }

    void display(List<PostInfo> postInfoList) {
        this.postInfoList.addAll(postInfoList);
        notifyDataSetChanged();
    }


    // endregion

    // region item View holder

    class ItemViewHolder extends RecyclerView.ViewHolder {

        private ViewPostItemInfoBinding binding;

        ItemViewHolder(@NonNull ViewPostItemInfoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void onBind(int position) {
            PostInfo postInfo = postInfoList.get(position);
            String imageUrl = postInfo.getImage();
            binding.image.setImageURI(imageUrl);
            binding.title.setText(postInfo.getHeadline());
        }
    }

    // endregion
}
