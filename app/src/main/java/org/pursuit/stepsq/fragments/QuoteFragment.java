package org.pursuit.stepsq.fragments;


import android.os.Bundle;
import android.print.PrinterId;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.pursuit.stepsq.R;
import org.pursuit.stepsq.model.Quotes;
import org.pursuit.stepsq.network.QuotesRetrofitSingleton;
import org.pursuit.stepsq.network.QuotesService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class QuoteFragment extends Fragment {
    private Retrofit retro;
    private View rootView;
    private TextView StepsQuote;
    private final String TAG = "QUOTEDISPLAYS";
    private final String TAGFAIL = "QUOTEFAILS";

    public QuoteFragment() {

    }
    public static QuoteFragment getInstance(){
        return new QuoteFragment();
    }

    @Override
    public void onStart() {
        super.onStart();
        retro = QuotesRetrofitSingleton.getInstance();
        QuotesService quotesService = retro.create(QuotesService.class);
        Call<Quotes> quotesCall = quotesService.getQuoteList();
        quotesCall.enqueue(new Callback<Quotes>() {
            @Override
            public void onResponse(Call<Quotes> call, Response<Quotes> response) {
                Log.d(TAG, "onResponse: " + response.body().getQuote());
                String quote = response.body().getQuote()+" - "+ response.body().getAuthor();
                StepsQuote.setText(quote);

            }

            @Override
            public void onFailure(Call<Quotes> call, Throwable t) {
                Log.d(TAGFAIL, "onResponse: " + t.getMessage());
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView =  inflater.inflate(R.layout.fragment_quote, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        StepsQuote = rootView.findViewById(R.id.tv_steps_quote);

    }
}
