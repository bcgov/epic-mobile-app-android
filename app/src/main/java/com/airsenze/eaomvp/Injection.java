package com.airsenze.eaomvp;

import android.content.Context;

import com.airsenze.eaomvp.api.EpicApi;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Aidan Laing on 2017-03-21.
 *
 */

public class Injection {

    public static EpicApi epicApi;

    static void initialize(Context context) {
        setUpEpicApi();
    }

    private static void setUpEpicApi() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        epicApi = retrofit.create(EpicApi.class);
    }

}
