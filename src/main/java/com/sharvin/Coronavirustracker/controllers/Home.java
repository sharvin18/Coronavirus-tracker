package com.sharvin.Coronavirustracker.controllers;

import com.sharvin.Coronavirustracker.models.LocationData;
import com.sharvin.Coronavirustracker.services.CoronavirusServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import java.io.*;

@Controller
public class Home {

    @Autowired
    CoronavirusServices coronavirusServices;

    @GetMapping("/")
    public String home(Model model){

        List<LocationData> covidData = coronavirusServices.getAllData();

        // Finding total cases over the world to display it on UI.
        long totalCases = covidData.stream().mapToInt(stats -> stats.getCurrentTotalCases()).sum();
        long totalNewCases = covidData.stream().mapToInt(stats -> stats.getDiff()).sum();

        model.addAttribute("locationData", covidData);
        model.addAttribute("totalCasesReported", totalCases);
        model.addAttribute("totalNewCases", totalNewCases);

        return "home";
    }
}
