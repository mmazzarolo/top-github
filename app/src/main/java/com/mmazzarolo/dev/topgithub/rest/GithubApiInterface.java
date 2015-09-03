package com.mmazzarolo.dev.topgithub.rest;



import com.mmazzarolo.dev.topgithub.model.SearchResult;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Matteo on 31/08/2015.
 */
public interface GithubApiInterface {

    @GET("/search/repositories")
    void getRepositories(
            @Query(value="q", encodeValue = false) String query,
            @Query("sort") String sort,
            @Query("order") String order,
            @Query("per_page") String perPage,
            @Query("page") String page,
            Callback<SearchResult> response
    );
}
