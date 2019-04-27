package org.pursuit.stepsq.network;

import org.pursuit.stepsq.model.Quotes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface QuotesService {

    String END_POINT_URL = "/qod";
    @GET(END_POINT_URL)
    Call<Quotes> getQuoteList();
}
