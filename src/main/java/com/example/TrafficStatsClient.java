package com.example;


import java.util.ArrayList;
import java.util.List;

import com.example.repository.TrafficStatsRepository;

public class TrafficStatsClient {
    private TrafficStatsRepository repository;

    public TrafficStatsClient(TrafficStatsRepository repository) {
        this.repository = repository;
    }

    public List<TrafficStatsResult> run() throws Exception {
        var stats = repository.getTrafficStats();
        List<TrafficStatsResult> results = new ArrayList<>();

        for (TrafficStatsProcessorDefinition definition : TrafficStatsProcessorRegistry.getDefinitions()) {
            TrafficStatsProcessor processor = definition.createProcessor(stats);
            processor.process();
            results.add(new TrafficStatsResult(definition.getTitle(), processor.getOutput()));
        }

        return results;
    }


}
