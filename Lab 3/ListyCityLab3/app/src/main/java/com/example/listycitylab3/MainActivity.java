package com.example.listcitylab3;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements com.example.listcitylab3.AddCityFragment.AddCityDialogListener {

    private ArrayList<com.example.listcitylab3.City> cityList;
    private com.example.listcitylab3.CityArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView cityListView = findViewById(R.id.city_list);
        Button addCityButton = findViewById(R.id.add_city_button);

        cityList = new ArrayList<>();
        cityList.add(new com.example.listcitylab3.City("Edmonton", "AB"));
        cityList.add(new com.example.listcitylab3.City("Vancouver", "BC"));
        cityList.add(new com.example.listcitylab3.City("Toronto", "ON"));

        adapter = new com.example.listcitylab3.CityArrayAdapter(this, cityList);
        cityListView.setAdapter(adapter);

        addCityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(
