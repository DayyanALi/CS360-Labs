package com.example.lab08;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CustomListTest {

    private CustomList mockCustomList() {
        CustomList customList = new CustomList();
        customList.addCity(mockCity());
        return customList;
    }

    private City mockCity() {
        return new City("Edmonton", "Alberta");
    }

    @Test
    void testAdd() {
        CustomList customList = mockCustomList();
        assertEquals(1, customList.getCities().size());
        City city = new City("Regina", "Saskatchewan");
        customList.addCity(city);
        assertEquals(2, customList.getCities().size());
        assertTrue(customList.getCities().contains(city));
    }

    @Test
    void testAddException() {
        CustomList customList = mockCustomList();
        City city = new City("Yellowknife", "Northwest Territories");
        customList.addCity(city);
        assertThrows(IllegalArgumentException.class, () -> {
            customList.addCity(city);
        });
    }

    @Test
    void testGetCities() {
        CustomList customList = mockCustomList();
        assertEquals(0, mockCity().compareTo(customList.getCities().get(0)));
        City city = new City("Charlottetown", "Prince Edward Island");
        customList.addCity(city);
        assertEquals(0, city.compareTo(customList.getCities().get(0)));
        assertEquals(0, mockCity().compareTo(customList.getCities().get(1)));
    }

    @Test
    void testHasCity() {
        CustomList customList = mockCustomList();
        City city = new City("Charlottetown", "Prince Edward Island");
        assertFalse(customList.hasCity(city));
        customList.addCity(city);
        assertTrue(customList.hasCity(city));
    }

    @Test
    void testDelete() {
        CustomList customList = mockCustomList();
        City city = new City("Charlottetown", "Prince Edward Island");
        customList.addCity(city);
        assertTrue(customList.hasCity(city));
        customList.deleteCity(city);
        assertFalse(customList.hasCity(city));
    }

    @Test
    void testDeleteException() {
        CustomList customList = mockCustomList();
        City city = new City("Charlottetown", "Prince Edward Island");
        assertThrows(IllegalArgumentException.class, () -> {
            customList.deleteCity(city);
        });
    }

    @Test
    void testCountCities() {
        CustomList customList = mockCustomList();
        assertEquals(1, customList.countCities());
        City city = new City("Charlottetown", "Prince Edward Island");
        customList.addCity(city);
        assertEquals(2, customList.countCities());
        customList.deleteCity(city);
        assertEquals(1, customList.countCities());
    }
}
