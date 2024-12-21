package solutions;

import solutions.common.Task;
import java.util.List;

public class RedNosedReports extends Task {
  @Override
  public TaskSolution run(String fileName) {
    List<int[]> reports = getFileAs2dIntArray(fileName);
    int ascendingSafeReports = (int) reports.stream().filter(this::ascendingReportIsSafe).count();
    int descendingSafeReports = (int) reports.stream().filter(this::descendingReportIsSafe).count();

    return new TaskSolution(String.valueOf(ascendingSafeReports + descendingSafeReports));
  }

  private boolean ascendingReportIsSafe(int[] report) {
    boolean safe = false;

    for (int level = 0; level < report.length - 1; level++) {
     if (report[level] < report[level + 1] && graduallyIncreasingDecreasing(report[level], report[level + 1])) {
       safe = true;
      } else {
       safe = false;
       return safe;
     }
    }

    return safe;
  }

  private boolean descendingReportIsSafe(int[] report) {
    boolean safe = false;

    for (int level = 0; level < report.length - 1; level++) {
      if (report[level] > report[level + 1] && graduallyIncreasingDecreasing(report[level], report[level + 1])) {
        safe = true;
      } else {
        safe = false;
        return safe;
      }
    }

    return safe;
  }

  private boolean graduallyIncreasingDecreasing(int a, int b) {
    return Math.abs(a - b) >= 1 && Math.abs(a - b) <= 3;
  }
}
