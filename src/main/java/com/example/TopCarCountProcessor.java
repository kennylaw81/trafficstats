package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Processor that finds the top N timestamps with the highest car counts.
 */
public class TopCarCountProcessor implements TrafficStatsProcessor {

    private List<TrafficStat> stats;
    private int topN;
    private List<TrafficStat> topStats;

    /**
     * Constructor for TopCarCountProcessor.
     * @param stats the list of traffic statistics
     * @param topN the number of top timespans to evaluate (must be positive)
     */
    public TopCarCountProcessor(List<TrafficStat> stats, int topN) {
        if (topN <= 0) {
            throw new IllegalArgumentException("topN must be positive");
        }
        this.stats = stats;
        this.topN = topN;
        this.topStats = new ArrayList<>();
    }

    /**
     * Processes the statistics by finding the top N entries with highest car counts.
     */
    @Override
    public void process() {
        topStats = stats.stream()
            .sorted((a, b) -> {
                int cmp = Integer.compare(b.getCarCount(), a.getCarCount());
                if (cmp == 0) {
                    return a.getTimestamp().compareTo(b.getTimestamp());
                }
                return cmp;
            })
            .limit(topN)
            .collect(Collectors.toList());
    }

    /**
     * Gets the output string with the top N timespans.
     * @return the output string in "yyyy-mm-ddTHH:mm:ss count" format, one per line, sorted by count descending
     */
    @Override
    public String getOutput() {
        List<String> lines = new ArrayList<>();
        for (TrafficStat stat : topStats) {
            lines.add(stat.toString());
        }
        return String.join("\n", lines);
    }
}