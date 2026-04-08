package com.example;

import java.util.List;
import java.util.function.Function;

/**
 * Defines how to create a processor result from traffic statistics.
 */
public class TrafficStatsProcessorDefinition {

    private final String title;
    private final Function<List<TrafficStat>, TrafficStatsProcessor> processorFactory;

    /**
     * @param title the presentation title for the processor output
     * @param processorFactory builds a processor for the provided stats
     */
    public TrafficStatsProcessorDefinition(
        String title,
        Function<List<TrafficStat>, TrafficStatsProcessor> processorFactory
    ) {
        this.title = title;
        this.processorFactory = processorFactory;
    }

    /**
     * @return the title for the processor output
     */
    public String getTitle() {
        return title;
    }

    /**
     * Creates a processor using the provided stats.
     * @param stats the traffic stats to process
     * @return a processor instance
     */
    public TrafficStatsProcessor createProcessor(List<TrafficStat> stats) {
        return processorFactory.apply(stats);
    }
}