package com.example;

/**
 * Interface for processing traffic statistics.
 */
public interface TrafficStatsProcessor {

    /**
     * Processes the traffic statistics.
     */
    void process();

    /**
     * Gets the output string.
     * @return the processed output
     */
    String getOutput();
}