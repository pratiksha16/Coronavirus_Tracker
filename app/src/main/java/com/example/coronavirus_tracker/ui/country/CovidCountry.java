package com.example.coronavirus_tracker.ui.country;

public class CovidCountry {
    String covidCountry, cases,todayCases,mdeaths,todayDeaths,mrecovered,critical,flags;

    public CovidCountry(String covidCountry, String cases,String flags) {
        this.covidCountry = covidCountry;
        this.cases = cases;
        this.todayCases = todayCases;
        this.mdeaths = mdeaths;
        this.todayDeaths = todayDeaths;
        this.mrecovered = mrecovered;
        this.critical = critical;
        this.flags = flags;
    }

    public String getCovidCountry() {
        return covidCountry;
    }

    public String getCases() {
        return cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public String getMdeaths() {
        return mdeaths;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public String getMrecovered() {
        return mrecovered;
    }

    public String getCritical() {
        return critical;
    }

    public String getFlags() {
        return flags;
    }



}
