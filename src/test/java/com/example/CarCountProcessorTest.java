package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Unit tests for CarCountProcessor class.
 */
public class CarCountProcessorTest {

    @Test
    public void testNormalCase() {
        List<TrafficStat> stats = Arrays.asList(
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 0), 5),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 30), 10),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 6, 0), 15)
        );
        CarCountProcessor processor = new CarCountProcessor(stats);
        processor.Process();
        assertEquals("Number of cars seen: 30", processor.getOutput());
    }

    @Test
    public void testAllZeros() {
        List<TrafficStat> stats = Arrays.asList(
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 0), 0),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 30), 0)
        );
        CarCountProcessor processor = new CarCountProcessor(stats);
        processor.Process();
        assertEquals("Number of cars seen: 0", processor.getOutput());
    }

    @Test
    public void testEmptyList() {
        List<TrafficStat> stats = new ArrayList<>();
        CarCountProcessor processor = new CarCountProcessor(stats);
        processor.Process();
        assertEquals("Number of cars seen: 0", processor.getOutput());
    }

    @Test
    public void testSingleStat() {
        List<TrafficStat> stats = Arrays.asList(
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 0), 7)
        );
        CarCountProcessor processor = new CarCountProcessor(stats);
        processor.Process();
        assertEquals("Number of cars seen: 7", processor.getOutput());
    }
}