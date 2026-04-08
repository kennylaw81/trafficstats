package com.example;

import java.util.List;

/**
 * Registry of processor definitions used by the traffic stats client.
 */
public final class TrafficStatsProcessorRegistry {

    private TrafficStatsProcessorRegistry() {
    }

    /**
     * @return the ordered processor definitions to execute
     */
    public static List<TrafficStatsProcessorDefinition> getDefinitions() {
        return List.of(
            new TrafficStatsProcessorDefinition(
                "Total cars:",
                CarCountProcessor::new
            ),
            new TrafficStatsProcessorDefinition(
                "The number of cars seen each day:",
                CarCountPerDayProcessor::new
            ),
            new TrafficStatsProcessorDefinition(
                "The top 3 half hours with most cars:",
                stats -> new TopCarCountProcessor(stats, 3)
            ),
            new TrafficStatsProcessorDefinition(
                "The 1.5 hour period with least cars:",
                stats -> new LeastCarVolumeProcessor(stats, 3)
            )
        );
    }
}