package com.example.repository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.example.TrafficStat;

/**
 * Reads a file containing traffic statistics and stores them as a list of TrafficStat objects.
 */
public class TrafficStatsFileRepository implements ITrafficStatsRepository {

    private String filePath;
    private List<TrafficStat> trafficStats;

    /**
     * Constructor that reads the file and parses the traffic stats.
     * @param filePath the path to the input file
     */
    public TrafficStatsFileRepository(String filePath) {
        this.filePath = filePath;
        trafficStats = new ArrayList<>();
    }

    /**
     * Loads the traffic statistics from the file.
     */
    @Override
    public void loadTrafficStats() throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        
        for (String line : lines) {
            String[] parts = line.trim().split("\\s+");
            if (parts.length == 2) {
                try {
                    LocalDateTime timestamp = LocalDateTime.parse(parts[0], formatter);
                    int count = Integer.parseInt(parts[1]);
                    trafficStats.add(new TrafficStat(timestamp, count));
                } catch (Exception e) {
                    // Skip invalid lines
                }
            }
        }
    }

    /**
     * Gets the list of traffic stats.
     * @return the list of TrafficStat objects
     */
    @Override
    public List<TrafficStat> getTrafficStats() {
        return trafficStats;
    }
}