package com.example.listcitylab3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class CityArrayAdapter extends ArrayAdapter<com.example.listcitylab3.City> {

    public CityArrayAdapter(Context context, ArrayList<com.example.listcitylab3.City> cities) {
        super(context, 0, cities);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView,
                        @NonNull ViewGroup parent) {

        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext())
                    .inflate(R.layout.content, parent, false);
        }

        com.example.listcitylab3.City city = getItem(position);

        TextView cityName = view.findViewById(R.id.city_name);
        TextView provinceName = view.findViewById(R.id.province_name);

        cityName.setText(city.getName());
        provinceName.setText(city.getProvince());

        return view;
    }
}
