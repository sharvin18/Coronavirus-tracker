package com.sharvin.Coronavirustracker.models;

import java.util.*;
import java.io.*;

public class LocationData {

    private String state;
    private String country;
    private int currentTotalCases;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCurrentTotalCases() {
        return currentTotalCases;
    }

    public void setCurrentTotalCases(int currentTotalCases) {
        this.currentTotalCases = currentTotalCases;
    }

    @Override
    public String toString() {
        return "LocationData{" +
                "state='" + state + '\'' +
                ", country='" + country + '\'' +
                ", currentTotalCases=" + currentTotalCases +
                '}';
    }
}
