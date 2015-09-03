package com.mmazzarolo.dev.topgithub.fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;


import com.mmazzarolo.dev.topgithub.MainApplication;
import com.mmazzarolo.dev.topgithub.MyDataStore;
import com.mmazzarolo.dev.topgithub.R;
import com.mmazzarolo.dev.topgithub.adapter.LanguageAdapter;
import com.mmazzarolo.dev.topgithub.event.ChangedLanguagesEvent;
import com.mmazzarolo.dev.topgithub.model.Language;
import com.mmazzarolo.dev.topgithub.model.LanguageList;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.BindString;
import butterknife.ButterKnife;
import de.greenrobot.event.EventBus;

/**
 * Created by Matteo on 03/09/2015.
 */
public class LanguagesFragment extends DialogFragment implements CompoundButton.OnCheckedChangeListener {

    public static final String TAG = "com.mmazzarolo.dev.githubnow.LanguageDialogFragment";

    private Context mContext;

    private MyDataStore mMyDataStore;

    private LanguageAdapter mAdapter;
    private List<Language> mLanguages = new ArrayList<Language>();

    @Bind(R.id.recyclerview) RecyclerView mRecyclerView;

    @BindString(R.string.cancel) String mStrCancel;
    @BindString(R.string.select_languages) String mStrSelectLanguages;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mContext = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRetainInstance(true);
        super.onCreate(savedInstanceState);

        mMyDataStore = MainApplication.getMyDataStore();
        mLanguages.addAll(mMyDataStore.languages().get().getLanguages());
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());
        dialog.setTitle(mStrSelectLanguages);
        dialog.setPositiveButton(
                android.R.string.ok,
                (DialogInterface dialogInt, int whichButton) -> {
                    mMyDataStore.languages().put(new LanguageList(mLanguages));
                    mMyDataStore.selectedLanguage().put("All");
                    EventBus.getDefault().post(new ChangedLanguagesEvent());
                    dialogInt.dismiss();
                }
        );
        dialog.setNegativeButton(
                "Cancel",
                (DialogInterface dialogInt, int whichButton) -> dialogInt.dismiss()
        );

        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_languages, null);
        dialog.setView(view);
        ButterKnife.bind(this, view);
        setupRecyclerView();
        return dialog.create();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Setting the width of the dialog programmatically
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    private void setupRecyclerView() {
        mAdapter = new LanguageAdapter(mLanguages, mContext, this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Language language = (Language) buttonView.getTag();
        int pos = mLanguages.indexOf(language);
        if (isChecked != language.isSelected()) {
            mLanguages.get(pos).toggleSelection();
        }
    }

    @Override
    public void onDestroyView() {
        if (getDialog() != null && getRetainInstance())
            getDialog().setDismissMessage(null);
        super.onDestroyView();
    }

}