package com.mmazzarolo.dev.topgithub;

import android.app.Application;
import android.preference.PreferenceManager;

import com.lacronicus.easydatastorelib.DatastoreBuilder;
import com.mmazzarolo.dev.topgithub.model.Language;
import com.mmazzarolo.dev.topgithub.model.LanguageList;
import com.mmazzarolo.dev.topgithub.rest.GithubApiClient;

import net.danlew.android.joda.JodaTimeAndroid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Matteo on 01/09/2015.
 */
public class MainApplication extends Application {

    private static GithubApiClient mGithubApiClient;
    private static MyDataStore mMyDataStore;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initalize JodaTime
        JodaTimeAndroid.init(this);

        // Get the RetrofitRestadapter reference
        mGithubApiClient = new GithubApiClient();

        // Initialize a MyDataStore to handle SharedPreferences
        mMyDataStore = new DatastoreBuilder(PreferenceManager.getDefaultSharedPreferences(this))
                .create(MyDataStore.class);

        // Check if it is the first time the application runs
        if (mMyDataStore.isFirstRun().get(true)) {
            onFirstRun();
        }
    }

    private void onFirstRun() {
        // Generate user languages SharedPreference from the array of default languages
        List<String> defaultLanguages =
                Arrays.asList(getResources().getStringArray(R.array.default_languages));
        List<String> availableLanguages =
                Arrays.asList(getResources().getStringArray(R.array.available_languages));
        ArrayList<Language> userLanguages = new ArrayList<Language>();

        for (String lang : availableLanguages) {
            Language language = new Language(lang, defaultLanguages.contains(lang));
            userLanguages.add(language);
        }

        LanguageList languageList = new LanguageList(userLanguages);
        mMyDataStore.languages().put(languageList);
        mMyDataStore.isFirstRun().put(false);
    }

    public static GithubApiClient getGihubApiClient() {
        return mGithubApiClient;
    }

    public static MyDataStore getMyDataStore() {
        return mMyDataStore;
    }
}
