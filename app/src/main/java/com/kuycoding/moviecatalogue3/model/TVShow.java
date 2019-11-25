package com.kuycoding.moviecatalogue3.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class TVShow implements Parcelable {
    private String name;
    private String popularity;
    private String origin_country;
    private String vote_count;
    private String first_air_date;
    private String backdrop_path;
    private String original_language;
    private String vote_average;
    private String overview;
    private String poster_path;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }

    public String getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String origin_country) {
        this.origin_country = origin_country;
    }

    public String getVote_count() {
        return vote_count;
    }

    public void setVote_count(String vote_count) {
        this.vote_count = vote_count;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getVote_average() {
        return vote_average;
    }

    public void setVote_average(String vote_average) {
        this.vote_average = vote_average;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public TVShow(JSONObject object) {
        try{
            String name = object.getString("name");
            String original_country = object.getString("origin_country");
            String vote_count = object.getString("vote_count");
            String first_air_date = object.getString("first_air_date");
            String backdrop_path = object.getString("backdrop_path");
            String original_language = object.getString("original_language");
            String overview = object.getString("overview");
            String poster_path = object.getString("poster_path");
            String popularity = object.getString("popularity");
            String vote_average = object.getString("vote_average");

            this.name = name;
            this.origin_country = original_country;
            this.vote_count = vote_count;
            this.first_air_date = first_air_date;
            this.backdrop_path = backdrop_path;
            this.original_language = original_language;
            this.overview = overview;
            this.poster_path = poster_path;
            this.popularity = popularity;
            this.vote_average = vote_average;

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
        dest.writeString(name);
        dest.writeString(popularity);
        dest.writeString(origin_country);
        dest.writeString(vote_count);
        dest.writeString(first_air_date);
        dest.writeString(backdrop_path);
        dest.writeString(original_language);
        dest.writeString(vote_average);
        dest.writeString(overview);
        dest.writeString(poster_path);
    }

    protected TVShow(Parcel in) {
        name = in.readString();
        popularity = in.readString();
        origin_country = in.readString();
        vote_count = in.readString();
        first_air_date = in.readString();
        backdrop_path = in.readString();
        original_language = in.readString();
        vote_average = in.readString();
        overview = in.readString();
        poster_path = in.readString();
    }

    public static final Creator<TVShow> CREATOR = new Creator<TVShow>() {
        @Override
        public TVShow createFromParcel(Parcel in) {
            return new TVShow(in);
        }

        @Override
        public TVShow[] newArray(int size) {
            return new TVShow[size];
        }
    };
}
