package solutions;

import solutions.common.Task;

import java.util.List;
import java.util.stream.IntStream;

public class HistorianHysteria extends Task {

  @Override
  public TaskSolution run(String fileName) {

    List<int[]> locationPairs = getLocationPairsFromFile(fileName);

    List<Integer> sortedA = locationPairs.stream().map(lp -> lp[0]).sorted().toList();
    List<Integer> sortedB = locationPairs.stream().map(lp -> lp[1]).sorted().toList();

    int totalDistance = IntStream.range(0, locationPairs.size())
        .map(id -> getDistance(sortedA.get(id), sortedB.get(id)))
        .sum();

    int similarityScore = 0;

    for (int a = 0; a < locationPairs.size(); a++) {
      int count = 0;
      int locationA = sortedA.get(a);

      for (int b = 0; b < locationPairs.size(); b++) {
        if (locationA == sortedB.get(b)) {
          count++;
        }
      }

      similarityScore = similarityScore + (locationA * count);
    }

    return new TaskSolution("Distance: " + totalDistance + " Similarity Score: " + similarityScore);
  }

  private int getDistance(int locationA, int locationB) {
    return Math.abs(locationA - locationB);
  }
}
