package com.duckit.home;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.duckit.R;
import com.duckit.databinding.ViewPostItemInfoBinding;
import com.duckit.model.PostInfo;
import com.facebook.common.util.UriUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
            // title
            binding.title.setText(postInfo.getHeadline());
            // image
            String imageUrl = postInfo.getImage();
            if (imageUrl.equalsIgnoreCase("https://en.wikipedia.org/wiki/Duck_test#/media/File:Mallard2.jpg")) {
                Uri uri = new Uri.Builder()
                        .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
                        .path(String.valueOf(R.drawable.mallard_duck))
                        .build();
                binding.image.setImageURI(uri);

            } else {
                binding.image.setImageURI(imageUrl);
            }

            // votes
            AtomicBoolean upvoteStatus = new AtomicBoolean(true);
            binding.upvote.setOnClickListener( v -> {
//                if(upvoteStatus.get()) {
//                    binding.upvote.setImageDrawable(ContextCompat.getDrawable(this.itemView.getContext(), R.drawable.ic_up_vote));
//                    upvoteStatus.set(false);
//                    Timber.e("blue");
//                } else {
//                    binding.upvote.setImageDrawable(ContextCompat.getDrawable(this.itemView.getContext(), R.drawable.ic_up_vote_pressed));
//                    upvoteStatus.set(true);
//                    Timber.e("red");
//                }
            }
            );

            binding.count.setText(String.valueOf(postInfo.getUpvotes()));
        }
    }

    // endregion
}
