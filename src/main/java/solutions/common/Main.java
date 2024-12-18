package solutions.common;

import solutions.common.Solution.SolutionAnswer;
import solutions.dayOne.HistorianHysteria;
import solutions.daySix.GuardGallivant;

import java.util.logging.Logger;

public class Main {

  private static final Logger logger = Logger.getLogger(Main.class.getName());

  public static void main(String[] args) {

    // TODO - 18/12/24 - Read in task name via CMD line
    String taskNameToRun = "HistorianHysteria";
    SolutionAnswer solutionAnswer = getSolutionAnswerToTask(taskNameToRun);
    logger.info(solutionAnswer.answer());
  }

  private static SolutionAnswer getSolutionAnswerToTask(String taskName) {
      return switch (taskName) {
          case "GuardGallivant" -> {
              GuardGallivant guardGallivant = new GuardGallivant();
              yield guardGallivant.run();
          }
          case "HistorianHysteria" -> {
              HistorianHysteria historianHysteria = new HistorianHysteria();
              yield historianHysteria.run();
          }
          default -> throw new IllegalStateException("Unexpected task name supplied: " + taskName);
      };
  }





}
