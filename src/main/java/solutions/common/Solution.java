package solutions.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

public abstract class Solution {

  private final Logger log = Logger.getLogger(Solution.class.getName());

  public abstract SolutionAnswer run();

  public char[][] getFileAs2dCharArray(String pathToFile) {
    List<String> fileLines = new ArrayList<>();
    Path filePath = Paths.get(pathToFile);

    try (Stream<String> lines = Files.lines(filePath)) {
      lines.forEach(fileLines::add);
    } catch (IOException e) {
      log.info("Exception occurred reading of file resource");
    }

    return fileLines.stream()
        .map(String::toCharArray)
        .toArray(char[][]::new);
  }

  public record SolutionAnswer(String answer) {}
}
