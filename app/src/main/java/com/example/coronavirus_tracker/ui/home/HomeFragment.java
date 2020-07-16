package com.example.coronavirus_tracker.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.coronavirus_tracker.R;

import org.json.JSONException;
import org.json.JSONObject;

public class HomeFragment extends Fragment {


private TextView totalConfirmed;
    private TextView totalDeaths;
    private TextView totalRecovered;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        //call view
        totalConfirmed= root.findViewById(R.id.totalConfirmed);
        totalDeaths= root.findViewById(R.id.totalDeaths);
        totalRecovered= root.findViewById(R.id.totalRecovered);

       getData();

        return root;
    }
    public void getData(){
        RequestQueue queue= Volley.newRequestQueue(getActivity());
        String url= "https://corona.lmao.ninja/v2/all";
        StringRequest stringRequest= new StringRequest(Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response.toString());
                    totalConfirmed.setText(jsonObject.getString("cases"));
                    totalDeaths.setText(jsonObject.getString("deaths"));
                    totalRecovered.setText(jsonObject.getString("recovered"));

                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("Error Response",error.toString());
            }

    });

    queue.add(stringRequest);

    }
}