package com.example;

import java.util.List;

/**
 * Processor that calculates the total number of cars seen.
 */
public class CarCountProcessor implements ITrafficStatsProcessor {

    private List<TrafficStat> stats;
    private int numberOfCars;

    /**
     * Constructor for CarCountProcessor.
     * @param stats the list of traffic statistics
     */
    public CarCountProcessor(List<TrafficStat> stats) {
        this.stats = stats;
        this.numberOfCars = 0;
    }

    /**
     * Processes the statistics by summing the car counts.
     */
    @Override
    public void Process() {
        stats.forEach(stat -> numberOfCars += stat.getCarCount()); 
    }

    /**
     * Gets the output string with the total car count.
     * @return the output string
     */
    @Override
    public String getOutput() {
        return "Number of cars seen: " + numberOfCars;
    }
}