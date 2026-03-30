package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;

/**
 * Unit tests for TrafficStat class.
 */
public class TrafficStatTest {

    @Test
    public void testToString() {
        LocalDateTime timestamp = LocalDateTime.of(2021, 12, 1, 5, 0, 0);
        int carCount = 5;
        TrafficStat stat = new TrafficStat(timestamp, carCount);
        String expected = "2021-12-01T05:00:00 5";
        assertEquals(expected, stat.toString());
    }
}