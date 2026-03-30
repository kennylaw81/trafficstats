package com.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Processor that calculates the total number of cars per day.
 */
public class CarCountPerDayProcessor implements ITrafficStatsProcessor {

    private List<TrafficStat> stats;
    private Map<LocalDate, Integer> dailyCounts;

    /**
     * Constructor for CarCountPerDayProcessor.
     * @param stats the list of traffic statistics
     */
    public CarCountPerDayProcessor(List<TrafficStat> stats) {
        this.stats = stats;
        this.dailyCounts = new TreeMap<>();
    }

    /**
     * Processes the statistics by summing car counts per day.
     */
    @Override
    public void Process() {
        for (TrafficStat stat : stats) {
            LocalDate date = stat.getTimestamp().toLocalDate();
            dailyCounts.put(date, dailyCounts.getOrDefault(date, 0) + stat.getCarCount());
        }
    }

    /**
     * Gets the output string with car counts per day.
     * @return the output string in "yyyy-mm-dd count" format, one per line
     */
    @Override
    public String getOutput() {
        List<String> lines = new ArrayList<>();
        for (Map.Entry<LocalDate, Integer> entry : dailyCounts.entrySet()) {
            lines.add(entry.getKey().toString() + " " + entry.getValue());
        }
        return String.join("\n", lines);
    }
}