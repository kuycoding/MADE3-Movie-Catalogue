package com.kuycoding.moviecatalogue3.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kuycoding.moviecatalogue3.R;
import com.kuycoding.moviecatalogue3.model.TVShow;
import com.kuycoding.moviecatalogue3.ui.DetailTVShowActivity;

import java.util.ArrayList;

public class TVShowAdapter extends RecyclerView.Adapter<TVShowAdapter.TVShowViewHolder> {

    private ArrayList<TVShow> tvData = new ArrayList<>();

    public void setTvData(ArrayList<TVShow> items) {
        tvData.clear();
        tvData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TVShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View tvView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cardview_tvshows, parent, false);
        return new TVShowViewHolder(tvView);
    }

    @Override
    public void onBindViewHolder(@NonNull TVShowViewHolder holder, int position) {
        holder.bind(tvData.get(position));
    }

    @Override
    public int getItemCount() {
        return tvData.size();
    }

    public class TVShowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView textViewTitle, textViewUserScore, textViewLang, textViewDate;
        ImageView imageViewPoster;
        public TVShowViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.tv_item_name);
            textViewUserScore = itemView.findViewById(R.id.tv_item_userscore);
            textViewLang = itemView.findViewById(R.id.tv_item_lang);
            textViewDate = itemView.findViewById(R.id.tv_item_date);
            imageViewPoster = itemView.findViewById(R.id.img_item_poster);

            itemView.setOnClickListener(this);
        }

        public void bind(TVShow tvShow) {
            String url_image = "https://image.tmdb.org/t/p/w185" + tvShow.getPoster_path();
            textViewTitle.setText(tvShow.getName());
            textViewUserScore.setText(tvShow.getVote_average());
            textViewLang.setText(tvShow.getOriginal_language());
            textViewDate.setText(tvShow.getFirst_air_date());

            Glide.with(itemView.getContext())
                    .load(url_image)
                    .placeholder(R.color.colorAccent)
                    .dontAnimate()
                    .into(imageViewPoster);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            TVShow tvShow = tvData.get(position);

            tvShow.setName(tvShow.getName());
            tvShow.setOverview(tvShow.getOverview());

            Intent moveWithObjectIntent = new Intent(itemView.getContext(), DetailTVShowActivity.class);
            moveWithObjectIntent.putExtra(DetailTVShowActivity.EXTRA_TV, tvShow);
            itemView.getContext().startActivity(moveWithObjectIntent);
        }
    }
}
