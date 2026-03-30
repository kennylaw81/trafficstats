package com.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a traffic statistic entry with a timestamp and car count.
 */
public class TrafficStat {

    private LocalDateTime timestamp;
    private int carCount;

    /**
     * Constructor for TrafficStat.
     * @param timestamp the timestamp of the entry
     * @param carCount the number of cars
     */
    public TrafficStat(LocalDateTime timestamp, int carCount) {
        this.timestamp = timestamp;
        this.carCount = carCount;
    }

    /**
     * Gets the timestamp.
     * @return the timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Gets the car count.
     * @return the car count
     */
    public int getCarCount() {
        return carCount;
    }

    /**
     * Returns a string representation in the input format.
     * @return the timestamp and car count as a string
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
        return timestamp.format(formatter) + " " + carCount;
    }
}