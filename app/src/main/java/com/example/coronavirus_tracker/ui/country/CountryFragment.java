package com.example.coronavirus_tracker.ui.country;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coronavirus_tracker.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CountryFragment extends Fragment {

RecyclerView rvcovidCountries;
ArrayList<CovidCountry> covidCountries;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_country, container, false);
        rvcovidCountries= root.findViewById(R.id.covidCountry);
        rvcovidCountries.setLayoutManager(new LinearLayoutManager(getActivity()));
        //call volley  method;
        getDataFromServer();


        return root;
    }
private void showRecyclerView(){
        CovidCountryAdapter covidCountryAdapter= new CovidCountryAdapter(covidCountries,getActivity());
        rvcovidCountries.setAdapter(covidCountryAdapter);

}
    private void getDataFromServer() {
        String url = "https://corona.lmao.ninja/v2/countries";
        covidCountries = new ArrayList<>();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    Log.e("countries", "onResponse" + response);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject data = jsonArray.getJSONObject(i);
                            JSONObject countryInfo= data.getJSONObject("countryInfo");
                            covidCountries.add(new CovidCountry(data.getString("country"), data.getString("cases"),countryInfo.getString("flag")));

                        }
                        showRecyclerView();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("onResponse", error.toString());
            }

    });
        Volley.newRequestQueue(getActivity()).add(stringRequest);



    }
}