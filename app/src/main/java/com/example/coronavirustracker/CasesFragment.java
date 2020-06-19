package com.example.coronavirustracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class CasesFragment extends Fragment {

    TextView infectedCaseCount;
    TextView deceasedCaseCount;
    TextView recoveredCaseCount;
    TextView totalConfirmed;
    TextView newConfirmed;
    TextView totalDeceased;
    TextView newDeceased;
    TextView totalRecovered;
    TextView newRecovered;
    RequestQueue requestQueue;
    Spinner spinner;
    Button parseBtn;
    String inf,dec,rec;
    Button parseCountry;
    String CountryName;
    ArrayList<String> countryName;
    int count ;
    String name;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        count = 0;

        View v =  inflater.inflate(R.layout.fragment_cases,container,false);
        parseBtn = v.findViewById(R.id.parseBtn);
        spinner = v.findViewById(R.id.spinnerCountry);
        infectedCaseCount = v.findViewById(R.id.infectedCaseCount);
        deceasedCaseCount = v.findViewById(R.id.deceasedCaseCount);
        recoveredCaseCount = v.findViewById(R.id.recoveredCaseCount);
        totalConfirmed = v.findViewById(R.id.totalConfirmed);
        newConfirmed = v.findViewById(R.id.newConfirmed);
        totalDeceased = v.findViewById(R.id.totalDeaths);
        newDeceased = v.findViewById(R.id.newDeaths);
        totalRecovered = v.findViewById(R.id.totalRecovered);
        newRecovered = v.findViewById(R.id.newRecovered);
        parseCountry = v.findViewById(R.id.parseCountry);
        requestQueue = Volley.newRequestQueue(getContext());
        countryName = new ArrayList<>();
        countryName.add("Select Country");

        retrievePrefData();






        //Spinner Data

        String url="https://api.covid19api.com/summary";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray countries = response.getJSONArray("Countries");

                    for(int i=0 ; i  <= countries.length();i++){

                        JSONObject country = countries.getJSONObject(i);

                        countryName.add(country.getString("Country"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item,countryName);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                if(position == 0){
//                    Toast.makeText(getActivity(),"Select Country...",Toast.LENGTH_LONG).show();
//                }
//                else {
                    name = spinner.getItemAtPosition(position).toString();
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



        //    button functions


        parseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();

            }
        });

        parseCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                jsonParseCountry(name);
            }
        });
       return v;





       //External Functions



    }
    public void jsonParse() {
        String url="https://api.covid19api.com/summary";
        Log.i("Json ","Started");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {


                    JSONObject global = response.getJSONObject("Global");


                        String TotalConfirmed = global.getString("TotalConfirmed");
                        String TotalDeaths = global.getString("TotalDeaths");
                        String TotalRecovered = global.getString("TotalRecovered");

                        if(infectedCaseCount.getText().equals(TotalConfirmed))
                        {
                            Toast.makeText(getContext(),"No new updates.",Toast.LENGTH_SHORT).show();
                        }
                        else {


                            infectedCaseCount.setText(TotalConfirmed);
                            deceasedCaseCount.setText(TotalDeaths);
                            recoveredCaseCount.setText(TotalRecovered);

                            inf = TotalConfirmed;
                            dec = TotalDeaths;
                            rec = TotalRecovered;

                            savePrefData();
                        }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }

    private void jsonParseCountry(String value){
       String url="https://api.covid19api.com/summary";
        if(name == "Select Country") {
            Toast.makeText(getActivity(), "Select Country", Toast.LENGTH_SHORT).show();
        }
        else{

            if (count == 0) {
                Toast.makeText(getContext(), "Fetching Content", Toast.LENGTH_SHORT).show();
                count++;
            }
            JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    try {
                        JSONArray countries = response.getJSONArray("Countries");

                        for (int i = 0; i <= countries.length(); i++) {

                            JSONObject country = countries.getJSONObject(i);
                            if (country.getString("Country").equals(name)) {

                                totalConfirmed.setText(country.getString("TotalConfirmed"));
                                newConfirmed.setText(country.getString("NewConfirmed"));
                                totalDeceased.setText(country.getString("TotalDeaths"));
                                newDeceased.setText(country.getString("NewDeaths"));
                                totalRecovered.setText(country.getString("TotalRecovered"));
                                newRecovered.setText(country.getString("NewRecovered"));
                            }

                        }
                    } catch (JSONException e) {
                        e.printStackTrace();

                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                }
            });
            requestQueue.add(request);
        }



    //SHARED PREFS

    }
    private void savePrefData(){
        SharedPreferences prefs = getActivity().getSharedPreferences("com.example.coronavirustracker;", Context.MODE_PRIVATE);
        prefs.edit().putString("infected",inf).apply();
        prefs.edit().putString("deceased",dec).apply();
        prefs.edit().putString("recovered",rec).apply();
    }
    private void retrievePrefData(){
        SharedPreferences prefs = getActivity().getSharedPreferences("com.example.coronavirustracker;", Context.MODE_PRIVATE);
        infectedCaseCount.setText(prefs.getString("infected","0"));
        deceasedCaseCount.setText(prefs.getString("deceased","0"));
        recoveredCaseCount.setText(prefs.getString("recovered","0"));

    }

}
