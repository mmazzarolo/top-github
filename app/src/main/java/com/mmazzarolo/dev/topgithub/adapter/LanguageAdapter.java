package com.mmazzarolo.dev.topgithub.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.mmazzarolo.dev.topgithub.R;
import com.mmazzarolo.dev.topgithub.Utilities;
import com.mmazzarolo.dev.topgithub.fragment.LanguagesFragment;
import com.mmazzarolo.dev.topgithub.model.Language;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Matteo on 01/09/2015.
 */
public class LanguageAdapter extends RecyclerView.Adapter<LanguageAdapter.ViewHolder> {

    private final Context mContext;
    private final LanguagesFragment mLanguagesFragment;
    private final List<Language> mLanguages;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.textview_language) TextView textViewLanguage;
        @Bind(R.id.imageview_language) ImageView imageView;
        @Bind(R.id.switchcompat) SwitchCompat switchCompat;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public LanguageAdapter(List<Language> languages, Context context, LanguagesFragment caller) {
        this.mLanguages = new ArrayList<Language>(languages);
        this.mContext = context;
        this.mLanguagesFragment = caller;
    }

    @Override
    public LanguageAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.row_language, viewGroup, false);

        final ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.switchCompat.setOnCheckedChangeListener(mLanguagesFragment);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Language language = mLanguages.get(position);
        holder.textViewLanguage.setText(language.getName());

        Drawable icon = Utilities.getDrawableFromLanguage(mContext, language.getName());
        holder.imageView.setImageDrawable(icon);

        holder.switchCompat.setTag(language);
        holder.switchCompat.setChecked(language.isSelected());
    }

    @Override
    public int getItemCount() {
        return (mLanguages != null) ? mLanguages.size() : 0;
    }

}
