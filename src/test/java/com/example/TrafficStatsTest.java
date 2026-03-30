package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for TrafficStats class (E2E tests later).
 */
public class TrafficStatsTest {

    // E2E tests to be added later

    @Test
    public void testE2EInput1TotalCars() throws Exception {
        FileInputReader reader = new FileInputReader("src/test/java/com/example/samples/input1.txt");
        CarCountProcessor processor = new CarCountProcessor(reader.getTrafficStats());
        processor.Process();
        assertEquals("Number of cars seen: 398", processor.getOutput());
    }

    @Test
    public void testE2EInput2TotalCars() throws Exception {
        FileInputReader reader = new FileInputReader("src/test/java/com/example/samples/input2.txt");
        CarCountProcessor processor = new CarCountProcessor(reader.getTrafficStats());
        processor.Process();
        assertEquals("Number of cars seen: 345", processor.getOutput());
    }

    @Test
    public void testE2EInput1LeastCarVolumeWindow() throws Exception {
        FileInputReader reader = new FileInputReader("src/test/java/com/example/samples/input1.txt");
        LeastCarVolumeProcessor processor = new LeastCarVolumeProcessor(reader.getTrafficStats(), 3);
        processor.Process();
        String expected = "2021-12-01T05:00:00 5\n2021-12-01T05:30:00 12\n2021-12-01T06:00:00 14";
        assertEquals(expected, processor.getOutput());
    }

    @Test
    public void testE2EInput2LeastCarVolumeWindow() throws Exception {
        FileInputReader reader = new FileInputReader("src/test/java/com/example/samples/input2.txt");
        LeastCarVolumeProcessor processor = new LeastCarVolumeProcessor(reader.getTrafficStats(), 3);
        processor.Process();
        String expected = "2022-01-01T03:00:00 3\n2022-01-01T03:30:00 2\n2022-01-01T04:00:00 9";
        assertEquals(expected, processor.getOutput());
    }

    @Test
    public void testE2EInput1CarCountPerDay() throws Exception {
        FileInputReader reader = new FileInputReader("src/test/java/com/example/samples/input1.txt");
        CarCountPerDayProcessor processor = new CarCountPerDayProcessor(reader.getTrafficStats());
        processor.Process();
        String expected = "2021-12-01 179\n2021-12-05 81\n2021-12-08 134\n2021-12-09 4";
        assertEquals(expected, processor.getOutput());
    }

    @Test
    public void testE2EInput2CarCountPerDay() throws Exception {
        FileInputReader reader = new FileInputReader("src/test/java/com/example/samples/input2.txt");
        CarCountPerDayProcessor processor = new CarCountPerDayProcessor(reader.getTrafficStats());
        processor.Process();
        String expected = "2022-01-01 345";
        assertEquals(expected, processor.getOutput());
    }

    @Test
    public void testE2EInput1TopCarCount() throws Exception {
        FileInputReader reader = new FileInputReader("src/test/java/com/example/samples/input1.txt");
        TopCarCountProcessor processor = new TopCarCountProcessor(reader.getTrafficStats(), 3);
        processor.Process();
        String expected = "2021-12-01T07:30:00 46\n2021-12-01T08:00:00 42\n2021-12-08T18:00:00 33";
        assertEquals(expected, processor.getOutput());
    }

    @Test
    public void testE2EInput2TopCarCount() throws Exception {
        FileInputReader reader = new FileInputReader("src/test/java/com/example/samples/input2.txt");
        TopCarCountProcessor processor = new TopCarCountProcessor(reader.getTrafficStats(), 3);
        processor.Process();
        String expected = "2022-01-01T02:00:00 15\n2022-01-01T11:00:00 15\n2022-01-01T08:30:00 14";
        assertEquals(expected, processor.getOutput());
    }
}