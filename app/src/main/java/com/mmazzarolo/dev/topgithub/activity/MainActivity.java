package com.mmazzarolo.dev.topgithub.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.mmazzarolo.dev.topgithub.MainApplication;
import com.mmazzarolo.dev.topgithub.MyDataStore;
import com.mmazzarolo.dev.topgithub.R;
import com.mmazzarolo.dev.topgithub.Utilities;
import com.mmazzarolo.dev.topgithub.adapter.RepositoryAdapter;
import com.mmazzarolo.dev.topgithub.event.ChangedLanguagesEvent;
import com.mmazzarolo.dev.topgithub.event.SearchFailureEvent;
import com.mmazzarolo.dev.topgithub.event.SearchSuccesEvent;
import com.mmazzarolo.dev.topgithub.fragment.LanguagesFragment;
import com.mmazzarolo.dev.topgithub.model.LanguageList;
import com.mmazzarolo.dev.topgithub.model.Repository;
import com.mmazzarolo.dev.topgithub.rest.GithubApiClient;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import de.greenrobot.event.EventBus;

public class MainActivity extends BaseActivity {

    private static final String SAVED_LIST =
            "com.mmazzarolo.dev.topgithub.activity.MainActivity.SAVED_LIST";

    private GithubApiClient mGithubApiClient;
    private MyDataStore mMyDataStore;

    private RepositoryAdapter mAdapter;
    private Drawer mDrawer;

    private List<Repository> mRepositories;
    private LanguageList mLanguages;

    private String mSelectedLanguage;
    private String mSelectedPeriod;

    private int mSelectedItemId;

    @Nullable @Bind(R.id.recyclerview) RecyclerView mRecyclerView;
    @Bind(R.id.toolbar) Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Register this Activity for Eventbus events
        EventBus.getDefault().register(this);

        // Get Application's RetroAdapter and MyDataStore
        mRepositories = new ArrayList<>();
        mGithubApiClient = MainApplication.getGihubApiClient();
        mMyDataStore = MainApplication.getMyDataStore();

        getSharedPreferences();

        setSupportActionBar(mToolbar);
        setToolbarTitle();

        showContentView();

        setupDrawer();

        // Handling screen rotation
        if (savedInstanceState != null && savedInstanceState.containsKey(SAVED_LIST)) {
            mRepositories = Parcels.unwrap(savedInstanceState.getParcelable(SAVED_LIST));
            setupRecyclerView();
            if (mRepositories.isEmpty()) {
                startSearch();
            }
        } else {
            setupRecyclerView();
            startSearch();
        }
    }

    private void setToolbarTitle() {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(mSelectedLanguage);
        }
    }

    private void getSharedPreferences() {
        mSelectedLanguage = mMyDataStore.selectedLanguage().get("All");
        mSelectedPeriod = mMyDataStore.selectedPeriod().get("action_this_month");
        mLanguages = mMyDataStore.languages().get();
    }

    private void setupDrawer() {
        // Create the drawer
        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .withTranslucentStatusBar(false)
                .withActionBarDrawerToggle(true)
                .withHeader(R.layout.drawer_header)
                .build();

        // Add the items to it
        addItemsToDrawer();

        // Set a Click Listener on every drawer's item.
        // If the item is clicked start a new search
        mDrawer.setOnDrawerItemClickListener((View view, int i, IDrawerItem iDrawerItem) -> {
            PrimaryDrawerItem drawerItem = (PrimaryDrawerItem) iDrawerItem;
            String language = drawerItem.getName().getText();
            mMyDataStore.selectedLanguage().put(language);
            mSelectedLanguage = language;
            getSupportActionBar().setTitle(mSelectedLanguage);
            startSearch();
            return false;
        });

        // Set the selected drawer's item
        mDrawer.setSelection(mSelectedItemId, false);

        // Set the Click Listener on the drawer header button
        // If it is clicked show the LanguagesFragment
        mDrawer.getHeader().findViewById(R.id.imageview_change_languages).setOnClickListener((View v) -> {
            LanguagesFragment newFragment = new LanguagesFragment();
            newFragment.show(getSupportFragmentManager().beginTransaction(), LanguagesFragment.TAG);
        });
    }

    private void addItemsToDrawer() {
        int itemId = 0;

        // Add the "All" programming language
        PrimaryDrawerItem allDrawerItem = new PrimaryDrawerItem()
                .withName("All")
                .withIdentifier(itemId)
                .withIcon(Utilities.getDrawableFromLanguage(this, "All"))
                .withIconTintingEnabled(true)
                .withSelectedIconColor(Color.WHITE)
                .withIconColorRes(R.color.primary_dark)
                .withTintSelectedIcon(true);
        mDrawer.addItem(allDrawerItem);
        mSelectedItemId = 0;

        // Add the every other programming language
        for (String languageName : mLanguages.getUserLanguages()) {
            itemId++;
            PrimaryDrawerItem drawerItem = new PrimaryDrawerItem()
                    .withName(languageName)
                    .withIdentifier(itemId)
                    .withIcon(Utilities.getDrawableFromLanguage(this, languageName))
                    .withIconTintingEnabled(true)
                    .withSelectedIconColor(Color.WHITE)
                    .withIconColorRes(R.color.primary_dark)
                    .withTintSelectedIcon(true);
            mDrawer.addItem(drawerItem);
            if (mSelectedLanguage.equals(languageName)) {
                mSelectedItemId = itemId;
            }
        }
    }

    private void setupRecyclerView() {
        mAdapter = new RepositoryAdapter(mRepositories, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    private void startSearch() {
        setToolbarTitle();
        String language = mSelectedLanguage;
        String created = Utilities.getCreatedDateFromPeriod(mSelectedPeriod);
        mRepositories.clear();
        mAdapter.notifyDataSetChanged();
        showLoadingView();
        // If there is a connection start a search
        if (Utilities.isConnected(this)) {
            if ("All".equals(language)) {
                language = "";
            }
            mGithubApiClient.startSearch(language, created);
        } else {
            showNoConnectionView();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        int selectedId = getResources().getIdentifier(mSelectedPeriod, "id", getPackageName());
        menu.findItem(selectedId).setIcon(R.drawable.ic_done_grey_900_18dp);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (item.getItemId()) {
            case R.id.action_today:
            case R.id.action_this_week:
            case R.id.action_this_month:
            case R.id.action_this_year:
                mMyDataStore.selectedPeriod().put(getResources().getResourceEntryName(id));
                mSelectedPeriod = getResources().getResourceEntryName(id);
                invalidateOptionsMenu();
                startSearch();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(SAVED_LIST, Parcels.wrap(mRepositories));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);
    }

    public void onEvent(SearchSuccesEvent event) {
        if (event.getRepositories() == null || event.getRepositories().isEmpty()) {
            showEmptyView();
        } else {
            for (Repository repository : event.getRepositories()) {
                mRepositories.add(repository);
                mAdapter.notifyItemInserted(0);
            }
            showContentView();
        }
    }

    public void onEvent(SearchFailureEvent event) {
        if (event.getRetrofitError().getResponse().getStatus() == 422) {
            showEmptyView();
        } else {
            showErrorView(event.getRetrofitError().getMessage());
        }
    }

    public void onEvent(ChangedLanguagesEvent event) {
        getSharedPreferences();
        if (mDrawer.isDrawerOpen()) {
            mDrawer.closeDrawer();
        }
        setupDrawer();
        startSearch();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onTryAgainClick() {
        startSearch();
    }
}
