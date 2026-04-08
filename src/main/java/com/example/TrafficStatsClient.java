package com.example;

import com.example.repository.ITrafficStatsRepository;

public class TrafficStatsClient {
    private ITrafficStatsRepository repository;

    public TrafficStatsClient(ITrafficStatsRepository repository) {
        this.repository = repository;
    }

    public void run() {
        try {
            repository.loadTrafficStats();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
        var stats = repository.getTrafficStats();

        CarCountProcessor processor = new CarCountProcessor(stats);
        processor.Process();
        System.out.println(processor.getOutput());

        System.out.println("The number of cars seen each day :");
        CarCountPerDayProcessor perDayProcessor = new CarCountPerDayProcessor(stats);
        perDayProcessor.Process();
        System.out.println(perDayProcessor.getOutput());

        System.out.println("The top 3 half hours with most cars :");
        TopCarCountProcessor topCarCountProcessor = new TopCarCountProcessor(stats, 3);
        topCarCountProcessor.Process();
        System.out.println(topCarCountProcessor.getOutput());

        System.out.println("The 1.5 hour period with least cars :");
        LeastCarVolumeProcessor leastCarVolumeProcessor = new LeastCarVolumeProcessor(stats, 3);
        leastCarVolumeProcessor.Process();
        System.out.println(leastCarVolumeProcessor.getOutput());
    }


}
