package com.kuycoding.moviecatalogue3.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Movies implements Parcelable {
    private String vote_count;
    private String user_score;
    private String genres;
    private String title;
    private Double popularity;
    private String original_language;
    private String overview;
    private String release_date;
    private String photo;
    private String banner;

    public String getGenres() {
        return genres;
    }

    public void setGenre(String genres) {
        this.genres = genres;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getUser_score() {
        return user_score;
    }

    public void setUser_score(String vote_average) {
        this.user_score = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Movies(JSONObject object) {
        try {
            String vote_count = object.getString("vote_count");
            String user_score = object.getString("vote_average");
            String title = object.getString("title");
            Double popularity = object.getDouble("popularity");
            String original_language = object.getString("original_language");
            String overview = object.getString("overview");
            String release_date = object.getString("release_date");
            String poster_path = object.getString("poster_path");
            String backdrop_path = object.getString("backdrop_path");
            String genres = object.getString("genre_ids");

            this.vote_count = vote_count;
            this.user_score = user_score;
            this.title = title;
            this.popularity = popularity;
            this.original_language = original_language;
            this.overview = overview;
            this.release_date = release_date;
            this.photo = poster_path;
            this.banner = backdrop_path;
            this.genres = genres;

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.vote_count);
        dest.writeString(this.user_score);
        dest.writeString(this.title);
        dest.writeValue(this.popularity);
        dest.writeString(this.original_language);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
        dest.writeString(this.photo);
        dest.writeString(this.banner);
        dest.writeString(this.genres);
    }

    protected Movies(Parcel in) {
        this.vote_count = in.readString();
        this.user_score = in.readString();
        this.title = in.readString();
        this.popularity = (Double) in.readValue(Double.class.getClassLoader());
        this.original_language = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
        this.photo = in.readString();
        this.banner = in.readString();
        this.genres = in.readString();
    }

    public static final Creator<Movies> CREATOR = new Creator<Movies>() {
        @Override
        public Movies createFromParcel(Parcel source) {
            return new Movies(source);
        }

        @Override
        public Movies[] newArray(int size) {
            return new Movies[size];
        }
    };
}
