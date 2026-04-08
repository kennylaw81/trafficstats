package com.example.repository;

import java.util.List;
import com.example.TrafficStat;

public interface ITrafficStatsRepository {

    /**
    * Loads the traffic statistics from the data source.
     * @throws Exception 
    */
    void loadTrafficStats() throws Exception;
    
    /**
     * Gets the traffic statistics.
     * @return a list of traffic statistics
     */
    List<TrafficStat> getTrafficStats();
}
