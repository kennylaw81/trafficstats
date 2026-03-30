package com.example;

import java.io.IOException;
import java.util.List;

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
        try {
            FileInputReader reader = new FileInputReader(filePath);
            List<TrafficStat> stats = reader.getTrafficStats();

            // Debugging purposes only: print the stats to console
            //stats.forEach(System.out::println);

            CarCountProcessor processor = new CarCountProcessor(stats);
            processor.Process();
            System.out.println(processor.getOutput());

            System.out.println("The number of cars seen each day:");
            CarCountPerDayProcessor perDayProcessor = new CarCountPerDayProcessor(stats);
            perDayProcessor.Process();
            System.out.println(perDayProcessor.getOutput());

            System.out.println("The top 3 half hours with most cars:");
            TopCarCountProcessor topCarCountProcessor = new TopCarCountProcessor(stats, 3);
            topCarCountProcessor.Process();
            System.out.println(topCarCountProcessor.getOutput());

            System.out.println("The 1.5 hour period with least cars:");
            LeastCarVolumeProcessor leastCarVolumeProcessor = new LeastCarVolumeProcessor(stats, 3);
            leastCarVolumeProcessor.Process();
            System.out.println(leastCarVolumeProcessor.getOutput());

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

    }
}