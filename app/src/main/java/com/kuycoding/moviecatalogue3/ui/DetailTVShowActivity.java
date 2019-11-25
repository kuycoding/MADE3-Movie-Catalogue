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
import com.kuycoding.moviecatalogue3.model.TVShow;

public class DetailTVShowActivity extends AppCompatActivity {

    public static final String EXTRA_TV = "extra_TV";
    private TextView textViewTitle, textViewUserScore, textViewDate, textViewVote, textViewPopular, textViewLang, textViewOverview, textViewCountry;
    private ImageView imgPoster, imgCover;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_tvshow);

        textViewTitle = findViewById(R.id.tv_title);
        textViewUserScore = findViewById(R.id.tv_userscore);
        textViewDate = findViewById(R.id.tv_date);
        textViewPopular = findViewById(R.id.txt_populer);
        textViewVote = findViewById(R.id.txt_vote);
        textViewLang = findViewById(R.id.txt_lang);
        textViewOverview = findViewById(R.id.txt_overview);
        textViewCountry = findViewById(R.id.txt_country);
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
                } catch (Exception e) { //
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        TVShow tv = getIntent().getParcelableExtra(EXTRA_TV);

                        assert tv != null;
                        textViewTitle.setText(tv.getPoster_path());
                        String url_poster = "https://image.tmdb.org/t/p/w185" + tv.getPoster_path();
                        String url_banner = "https://image.tmdb.org/t/p/w780" + tv.getBackdrop_path();

                        textViewPopular.setText(tv.getPopularity());
                        textViewTitle.setText(tv.getName());
                        textViewDate.setText(tv.getFirst_air_date());
                        textViewUserScore.setText(tv.getVote_average());
                        textViewVote.setText(tv.getVote_count());
                        textViewPopular.setText(tv.getPopularity());
                        textViewCountry.setText(tv.getOrigin_country());
                        textViewLang.setText(tv.getOriginal_language());
                        textViewOverview.setText(tv.getOverview());

                        Glide.with(DetailTVShowActivity.this)
                                .load(url_poster)
                                .placeholder(R.color.colorAccent)
                                .dontAnimate()
                                .into(imgPoster);

                        Glide.with(DetailTVShowActivity.this)
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
