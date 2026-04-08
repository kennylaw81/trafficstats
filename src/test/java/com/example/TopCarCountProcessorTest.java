package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Unit tests for TopCarCountProcessor class.
 */
public class TopCarCountProcessorTest {

    @Test
    public void testTopTwoFromFour() {
        List<TrafficStat> stats = Arrays.asList(
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 0), 5),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 30), 15),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 6, 0), 10),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 6, 30), 20)
        );
        TopCarCountProcessor processor = new TopCarCountProcessor(stats, 2);
        processor.process();
        String expected = "2021-12-01T06:30:00 20\n2021-12-01T05:30:00 15";
        assertEquals(expected, processor.getOutput());
    }

    @Test
    public void testTopNGreaterThanStats() {
        List<TrafficStat> stats = Arrays.asList(
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 0), 5),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 30), 10)
        );
        TopCarCountProcessor processor = new TopCarCountProcessor(stats, 5);
        processor.process();
        String expected = "2021-12-01T05:30:00 10\n2021-12-01T05:00:00 5";
        assertEquals(expected, processor.getOutput());
    }

    @Test
    public void testTopOne() {
        List<TrafficStat> stats = Arrays.asList(
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 0), 5),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 30), 15),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 6, 0), 10)
        );
        TopCarCountProcessor processor = new TopCarCountProcessor(stats, 1);
        processor.process();
        String expected = "2021-12-01T05:30:00 15";
        assertEquals(expected, processor.getOutput());
    }

    @Test
    public void testEmptyList() {
        List<TrafficStat> stats = new ArrayList<>();
        TopCarCountProcessor processor = new TopCarCountProcessor(stats, 1);
        processor.process();
        String expected = "";
        assertEquals(expected, processor.getOutput());
    }

    @Test
    public void testSameCountsTopNOne() {
        List<TrafficStat> stats = Arrays.asList(
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 0), 10),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 6, 0), 10),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 7, 0), 5)
        );
        TopCarCountProcessor processor = new TopCarCountProcessor(stats, 1);
        processor.process();
        // Should get the earlier timestamp with count 10
        String expected = "2021-12-01T05:00:00 10";
        assertEquals(expected, processor.getOutput());
    }

    @Test
    public void testSameCountsTopNTwo() {
        List<TrafficStat> stats = Arrays.asList(
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 0), 10),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 6, 0), 10),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 7, 0), 5)
        );
        TopCarCountProcessor processor = new TopCarCountProcessor(stats, 2);
        processor.process();
        // Should get both 10s, in timestamp order
        String expected = "2021-12-01T05:00:00 10\n2021-12-01T06:00:00 10";
        assertEquals(expected, processor.getOutput());
    }

    @Test
    public void testInvalidTopN() {
        List<TrafficStat> stats = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new TopCarCountProcessor(stats, 0));
        assertThrows(IllegalArgumentException.class, () -> new TopCarCountProcessor(stats, -1));
    }
}