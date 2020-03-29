package com.example.videoplayer.screen.interfaces;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonDataFetchAPi {
    @GET("posts")
    Call<List> getPosts();
    @GET("users")
    Call<List> getUsers();
}
