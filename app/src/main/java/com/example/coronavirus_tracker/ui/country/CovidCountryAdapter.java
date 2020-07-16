package com.example.coronavirus_tracker.ui.country;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.coronavirus_tracker.R;

import java.util.ArrayList;

public class CovidCountryAdapter extends RecyclerView.Adapter<CovidCountryAdapter.ViewHolder> {

ArrayList<CovidCountry>covidCountries;
private Context context;
public CovidCountryAdapter(ArrayList<CovidCountry>covidCountries, Context context){
    this.covidCountries= covidCountries;
    this.context= context;
}
    @NonNull
    @Override
    public CovidCountryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CovidCountryAdapter.ViewHolder holder, int position) {
    CovidCountry covidCountry= covidCountries.get(position);
    holder.totalCases.setText(covidCountry.getCases());
    holder.countryName.setText(covidCountry.getCovidCountry());
    //Glide for image View
        Glide.with(context)
                .load(covidCountry.getFlags())
                .apply(new RequestOptions()
                .override(240,160))
                .into(holder.imgFlag);



    }

    @Override
    public int getItemCount() {

    return covidCountries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
    TextView totalCases, countryName;
    ImageView imgFlag;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            totalCases=itemView.findViewById(R.id.totalCases);

            countryName= itemView.findViewById(R.id.countryName);
            imgFlag= itemView.findViewById(R.id.countryFlag);



        }
    }
}

