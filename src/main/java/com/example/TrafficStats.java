package com.example;

import java.util.List;

import com.example.repository.TrafficStatsFileRepository;

/**
 * Traffic Stats console application. Handles constructing the client and repository source, and printing the results.
 */
public class TrafficStats {

    /**
     * Main method to run the application.
     * @param args command line arguments, expects the file path as first arg
     */
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Usage: java TrafficStats <file_path>");
            return;
        }
        String filePath = args[0];
        TrafficStatsClient client = new TrafficStatsClient(new TrafficStatsFileRepository(filePath));

        try {
            List<TrafficStatsResult> results = client.run();
            for (TrafficStatsResult result : results) {
                System.out.println(result.getTitle());
                System.out.println(result.getOutput());
                System.out.println();
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}