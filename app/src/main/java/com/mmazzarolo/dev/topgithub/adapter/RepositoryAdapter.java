package com.mmazzarolo.dev.topgithub.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mmazzarolo.dev.topgithub.MainApplication;
import com.mmazzarolo.dev.topgithub.R;
import com.mmazzarolo.dev.topgithub.RoundedTransformation;
import com.mmazzarolo.dev.topgithub.Utilities;
import com.mmazzarolo.dev.topgithub.model.Repository;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Matteo on 01/09/2015.
 */
public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.ViewHolder>
        implements View.OnClickListener {

    private List<Repository> mRepositories;
    private Context mContext;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.textview_full_name) TextView textViewFullName;
        @Bind(R.id.textview_description) TextView textViewDescription;
        @Bind(R.id.textview_subtitle) TextView textViewSubtitle;
        @Bind(R.id.imageview_owner) ImageView imageViewOwner;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public RepositoryAdapter(List<Repository> repositories, Context context) {
        this.mRepositories = repositories;
        this.mContext = context;
    }

    @Override
    public RepositoryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.row_repository, viewGroup, false);

        final ViewHolder viewHolder = new ViewHolder(view);

        viewHolder.itemView.setOnClickListener(this);
        viewHolder.itemView.setTag(viewHolder);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Repository repository = mRepositories.get(position);

        String fullName = "<b>" + repository.getOwner().getLogin() + "&#47;" + "</b>" + repository.getName();
        holder.textViewFullName.setText(Html.fromHtml(fullName));

        holder.textViewDescription.setText(repository.getDescription());

        String language = repository.getLanguage();
        int numStars = repository.getStargazersCount();
        String period = MainApplication.getMyDataStore().selectedPeriod().get("always");
        String periodName = Utilities.getPeriodNameFromPeriod(period);
        String subtitle;
        if (language != null) {
            subtitle = mContext.getResources().getString(R.string.row_subtitle, language, numStars, periodName);
        } else {
            subtitle = mContext.getResources().getString(R.string.row_subtitle_no_lang, numStars, periodName);
        }
        holder.textViewSubtitle.setText(subtitle);

        if (repository.getOwner().getAvatarUrl() != null) {
            Picasso.with(mContext).load(repository.getOwner().getAvatarUrl() + "&s=40")
                    .transform(new RoundedTransformation(6, 0))
                    .into(holder.imageViewOwner);
        }
    }

    @Override
    public void onClick(View view) {
        ViewHolder holder = (ViewHolder) view.getTag();
        int position = holder.getPosition();
        String url = mRepositories.get(position).getHtmlUrl();
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        mContext.startActivity(i);
    }

    @Override
    public int getItemCount() {
        return (mRepositories != null) ? mRepositories.size() : 0;
    }
}
