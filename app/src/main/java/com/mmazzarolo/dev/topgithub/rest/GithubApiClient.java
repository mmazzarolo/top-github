package com.mmazzarolo.dev.topgithub.rest;

import com.mmazzarolo.dev.topgithub.event.SearchFailureEvent;
import com.mmazzarolo.dev.topgithub.event.SearchSuccesEvent;
import com.mmazzarolo.dev.topgithub.model.SearchResult;

import java.net.URLEncoder;

import de.greenrobot.event.EventBus;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Matteo on 31/08/2015.
 */
public class GithubApiClient {

    private static final String BASE_URL = "https://api.github.com/";
    private GithubApiInterface mGithubApiInterface;

    // Creates the Retrofit RestAdapter
    public GithubApiClient() {
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setEndpoint(BASE_URL)
                .build();

        mGithubApiInterface = restAdapter.create(GithubApiInterface.class);
    }

    public GithubApiInterface getGithubApiInterface() {
        return mGithubApiInterface;
    }

    public void startSearch(String language, String created) {
        // Callback for the search results
        Callback callback = new Callback<SearchResult>() {
            @Override
            public void success(SearchResult searchResult, Response response) {
                EventBus.getDefault().post(new SearchSuccesEvent(searchResult));
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                EventBus.getDefault().post(new SearchFailureEvent(retrofitError));
            }
        };

        // Generate the "q" parameter for the API call: be carefull on encoding the value but not
        // the "+" symbol that links the parameters
        String query = "created:>" + created;
        if (!"".equals(language)) {
            query = query.concat("+language:" + URLEncoder.encode(language));
        }

        // Start the GitHubApi call
        mGithubApiInterface.getRepositories(
                query,
                "stars",
                "desc",
                "50",
                "1",
                callback);
    }

}
