package solutions.common;

import solutions.common.Solution.SolutionAnswer;
import solutions.daySix.GuardGallivant;
import java.util.logging.Logger;

public class Main {

  private static final Logger logger = Logger.getLogger(Main.class.getName());

  public static void main(String[] args) {
    GuardGallivant guardGallivant = new GuardGallivant();
    SolutionAnswer solutionAnswer = guardGallivant.run();
    logger.info(solutionAnswer.answer());
  }
}
