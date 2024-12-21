package solutions.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.stream.Stream;

public abstract class Task {

  private final Logger log = Logger.getLogger(Task.class.getName());

  public abstract TaskSolution run(String fileName);

  public Class<? extends Task> getType() {
    return this.getClass();
  }

  public char[][] getFileAs2dCharArray(String pathToFile) {
    List<String> fileLines = getFileLines(pathToFile);

    return fileLines.stream()
        .map(String::toCharArray)
        .toArray(char[][]::new);
  }

  public List<int[]> getFileAs2dIntArray(String pathToFile) {
    List<String> reportLines = getFileLines(pathToFile);
    List<int[]> reports = new ArrayList<>();
    reportLines.forEach(f -> reports.add(getIntArray(f)));
    return reports;
  }

  private int[] getIntArray(String stringArray) {
    return Arrays.stream(stringArray.split(" "))
        .mapToInt(Integer::parseInt)
        .toArray();
  }

  private List<String> getFileLines(String pathToFile) {
    List<String> fileLines = new ArrayList<>();

    Path filePath = Paths.get(pathToFile);
    try (Stream<String> lines = Files.lines(filePath)) {
      lines.forEach(fileLines::add);
    } catch (IOException e) {
      log.info("Exception occurred during reading of file resource");
    }

    return fileLines;
  }

  public List<int[]> getLocationPairsFromFile(String pathToFile) {

    List<int[]> locationIds = new ArrayList<>();

    try (Scanner sc = new Scanner(new File(pathToFile))) {

      while (sc.hasNext()) {
        String nextLine = sc.nextLine();
        String[] locationIdPair = nextLine.split("   ");
        int locationIdA = Integer.parseInt(locationIdPair[0]);
        int locationIdB = Integer.parseInt(locationIdPair[1]);
        int[] pair = {locationIdA, locationIdB};

        locationIds.add(pair);
      }

    } catch (FileNotFoundException e) {
      log.info("Exception occurred during reading of file resource");
    }

    return locationIds;
  }

  public record TaskSolution(String answer) {}
}
