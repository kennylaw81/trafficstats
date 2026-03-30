package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Unit tests for LeastCarVolumeProcessor class.
 */
public class LeastCarVolumeProcessorTest {

    @Test
    public void testExactWindowSize() {
        List<TrafficStat> stats = Arrays.asList(
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 0), 5),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 30), 10),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 6, 0), 15),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 6, 30), 20)
        );
        LeastCarVolumeProcessor processor = new LeastCarVolumeProcessor(stats, 3);
        processor.Process();
        String expected = "2021-12-01T05:00:00 5\n2021-12-01T05:30:00 10\n2021-12-01T06:00:00 15";
        assertEquals(expected, processor.getOutput());
    }

    @Test
    public void testNoExactWindow() {
        List<TrafficStat> stats = Arrays.asList(
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 0), 5),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 30), 10),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 7, 0), 15)  // gap
        );
        LeastCarVolumeProcessor processor = new LeastCarVolumeProcessor(stats, 3);
        processor.Process();
        String expected = "";
        assertEquals(expected, processor.getOutput());
    }

    @Test
    public void testWindowSizeOne() {
        List<TrafficStat> stats = Arrays.asList(
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 0), 5),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 30), 10)
        );
        LeastCarVolumeProcessor processor = new LeastCarVolumeProcessor(stats, 1);
        processor.Process();
        String expected = "2021-12-01T05:00:00 5";
        assertEquals(expected, processor.getOutput());
    }

    @Test
    public void testMultipleWindowsPickMin() {
        List<TrafficStat> stats = Arrays.asList(
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 0), 10),  // sum 10+5+1=16
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 30), 5),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 6, 0), 1),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 6, 30), 2),  // sum 5+1+2=8
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 7, 0), 5),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 7, 30), 3)
        );
        LeastCarVolumeProcessor processor = new LeastCarVolumeProcessor(stats, 3);
        processor.Process();
        String expected = "2021-12-01T05:30:00 5\n2021-12-01T06:00:00 1\n2021-12-01T06:30:00 2";
        assertEquals(expected, processor.getOutput());
    }

    @Test
    public void testEmptyStats() {
        List<TrafficStat> stats = new ArrayList<>();
        LeastCarVolumeProcessor processor = new LeastCarVolumeProcessor(stats, 3);
        processor.Process();
        String expected = "";
        assertEquals(expected, processor.getOutput());
    }

    @Test
    public void testLeastVolumeAtLastWindow() {
        List<TrafficStat> stats = Arrays.asList(
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 0), 20),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 5, 30), 10),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 6, 0), 5),
            new TrafficStat(LocalDateTime.of(2021, 12, 1, 6, 30), 1)
        );
        LeastCarVolumeProcessor processor = new LeastCarVolumeProcessor(stats, 3);
        processor.Process();
        String expected = "2021-12-01T05:30:00 10\n2021-12-01T06:00:00 5\n2021-12-01T06:30:00 1";
        assertEquals(expected, processor.getOutput());
    }
}