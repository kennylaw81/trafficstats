# TrafficStats Java Console Application

### Note: This README.md is AI Generated

## Overview

This project is a Java console application for processing traffic statistics from a text file. It supports:

- Counting total cars
- Summing cars per day
- Finding top N timespans with the most cars
- Finding the least car volume window of a given size

## Prerequisites

- Java 11 or higher
- Maven 3.6 or higher

## Project Structure

- `src/main/java/com/example/` — Application source code
- `src/test/java/com/example/` — Unit and E2E tests
- `src/test/java/com/example/samples/` — Sample input files

## How to Compile

```
mvn clean compile
```

## How to Run the Program

1. **Build the project:**
   ```
   mvn clean package
   ```
2. **Run the main class:**
   ```
   java -cp target/classes com.example.TrafficStats <input_file_path>
   ```
   Example:
   ```
   java -cp target/classes com.example.TrafficStats src/test/java/com/example/samples/input1.txt
   ```

## How to Run the Tests

```
mvn test
```

This will run all unit and end-to-end tests, including those for:

- `CarCountProcessor`
- `CarCountPerDayProcessor`
- `TopCarCountProcessor`
- `LeastCarVolumeProcessor`

## Sample Input Format

Each line in the input file should be:

```
yyyy-mm-ddThh:mm:ss <car_count>
```

Example:

```
2022-01-01T00:00:00 8
2022-01-01T00:30:00 12
```

## Notes

- All input files must be plain text and use the format above.
- For custom processing, modify or extend the processor classes in `src/main/java/com/example/`.
