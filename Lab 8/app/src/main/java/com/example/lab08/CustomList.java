package com.example.lab08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * This is a class that keeps track of a list of city objects
 */
public class CustomList {
    private List<City> cities = new ArrayList<>();

    /**
     * This adds a city to the list if the city does not exist
     * @param city This is a candidate city to add
     */
    public void addCity(City city) {
        if (cities.contains(city)) {
            throw new IllegalArgumentException();
        }
        cities.add(city);
    }

    /**
     * This returns a sorted list of cities
     * @return Return the sorted list
     */
    public List<City> getCities() {
        List<City> list = cities;
        Collections.sort(list);
        return list;
    }

    /**
     * Checks if a city is present in the list.
     * @param city The city to check for
     * @return true if the city is in the list, false otherwise
     */
    public boolean hasCity(City city) {
        return cities.contains(city);
    }

    /**
     * Removes a city from the list.
     * @param city The city to remove
     * @throws IllegalArgumentException if the city does not exist in the list
     */
    public void deleteCity(City city) {
        if (!cities.contains(city)) {
            throw new IllegalArgumentException();
        }
        cities.remove(city);
    }

    /**
     * Returns how many cities are in the list.
     * @return the number of cities in the list
     */
    public int countCities() {
        return cities.size();
    }
}
