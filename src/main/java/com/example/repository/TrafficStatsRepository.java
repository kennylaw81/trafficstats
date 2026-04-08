package com.example.repository;

import java.util.List;
import com.example.TrafficStat;

public interface TrafficStatsRepository {

    
    /**
     * Gets the traffic statistics.
     * @return a list of traffic statistics
     */
    List<TrafficStat> getTrafficStats() throws Exception;
}
