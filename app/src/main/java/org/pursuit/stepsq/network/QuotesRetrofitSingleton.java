package org.pursuit.stepsq.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class QuotesRetrofitSingleton {

    private static Retrofit QuotesRetro;
    public static Retrofit getInstance() {
        if (QuotesRetro != null) {
            return QuotesRetro;
        }
        QuotesRetro = new Retrofit.Builder()
                .baseUrl("https://quotes.rest")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return QuotesRetro;
    }

}
