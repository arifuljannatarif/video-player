package com.example.videoplayer.screen.common.dependencyinjection;

import com.example.videoplayer.screen.interfaces.JsonDataFetchAPi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * this class is mainly responsible for instanriarinf internal api's and other
 * for adding abstraction to the internal implementation from the root level
 */
public class CompositionRoot {
    Retrofit mRetrofit;

    public JsonDataFetchAPi getJsonApi() {
        return getRetrofit().create(JsonDataFetchAPi.class);
    }

    public Retrofit getRetrofit() {
        if(mRetrofit==null){
            mRetrofit=new Retrofit.Builder()
                    .baseUrl(Const.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return mRetrofit;
    }
}
