package solutions.common;

import solutions.RedNosedReports;
import solutions.common.Task.TaskSolution;
import solutions.HistorianHysteria;
import solutions.GuardGallivant;

import java.util.logging.Logger;

public class Main {

  private static final Logger logger = Logger.getLogger(Main.class.getName());

  private static final String DAY_ONE_FILE = "src/main/resources/HistorianList.txt";
  private static final String DAY_TWO_FILE = "src/main/resources/RedNosedReports.txt";
  private static final String DAY_SIX_FILE = "src/main/resources/GuardGallivant.txt";

  public static void main(String[] args) {

    String taskNameToRun = "RedNosedReports";

    TaskSolution solutionAnswer = getSolutionAnswerToTask(taskNameToRun);
    logger.info(solutionAnswer.answer());
  }

  private static TaskSolution getSolutionAnswerToTask(String taskName) {
      return switch (taskName) {
        case "HistorianHysteria" -> {
          HistorianHysteria historianHysteria = new HistorianHysteria();
          yield historianHysteria.run(DAY_ONE_FILE);
        }
        case "RedNosedReports" -> {
          RedNosedReports redNosedReports = new RedNosedReports();
          yield redNosedReports.run(DAY_TWO_FILE);
        }
        case "GuardGallivant" -> {
            GuardGallivant guardGallivant = new GuardGallivant();
            yield guardGallivant.run(DAY_SIX_FILE);
        }
        default -> throw new IllegalStateException("Unexpected task name supplied: " + taskName);
      };
  }
}
