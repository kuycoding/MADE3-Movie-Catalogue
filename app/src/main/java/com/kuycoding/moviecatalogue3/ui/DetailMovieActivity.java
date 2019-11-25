package com.kuycoding.moviecatalogue3.ui;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.kuycoding.moviecatalogue3.R;
import com.kuycoding.moviecatalogue3.model.Movies;

public class DetailMovieActivity extends AppCompatActivity {
    public static final String EXTRA_MOVIE = "extra_movie";
    private TextView textViewTitle, textViewUserScore, textViewDate, textViewVote, textViewPopular, textViewLang, textViewOverview;
    private ImageView imgPoster, imgCover;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        textViewTitle = findViewById(R.id.tv_title);
        textViewUserScore = findViewById(R.id.tv_userscore);
        textViewDate = findViewById(R.id.tv_date);
        textViewPopular = findViewById(R.id.txt_populer);
        textViewVote = findViewById(R.id.txt_vote);
        textViewLang = findViewById(R.id.txt_lang);
        textViewOverview = findViewById(R.id.txt_overview);
        imgCover = findViewById(R.id.img_cover);
        imgPoster = findViewById(R.id.img_poster);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        final Handler handler = new Handler();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (Exception e) {
                    //
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Movies movies = getIntent().getParcelableExtra(EXTRA_MOVIE);

                        assert movies != null;
                        textViewTitle.setText(movies.getPhoto());
                        String url_poster = "https://image.tmdb.org/t/p/w185" + movies.getPhoto();
                        String url_banner = "https://image.tmdb.org/t/p/w780" + movies.getBanner();

                        String popular = Double.toString(movies.getPopularity());
                        textViewTitle.setText(movies.getTitle());
                        textViewDate.setText(movies.getRelease_date());
                        textViewUserScore.setText(movies.getUser_score());
                        textViewVote.setText(movies.getVote_count());
                        textViewPopular.setText(popular);
                        textViewLang.setText(movies.getOriginal_language());
                        textViewOverview.setText(movies.getOverview());

                        Glide.with(DetailMovieActivity.this)
                                .load(url_poster)
                                .placeholder(R.color.colorAccent)
                                .dontAnimate()
                                .into(imgPoster);

                        Glide.with(DetailMovieActivity.this)
                                .load(url_banner)
                                .placeholder(R.color.colorAccent)
                                .dontAnimate()
                                .into(imgCover);
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
            }
        }).start();
    }
}
