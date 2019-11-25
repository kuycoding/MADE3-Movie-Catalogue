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
import com.kuycoding.moviecatalogue3.model.Movies;
import com.kuycoding.moviecatalogue3.ui.DetailMovieActivity;

import java.util.ArrayList;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {

    private ArrayList<Movies> mData = new ArrayList<>();

    public void setData(ArrayList<Movies> items) {
        mData.clear();
        mData.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int position) {
        View mView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_movies, viewGroup, false);
        return new MovieViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int position) {
        movieViewHolder.bind(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView imgPhoto;
        TextView textViewTitle, textViewDateReleased, textViewLang,
                textViewUserScore;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.tv_item_name);
            textViewUserScore = itemView.findViewById(R.id.tv_item_userscore);
            textViewDateReleased = itemView.findViewById(R.id.tv_item_date);
            textViewLang = itemView.findViewById(R.id.tv_item_lang);

            imgPhoto = itemView.findViewById(R.id.img_item_poster);


            itemView.setOnClickListener(this);
        }

        void bind(Movies movies) {
            String url_image = "https://image.tmdb.org/t/p/w185" + movies.getPhoto();
            textViewTitle.setText(movies.getTitle());
            textViewUserScore.setText(movies.getUser_score());
            textViewDateReleased.setText(movies.getRelease_date());
            textViewLang.setText(movies.getOriginal_language());

            Glide.with(itemView.getContext())
                    .load(url_image)
                    .placeholder(R.color.colorAccent)
                    .dontAnimate()
                    .into(imgPhoto);

        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            Movies movie = mData.get(position);

            movie.setTitle(movie.getTitle());
            movie.setOverview(movie.getOverview());

            Intent moveWithObjectIntent = new Intent(itemView.getContext(), DetailMovieActivity.class);
            moveWithObjectIntent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie);
            itemView.getContext().startActivity(moveWithObjectIntent);
        }
    }


}

