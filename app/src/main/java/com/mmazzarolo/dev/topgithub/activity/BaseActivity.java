package com.mmazzarolo.dev.topgithub.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.malinskiy.materialicons.IconDrawable;
import com.malinskiy.materialicons.Iconify;
import com.mmazzarolo.dev.topgithub.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Matteo on 03/09/2015.
 *
 * This Activity handles the content/loading/empty/error/no_connection views.
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Bind(R.id.recyclerview) RecyclerView mViewContent;

    @Bind(R.id.main_loading) View mViewLoading;

    @Bind(R.id.main_empty) View mViewEmpty;
    @Bind(R.id.imageview_empty) ImageView mImageViewEmpty;

    @Bind(R.id.main_no_connection) View mViewNoConnection;
    @Bind(R.id.imageview_no_connection) ImageView mImageViewNoConnection;
    @Bind(R.id.button_no_connection) Button mButtonNoConnection;


    @Bind(R.id.main_error) View mViewError;
    @Bind(R.id.imageview_error) ImageView mImageViewError;
    @Bind(R.id.textview_content_error) TextView mTextViewContentError;
    @Bind(R.id.button_error) Button mButtonError;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        ButterKnife.bind(this);

        setupViews();
    }

    private void setupViews() {
        // Main Content View
        mViewContent.setVisibility(View.GONE);

        // Loading View
        mViewLoading.setVisibility(View.GONE);

        // Empty View
        mViewEmpty.setVisibility(View.GONE);
        mImageViewEmpty.setImageDrawable(new IconDrawable(this,
                Iconify.IconValue.zmdi_search_in_file).colorRes(android.R.color.white));

        // No Connection View
        mViewNoConnection.setVisibility(View.GONE);
        mImageViewNoConnection.setImageDrawable(new IconDrawable(this,
                Iconify.IconValue.zmdi_wifi_off).colorRes(android.R.color.white));
        mButtonNoConnection.setOnClickListener((View v) -> onTryAgainClick());

        // Error View
        mViewError.setVisibility(View.GONE);
        mImageViewError.setImageDrawable(new IconDrawable(this,
                Iconify.IconValue.zmdi_alert_circle).colorRes(android.R.color.white));
        mButtonNoConnection.setOnClickListener((View v) -> onTryAgainClick());
    }

    public void showContentView() {
        mViewContent.setVisibility(View.VISIBLE);
        mViewLoading.setVisibility(View.GONE);
        mViewEmpty.setVisibility(View.GONE);
        mViewNoConnection.setVisibility(View.GONE);
        mViewError.setVisibility(View.GONE);
    }

    public void showLoadingView() {
        mViewContent.setVisibility(View.GONE);
        mViewLoading.setVisibility(View.VISIBLE);
        mViewEmpty.setVisibility(View.GONE);
        mViewNoConnection.setVisibility(View.GONE);
        mViewError.setVisibility(View.GONE);
    }

    public void showEmptyView() {
        mViewContent.setVisibility(View.GONE);
        mViewLoading.setVisibility(View.GONE);
        mViewEmpty.setVisibility(View.VISIBLE);
        mViewNoConnection.setVisibility(View.GONE);
        mViewError.setVisibility(View.GONE);
    }

    public void showNoConnectionView() {
        mViewContent.setVisibility(View.GONE);
        mViewLoading.setVisibility(View.GONE);
        mViewEmpty.setVisibility(View.GONE);
        mViewNoConnection.setVisibility(View.VISIBLE);
        mViewError.setVisibility(View.GONE);
    }

    public void showErrorView(String error) {
        mViewContent.setVisibility(View.GONE);
        mViewLoading.setVisibility(View.GONE);
        mViewEmpty.setVisibility(View.GONE);
        mViewNoConnection.setVisibility(View.GONE);
        mViewError.setVisibility(View.VISIBLE);

        mTextViewContentError.setText(getResources().getString(R.string.error_content, error));
    }

    /**
     * Abstract methods that MUST be implemented by the extending class
     */
    protected abstract int getLayoutResourceId();

    protected abstract void onTryAgainClick();

}
