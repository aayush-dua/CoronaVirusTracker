package com.example.coronavirustracker;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView infectedCaseCount;
    TextView deceasedCaseCount;
    TextView recoveredCaseCount;
    RequestQueue requestQueue;
    Button parseBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);


        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CasesFragment()).commit();


        infectedCaseCount = findViewById(R.id.infectedCaseCount);
        deceasedCaseCount = findViewById(R.id.deceasedCaseCount);
        recoveredCaseCount = findViewById(R.id.recoveredCaseCount);
        parseBtn =  findViewById(R.id.parseBtn);
        requestQueue = Volley.newRequestQueue(this);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                   Fragment selectedFragment = null;

                   switch(menuItem.getItemId()){
                       case R.id.updates:
                           selectedFragment = new CasesFragment();
                           break;
                       case R.id.symptoms:
                           selectedFragment = new SymptomsFragment();
                           break;
                       case R.id.safety:
                           selectedFragment = new SafetyFragment();
                           break;
                       case R.id.india:
                           selectedFragment = new FragmentIndia();
                           break;
                   }
                   getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,selectedFragment).commit();
                   return true;
                }
            };


}
