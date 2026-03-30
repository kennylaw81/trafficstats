package com.example;

/**
 * Interface for processing traffic statistics.
 */
public interface ITrafficStatsProcessor {

    /**
     * Processes the traffic statistics.
     */
    void Process();

    /**
     * Gets the output string.
     * @return the processed output
     */
    String getOutput();
}