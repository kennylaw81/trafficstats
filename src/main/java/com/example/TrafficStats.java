package com.example;

import com.example.repository.TrafficStatsFileRepository;

/**
 * Traffic Stats console application.
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
        client.run();
    }
}