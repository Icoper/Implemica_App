package com.wbapps.TaskTwo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by samik on 20.05.2017.
 * <p>
 * Find the paths of minimum cost between pairs of cities.
 * <p>
 * This class is responsible for collecting data, and sending them for processing to the ResultCount.class
 */
public class GetTaskData {
    private static final int DEFAULT_NUMBER_TEST = 1;

    private static final String TEXT_ERROR = "| ERROR! Re-enter psl!";
    private static final String TEXT_CITY_COUNT = "| Ok. Enter data about city number ";
    private static final String TEXT_PART = "| part - ";

    private static final String TEXT_TEST_NUMBER = "| the number of tests (match bee <=10) :  ";
    private static final String TEXT_CITY_NUMBER = "| the number of cities (match bee <=10000) :  ";
    private static final String TEXT_CITY_NAME = "| city name (is at most 10 characters long):  ";
    private static final String TEXT_NEIGH_NUMBER = "| the number of neighbours of city :  ";
    private static final String TEXT_PARTS_NUMBER = "| the number of paths to find (match bee <=100) :  ";
    private static final String TEXT_ID_NR = "| enter index of a city connected to ";
    private static final String TEXT_ID_COTS = "| ----the transportation cost in this city : ";
    private static final String TEXT_SOURCE = "| the source name :  ";
    private static final String TEXT_DESTINATION = "| the destination name :  ";

    private BufferedReader reader;

    private int numbTest; // Keep number of test
    private int numbCities; // Keep number of cities
    private int partToFind; // Keep number of paths to find

    private ArrayList<CityModel> citiesList;
    // Keeps variants of ways.
    // String[] = {name1, name2}
    private ArrayList<String[]> routsList;


    public void start() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        citiesList = new ArrayList<>();
        routsList = new ArrayList<>();

        int count = numbTest;
        if (count <= DEFAULT_NUMBER_TEST) {
            count = DEFAULT_NUMBER_TEST;
        }

        // Repeat the whole cycle count number of times
        for (int i = 0; i < count; i++) {
            getInputData();
        }

    }

    // This method is called when we need to get data from a user
    private void getInputData() {

        try {
            // Get the total number of tests
            printL(TEXT_TEST_NUMBER);
            String test = reader.readLine();

            if (checkInputData(test)) {
                numbTest = Integer.parseInt(test);
            } else {
                // show error msg & re-enter data
                printL(TEXT_ERROR);
                getInputData();
            }

            // Get the total number of cities participating in the test
            printL(TEXT_CITY_NUMBER);
            String cNumb = reader.readLine();

            // Check the entered data for compliance with the job condition
            if (checkInputData(cNumb)) {
                numbCities = Integer.parseInt(cNumb);

                // if city numbers <= 1 - stop the task
                if (numbCities <= 1) {
                    printL("\nThis is senseless");
                    return;
                }

            } else {
                // show error msg & re-enter data
                printL(TEXT_ERROR);
                getInputData();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // We request input for all cities
        int count = 1;
        while (count <= numbCities) {
            printL(TEXT_CITY_COUNT + count);
            getCityData(count);
            count++;
        }

        printL(TEXT_PARTS_NUMBER);
        String parts = "";
        try {
            parts = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Check the entered data for compliance with the job condition
        if (!checkInputData(parts) &&
                Integer.parseInt(parts) > 100) {

            // show error msg & re-enter data
            printL(TEXT_ERROR);
            getCityData(count);
        }
        partToFind = Integer.parseInt(parts);

        // Enter the required route for calculation
        inputPartToFind();

        // We send all the data for processing and displaying the result
        ResultCount resultCount = new ResultCount(
                numbCities,
                partToFind,
                citiesList,
                routsList
        );

        printL("\n      -RESULT- ");
        resultCount.startWork();

    }

    // Enter the required route for calculation
    private void inputPartToFind() {
        for (int i = 1; i <= partToFind; i++) {
            printL(TEXT_PART + i);
            try {
                printL(TEXT_SOURCE);
                String source = reader.readLine();
                // Check the entered data for compliance with the job condition
                if (!checkCityName(source)) {
                    // show error msg & re-enter data
                    printL(TEXT_ERROR);
                    inputPartToFind();
                }

                printL(TEXT_DESTINATION);
                String destination = reader.readLine();

                // Check the entered data for compliance with the job condition
                if (!checkCityName(destination)) {
                    // show error msg & re-enter data
                    printL(TEXT_ERROR);
                    inputPartToFind();
                }
                String[] temp = {source, destination};

                routsList.add(temp);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    // In this method, we completely fill the CityModel object with the data necessary for the task
    private void getCityData(int count) {
        CityModel city = new CityModel();

        try {
            // Read the name of the city
            printL(TEXT_CITY_NAME);
            String name = reader.readLine();

            // Check the entered data for compliance with the job condition
            if (name.length() > 10) {
                // show error msg & re-enter data
                printL(TEXT_ERROR);
                getCityData(count);
            }
            city.setName(name);

            // Read the number of cities nearby
            printL(TEXT_NEIGH_NUMBER);
            String neigh = reader.readLine();

            // Check the entered data for compliance with the job condition
            if (!checkInputData(neigh)) {
                // show error msg & re-enter data
                printL(TEXT_ERROR);
                getCityData(count);
            }
            city.setNeighbours(Integer.parseInt(neigh));

            // Read which cities are available for transportation and what is the cost
            // idAndCost <key(id), volume(cost)>
            HashMap<Integer, Integer> idAndCost = new HashMap<>();
            for (int i = 0; i < city.getNeighbours(); i++) {

                printL(TEXT_ID_NR + city.getName());
                String id = reader.readLine();

                // Check the entered data for compliance with the job condition
                if (!checkInputData(id)) {
                    // show error msg & re-enter data
                    printL(TEXT_ERROR);
                    getCityData(count);
                }
                printL(TEXT_ID_COTS + city.getName());
                String cost = reader.readLine();

                // Check the entered data for compliance with the job condition
                if (!checkInputData(cost)) {
                    // show error msg & re-enter data
                    printL(TEXT_ERROR);
                    getCityData(count);
                }

                idAndCost.put(
                        Integer.parseInt(id),
                        Integer.parseInt(cost)
                );
            }

            city.setIdAndCost(idAndCost);


        } catch (IOException e) {
            e.printStackTrace();
        }

        citiesList.add(city);
    }

    /**
     * Check if the name entered by the user matches the cities present in our list
     *
     * @param name - entering city name
     * @return - false if entering name not contained in our list
     */
    private boolean checkCityName(String name) {

        for (CityModel s : citiesList) {
            if (s.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method is called when we need to check the string for the correctness.
     *
     * @param s User-entered string
     * @return If the string contains characters other than numbers, it returns false
     */
    private boolean checkInputData(String s) {
        return (s.matches("-?[\\d]+") && s.length() != 0);
    }

    private void printL(String s) {
        System.out.println(s);
    }
}
