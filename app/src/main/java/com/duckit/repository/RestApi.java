package com.duckit.repository;

import com.duckit.model.PostResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface RestApi {

    @GET("/posts")
    Flowable<PostResponse> getPosts();
}
