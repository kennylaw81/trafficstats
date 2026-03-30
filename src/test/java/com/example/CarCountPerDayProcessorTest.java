package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Unit tests for CarCountPerDayProcessor class.
 */
public class CarCountPerDayProcessorTest {


    @Test
    public void testEmptyList() {
        List<TrafficStat> stats = new ArrayList<>();
        CarCountPerDayProcessor processor = new CarCountPerDayProcessor(stats);
        processor.Process();
        String expected = "";
        assertEquals(expected, processor.getOutput());
    }

    @Test
    public void testSingleDay() {
        List<TrafficStat> stats = Arrays.asList(
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 0), 7),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 30), 3)
        );
        CarCountPerDayProcessor processor = new CarCountPerDayProcessor(stats);
        processor.Process();
        String expected = "2021-12-01 10";
        assertEquals(expected, processor.getOutput());
    }

    @Test
    public void testAllZeros() {
        List<TrafficStat> stats = Arrays.asList(
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 0), 0),
            new TrafficStat(LocalDateTime.of(2021, 12, 2, 6, 0), 0)
        );
        CarCountPerDayProcessor processor = new CarCountPerDayProcessor(stats);
        processor.Process();
        String expected = "2021-12-01 0\n2021-12-02 0";
        assertEquals(expected, processor.getOutput());
    }

        @Test
    public void testMultipleDays() {
        List<TrafficStat> stats = Arrays.asList(
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 0), 5),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 30), 10),
            new TrafficStat(LocalDateTime.of(2021, 12, 2, 6, 0), 15),
            new TrafficStat(LocalDateTime.of(2021, 12, 2, 6, 30), 5)
        );
        CarCountPerDayProcessor processor = new CarCountPerDayProcessor(stats);
        processor.Process();
        String expected = "2021-12-01 15\n2021-12-02 20";
        assertEquals(expected, processor.getOutput());
    }

    @Test
    public void testSameDayMultipleEntries() {
        List<TrafficStat> stats = Arrays.asList(
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 0), 1),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 6, 0), 2),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 7, 0), 3)
        );
        CarCountPerDayProcessor processor = new CarCountPerDayProcessor(stats);
        processor.Process();
        String expected = "2021-12-01 6";
        assertEquals(expected, processor.getOutput());
    }
}