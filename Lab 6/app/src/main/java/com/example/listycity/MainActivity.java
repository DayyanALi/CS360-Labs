package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    ListView cityList;
    EditText inputCity;
    Button addBtn, deleteBtn, confirmBtn;

    ArrayList<String> cities;
    ArrayAdapter<String> adapter;

    int selected = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityList = findViewById(R.id.city_list);
        inputCity = findViewById(R.id.input_city);
        addBtn = findViewById(R.id.add_btn);
        deleteBtn = findViewById(R.id.delete_btn);
        confirmBtn = findViewById(R.id.confirm_btn);

        cities = new ArrayList<>(Arrays.asList("Edmonton", "Vancouver", "Moscow", "Sydney", "Berlin", "Vienna",
                "Tokyo", "Beijing", "Osaka", "New Delhi"));

        adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_activated_1,
                cities
        );

        cityList.setAdapter(adapter);

        cityList.setOnItemClickListener((p, v, i, l) -> selected = i);

        addBtn.setOnClickListener(v -> {
            inputCity.setVisibility(View.VISIBLE);
            confirmBtn.setVisibility(View.VISIBLE);
        });

        confirmBtn.setOnClickListener(v -> {
            String name = inputCity.getText().toString();
            if (!name.isEmpty()) {
                cities.add(name);
                adapter.notifyDataSetChanged();
                inputCity.setText("");
                inputCity.setVisibility(View.GONE);
                confirmBtn.setVisibility(View.GONE);
            }
        });

        deleteBtn.setOnClickListener(v -> {
            if (selected != -1) {
                cities.remove(selected);
                adapter.notifyDataSetChanged();
                selected = -1;
            }
        });
    }
}
