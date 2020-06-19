package com.example.coronavirustracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FragmentIndia extends Fragment {

    private RecyclerView mRecyclerView;
    private CardAdapter cardAdapter;
    private ArrayList<CardItem> arrayList;
    private RequestQueue mRequestQueue;
    private ProgressBar progressBar;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_india, container, false);
        mRecyclerView = v.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        progressBar = v.findViewById(R.id.progressBar);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        arrayList = new ArrayList<>();
        mRequestQueue = Volley.newRequestQueue(getContext());
        progressBar.setVisibility(View.VISIBLE);

        parseJSON();

        return v;
    }

    private void parseJSON() {

        String url = "https://api.covidindiatracker.com/state_data.json";

        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject hit = response.getJSONObject(i);
                        String state = hit.getString("state");
                        String active = hit.getString("active");
                        String confirmed = hit.getString("confirmed");
                        String deceased = hit.getString("deaths");
                        String recovered = hit.getString("recovered");
                        arrayList.add(new CardItem(state, active, confirmed, deceased, recovered));
                        progressBar.setVisibility(View.GONE);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                cardAdapter = new CardAdapter(getContext(), arrayList);
                mRecyclerView.setAdapter(cardAdapter);
            }

        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mRequestQueue.add(request);
    }
}




//
//        mRequestQueue.add(request);

//        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,url,null,
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        try {
//                            JSONArray jsonArray = response.getJSONArray("");
//
//                            for(int i = 0; i < response.length(); i ++) {
//
//                                JSONObject hit = jsonArray.getJSONObject(i);
//                                String state = hit.getString("state");
//                                String active = hit.getString("active");
//                                String confirmed = hit.getString("confirmed");
//                                String deceased = hit.getString("deaths");
//                                String recovered = hit.getString("recovered");
//
//
//                                arrayList.add(new CardItem(state,active,confirmed,deceased,recovered));
//
//                            }
//
//                            cardAdapter = new CardAdapter(getContext(),arrayList);
//                            mRecyclerView.setAdapter(cardAdapter);
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                    error.printStackTrace();
//            }
//        });



