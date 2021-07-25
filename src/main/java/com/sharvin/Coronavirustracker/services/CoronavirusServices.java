package com.sharvin.Coronavirustracker.services;

import com.sharvin.Coronavirustracker.models.LocationData;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronavirusServices {

    private static String DATA_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";

    private List<LocationData> allData = new ArrayList<>();

    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void fetchData() throws IOException, InterruptedException {

        List<LocationData> newStats = new ArrayList<>();

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(DATA_URL))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


        StringReader readData = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(readData);

        for (CSVRecord record : records) {

            LocationData locationData = new LocationData();
            locationData.setState(record.get("Province/State"));
            locationData.setCountry(record.get("Country/Region"));

            // Store the current dated cases only.
            locationData.setCurrentTotalCases(Integer.parseInt(record.get(record.size()-1)));
            System.out.println(locationData);
            newStats.add(locationData);
        }

        this.allData = newStats;

    }
}
