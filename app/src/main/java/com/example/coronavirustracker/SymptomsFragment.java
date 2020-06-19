package com.example.coronavirustracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SymptomsFragment extends Fragment {

    ArrayList<String> symptomsList = new ArrayList<>();
    ListView listView;
    ArrayAdapter<String> arrayAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View v =  inflater.inflate(R.layout.fragment_symptoms,container,false);
        listView = v.findViewById(R.id.symptomListView);
        symptomsList.add("Aches and pains");
        symptomsList.add("Sore throat");
        symptomsList.add("Diarrhoea");
        symptomsList.add("Conjunctivitis");
        symptomsList.add("Headache");
        symptomsList.add("Loss of taste or smell");
        symptomsList.add("Rashes on skin");
        arrayAdapter = new ArrayAdapter<>(getActivity(),android.R.layout.simple_list_item_1,symptomsList);


        listView.setAdapter(arrayAdapter);
        return v;
        //return inflater.inflate(R.layout.fragment_symptoms,container,false);
    }
}
