package com.example;

/**
 * Represents a titled processor result for presentation.
 */
public class TrafficStatsResult {

    private final String title;
    private final String output;

    /**
     * @param title the result title
     * @param output the formatted result output
     */
    public TrafficStatsResult(String title, String output) {
        this.title = title;
        this.output = output;
    }

    /**
     * @return the result title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @return the formatted result output
     */
    public String getOutput() {
        return output;
    }
}