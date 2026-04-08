package com.example;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Processor that finds the contiguous window of timespans with the least car volume.
 */
public class LeastCarVolumeProcessor implements ITrafficStatsProcessor {

    private List<TrafficStat> stats;
    private int windowSize;
    private List<TrafficStat> leastWindow;

    /**
     * Constructor for LeastCarVolumeProcessor.
     * @param stats the list of traffic statistics
     * @param windowSize the number of contiguous timespans to evaluate (must be positive)
     */
    public LeastCarVolumeProcessor(List<TrafficStat> stats, int windowSize) {
        if (windowSize <= 0) {
            throw new IllegalArgumentException("windowSize must be positive");
        }
        this.stats = stats;
        this.windowSize = windowSize;
        this.leastWindow = new ArrayList<>();
    }

    /**
     * Processes the statistics by finding the contiguous window with the least car volume.
     */
    @Override
    public void Process() {
        if (stats.isEmpty()) {
            return;
        }
        long minSum = Long.MAX_VALUE;   // Integer overflow is possible if we use int, so use long for sum
        List<TrafficStat> bestWindow = new ArrayList<>();
        long currentSum = 0;
        int left = 0;
        for (int right = 0; right < stats.size(); right++) {
            if (right > 0 && Duration.between(stats.get(right - 1).getTimestamp(), stats.get(right).getTimestamp()).toMinutes() != 30) {
                left = right;
                currentSum = 0;
            }
            currentSum += stats.get(right).getCarCount();
            int currentSize = right - left + 1;

            if (currentSize > windowSize) {
                currentSum -= stats.get(left).getCarCount();
                left++;
                currentSize--;
            }

            // ASSUMPTION: Only evaluate records where the window is exactly the specified size, not smaller. 
            if (currentSize == windowSize && currentSum < minSum) {
                minSum = currentSum;
                bestWindow = new ArrayList<>(stats.subList(left, right + 1));
            }
        }
        leastWindow = bestWindow;
    }

    /**
     * Gets the output string with the least volume window.
     * @return the output string in "yyyy-mm-ddTHH:mm:ss count" format, one per line
     */
    @Override
    public String getOutput() {
        List<String> lines = new ArrayList<>();
        for (TrafficStat stat : leastWindow) {
            lines.add(stat.toString());
        }
        return String.join("\n", lines);
    }
}