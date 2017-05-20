package com.wbapps.TaskTwo;

import java.util.HashMap;

/**
 * Created by samik on 20.05.2017.
 * <p>
 * This class is a data model of the City
 */
public class CityModel {

    // Keeps the name of the city
    private String name;
    // Keeps the number of neighbours of city
    private int neighbours;
    // Keeps id and cost,
    // key - id, volume - cost
    private HashMap<Integer, Integer> idAndCost;


    public CityModel() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(int neighbours) {
        this.neighbours = neighbours;
    }

    public HashMap<Integer, Integer> getIdAndCost() {
        return idAndCost;
    }

    public void setIdAndCost(HashMap<Integer, Integer> idAndCost) {
        this.idAndCost = idAndCost;
    }

}
