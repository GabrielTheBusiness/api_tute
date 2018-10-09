package com.example.android.api_tute;

public class MovieInfo {

    private String Title;
    private String Year;


    public MovieInfo(String title, String year, String poster) {

        this.Title = title;
        this.Year = year;

    }

    public String getTitle() {

        return Title;
    }

    public String getYear() {

        return Year;
    }


    @Override
    public String toString() {

        return Title + " " + Year;
    }
}
