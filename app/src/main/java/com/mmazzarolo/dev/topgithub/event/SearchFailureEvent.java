package com.mmazzarolo.dev.topgithub.event;

import retrofit.RetrofitError;

/**
 * Created by Matteo on 31/08/2015.
 */
public class SearchFailureEvent {

    private RetrofitError retrofitError;

    public SearchFailureEvent(RetrofitError retrofitError) {
        this.retrofitError = retrofitError;
    }

    public RetrofitError getRetrofitError() {
        return retrofitError;
    }

    public void setRetrofitError(RetrofitError retrofitError) {
        this.retrofitError = retrofitError;
    }
}
