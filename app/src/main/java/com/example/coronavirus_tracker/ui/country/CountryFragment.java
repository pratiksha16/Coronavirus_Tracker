package com.example.coronavirus_tracker.ui.country;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
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
import java.util.List;

public class CountryFragment extends Fragment {

RecyclerView rvcovidCountries;
List<CovidCountry> covidCountries;

CovidCountryAdapter covidCountryAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_country, container, false);

        //set has option menu
        setHasOptionsMenu(true);

        rvcovidCountries= root.findViewById(R.id.covidCountry);
        rvcovidCountries.setLayoutManager(new LinearLayoutManager(getActivity()));
        DividerItemDecoration dividerItemDecoration= new DividerItemDecoration(rvcovidCountries.getContext(),DividerItemDecoration.VERTICAL);
        dividerItemDecoration.setDrawable(ContextCompat.getDrawable(getContext(),R.drawable.linedivider));
        rvcovidCountries.addItemDecoration(dividerItemDecoration);
        //call covid countris
        covidCountries = new ArrayList<>();
        //call volley  method;
        getDataFromServer();

        return root;
    }
private void showRecyclerView(){
        covidCountryAdapter= new CovidCountryAdapter(covidCountries,getActivity());
        rvcovidCountries.setAdapter(covidCountryAdapter);

        ItemClickSupport.addTo(rvcovidCountries).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedCovidCountry(covidCountries.get(position));
            }
        });

}

private void showSelectedCovidCountry(CovidCountry covidCountry){
        Intent covidCountryDetail = new Intent(getActivity(),CovidCountryDetail.class);
        covidCountryDetail.putExtra("EXTRA_COVID",covidCountry );
        startActivity(covidCountryDetail);

}
    private void getDataFromServer() {
        String url = "https://corona.lmao.ninja/v2/countries";

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
                            covidCountries.add(new CovidCountry(data.getString("country"), data.getString("cases"),
                                    data.getString("todayCases"), data.getString("deaths"),
                                    data.getString("todayDeaths"), data.getString("recovered"),
                                    data.getString("critical"),data.getString("active"),
                                    countryInfo.getString("flag")
                                    ));

                        }
//                        tvTotalCountry.setText(jsonArray.length()+" countries");
                        //change action bar ttile
                        getActivity().setTitle(jsonArray.length()+ " countries");;
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

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.search,menu);
        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView  = new SearchView(getActivity());
        searchView.setQueryHint("Search");
        searchView.setMaxWidth(Integer.MAX_VALUE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(covidCountryAdapter!=null){
                    covidCountryAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        searchItem.setActionView(searchView);

        super.onCreateOptionsMenu(menu, inflater);

    }
}