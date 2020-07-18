package com.example.coronavirus_tracker.ui.country;

import android.os.Parcel;
import android.os.Parcelable;

public class CovidCountry implements Parcelable{
    String covidCountry, cases,todayCases,mdeaths,todayDeaths,mrecovered,critical, mActive,flags;


    public CovidCountry(String covidCountry, String cases, String todayCases, String mdeaths, String todayDeaths, String mrecovered, String critical, String mActive, String flags) {
        this.covidCountry = covidCountry;
        this.cases = cases;
        this.todayCases = todayCases;
        this.mdeaths = mdeaths;
        this.todayDeaths = todayDeaths;
        this.mrecovered = mrecovered;
        this.critical = critical;
        this.mActive = mActive;
        this.flags = flags;
    }

    protected CovidCountry(Parcel in) {
        covidCountry = in.readString();
        cases = in.readString();
        todayCases = in.readString();
        mdeaths = in.readString();
        todayDeaths = in.readString();
        mrecovered = in.readString();
        critical = in.readString();
        mActive = in.readString();
        flags = in.readString();
    }

    public static final Parcelable.Creator<CovidCountry> CREATOR = new Parcelable.Creator<CovidCountry>() {
        @Override
        public CovidCountry createFromParcel(Parcel in) {
            return new CovidCountry(in);
        }

        @Override
        public CovidCountry[] newArray(int size) {
            return new CovidCountry[size];
        }
    };

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

    public String getmActive() {
        return mActive;
    }

    public String getFlags() {
        return flags;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(covidCountry);
        dest.writeString(cases);
        dest.writeString(todayCases);
        dest.writeString(mdeaths);
        dest.writeString(todayDeaths);
        dest.writeString(mrecovered);
        dest.writeString(critical);
        dest.writeString(mActive);
        dest.writeString(String.valueOf(flags));
    }
}
