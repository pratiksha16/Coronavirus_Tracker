package com.example.coronavirus_tracker.ui.country;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.coronavirus_tracker.R;

public class CovidCountryDetail extends AppCompatActivity {
    TextView tvDetailCountryName, tvDetailTotalCases, tvDetailTodayCases,
            tvDetailTotalDeaths, tvDetailTodayDeaths, tvDetailTotalRecovered, tvDetailTotalActive, tvDetailTotalCritical;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_country_detail);
        //call view
        tvDetailCountryName = findViewById(R.id.tvDetailCountryName);
        tvDetailTodayCases = findViewById(R.id.tvDetailTodayCases);
        tvDetailTodayDeaths = findViewById(R.id.tvDetailTodayDeaths);
        tvDetailTotalCases = findViewById(R.id.tvDetailTotalCases);
        tvDetailTotalDeaths = findViewById(R.id.tvDetailTotalDeaths);
        tvDetailTotalRecovered = findViewById(R.id.tvDetailTotalRecovered);
        tvDetailTotalActive = findViewById(R.id.tvDetailTotalActive);
        tvDetailTotalCritical = findViewById(R.id.tvDetailTotalCritical);

        //call our covid country
        CovidCountry covidCountry = getIntent().getParcelableExtra("EXTRA_COVID");
        //set text view
        tvDetailCountryName.setText(covidCountry.getCovidCountry());
        tvDetailTodayCases.setText(covidCountry.getTodayCases());
        tvDetailTodayDeaths.setText(covidCountry.getTodayDeaths());
        tvDetailTotalCases.setText(covidCountry.getCases());
        tvDetailTotalDeaths.setText(covidCountry.getMdeaths());
        tvDetailTotalRecovered.setText(covidCountry.getMrecovered());
        tvDetailTotalActive.setText(covidCountry.getmActive());
        tvDetailTotalCritical.setText(covidCountry.getCritical());



    }
}