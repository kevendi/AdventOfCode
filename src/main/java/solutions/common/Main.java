package solutions.common;

import solutions.common.Task.TaskSolution;
import solutions.dayOne.HistorianHysteria;
import solutions.daySix.GuardGallivant;

import java.util.logging.Logger;

public class Main {

  private static final Logger logger = Logger.getLogger(Main.class.getName());

  private static final String DAY_ONE_FILE = "src/main/resources/HistorianList.txt";
  private static final String DAY_SIX_FILE = "src/main/resources/GuardGallivant.txt";

  public static void main(String[] args) {

    // TODO - 18/12/24 - Read in task name via CMD line
    String taskNameToRun = "HistorianHysteria";

    TaskSolution solutionAnswer = getSolutionAnswerToTask(taskNameToRun);
    logger.info(solutionAnswer.answer());
  }

  private static TaskSolution getSolutionAnswerToTask(String taskName) {
      return switch (taskName) {
          case "GuardGallivant" -> {
              GuardGallivant guardGallivant = new GuardGallivant();
              yield guardGallivant.run(DAY_SIX_FILE);
          }
          case "HistorianHysteria" -> {
              HistorianHysteria historianHysteria = new HistorianHysteria();
              yield historianHysteria.run(DAY_ONE_FILE);
          }
          default -> throw new IllegalStateException("Unexpected task name supplied: " + taskName);
      };
  }
}
