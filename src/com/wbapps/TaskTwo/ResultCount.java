package com.wbapps.TaskTwo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by samik on 20.05.2017.
 * <p>
 * This class is responsible for counting the result and showing it to the user
 */
public class ResultCount {
    private static final String START_PART_TEXT = "\n| PART : ";
    private static final String END_PART_TEXT = "\n| Minimal transportation cost : ";


    private int numbCities; // Keep number of cities
    private int partToFind; // Keep number of paths to find
    private ArrayList<CityModel> citiesList;
    // Keeps variants of ways.
    // String[] = {name1, name2}
    private ArrayList<String[]> routsList;


    public ResultCount(int numbCities, int partToFind, ArrayList<CityModel> citiesList, ArrayList<String[]> routsList) {
        this.numbCities = numbCities;
        this.partToFind = partToFind;
        this.citiesList = citiesList;
        this.routsList = routsList;
    }


    public void startWork() {

        // We consider the minimum shipping cost for each route
        for (String[] list : routsList) {

            HashMap<Integer, Integer> startCityRoad = new HashMap<>();
            HashMap<Integer, Integer> endCityRoad = new HashMap<>();
            ArrayList<Integer> finalCityRoad = new ArrayList<>();

            String startCity = list[0]; // Source
            String endCity = list[1]; // Destination

            // Record cities to which you can get from startCity
            for (CityModel city : citiesList) {

                if (city.getName().equals(startCity)) {
                    startCityRoad = city.getIdAndCost();
                }
            }

            // Record cities to which you can get from endCity
            for (CityModel city : citiesList) {

                if (city.getName().equals(endCity)) {
                    endCityRoad = city.getIdAndCost();
                }
            }

            /** We search for coincidences on cities.
             * The cities contained in both arrays are preserved.
             * The main choice is the array, the length of which is longer, that would go through all the options
             */
            if (startCityRoad.size() >= endCityRoad.size()) {

                /**
                 * We go through 2 collections.
                 * In case of coincidence id, I think the cost of delivery through this city.
                 * The result is saved in finalCityRoad
                 */
                for (Map.Entry entry : startCityRoad.entrySet()) {
                    int id = (int) entry.getKey();

                    for (Map.Entry _entry : endCityRoad.entrySet()) {
                        int _id = (int) _entry.getKey();

                        if (id == _id) {
                            int sum = (int) entry.getValue() + (int) _entry.getValue();
                            finalCityRoad.add(sum);
                        }
                    }
                }

            } else {

                // Do the same thing as in the case of true. Only we change arrays in places
                for (Map.Entry entry : endCityRoad.entrySet()) {
                    int id = (int) entry.getKey();

                    for (Map.Entry _entry : startCityRoad.entrySet()) {
                        int _id = (int) _entry.getValue();
                        if (id == _id) {
                            int sum = (int) entry.getValue() + (int) _entry.getValue();
                            finalCityRoad.add(sum);
                        }
                    }
                }
            }

            // We find the minimum amount of delivery
            // P.S: That would sort out all possible options, first you need to find the maximum cost of the goods
            int minCost = 0;
            for (int x : finalCityRoad) {
                if (x > minCost) {
                    minCost = x;
                }
            }
            for (int x : finalCityRoad) {
                if (minCost > x) {
                    minCost = x;
                }
            }

            // Show result in console
            printResult(minCost, startCity, endCity);
        }

    }

    private void printResult(int sum, String start, String end) {
        System.out.println(START_PART_TEXT + start + " --> " + end + END_PART_TEXT + sum);
    }
}
