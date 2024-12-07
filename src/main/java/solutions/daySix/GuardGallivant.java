package solutions.daySix;

import solutions.common.Solution;
import java.awt.Point;
import java.util.logging.Logger;

public class GuardGallivant extends Solution {

  private int guardRow = 0;
  private int guardColumm = 0;
  private int visited = 1;

  private static final char GUARD_UP = '^';
  private static final char GUARD_RIGHT = '>';
  private static final char GUARD_LEFT = '<';
  private static final char GUARD_DOWN = 'v';
  private static final char OBSTACLE = '#';
  private static final char DISCOVERED = 'D';

  private static final Logger logger = Logger.getLogger(GuardGallivant.class.getName());

  public SolutionAnswer run() {
    char[][] labGrid = getFileAs2dCharArray("src/main/resources/LabGrid.txt");
    Point startingPoint = getStartingPoint(labGrid);
    this.guardRow = startingPoint.x;
    this.guardColumm = startingPoint.y;

    int visitedCount = startCycleAndReturnPositionCount(labGrid);

    return new SolutionAnswer(String.valueOf(visitedCount));
  }

  public Point getStartingPoint(char[][] labGrid) {

    int rowCoordinate = -1;

    for (char[] labRow : labGrid) {
      rowCoordinate++;
      int columnCoordinate = -1;
      for (char labSquare : labRow) {
        columnCoordinate++;
        if (labSquare == '^') {
          return new Point(rowCoordinate, columnCoordinate);
        }
      }
    }

    return new Point(0, 0);
  }

  private int startCycleAndReturnPositionCount(char[][] labGrid) {
    logger.info("Guard starting sweep");
    headUp(labGrid);

    if (guardRow == 0) {
      return visited;
    }

    if (labGrid[guardRow - 1][guardColumm] == OBSTACLE) {
      logger.info("Guard heading right");
      headRight(labGrid);
    }

    if (guardColumm == labGrid[0].length - 1) {
      return visited;
    }

    if (labGrid[guardRow][guardColumm + 1] == OBSTACLE) {
      logger.info("Guard heading down");
      headDown(labGrid);
    }

    if (guardRow == labGrid[0].length - 1) {
      return visited;
    }

    if (labGrid[guardRow + 1][guardColumm] == OBSTACLE) {
      logger.info("Guard heading left");
      headLeft(labGrid);
    }

    if (guardColumm == 0) {
      labGrid[guardRow][guardColumm] = DISCOVERED;
      return visited;
    }

    return startCycleAndReturnPositionCount(labGrid);
  }

  private void headUp(char[][] labGrid) {
    do {
      labGrid[guardRow][guardColumm] = DISCOVERED;

      if (labGrid[guardRow - 1][guardColumm] != DISCOVERED) {
        visited++;
      }

      labGrid[guardRow - 1][guardColumm] = GUARD_UP;
      guardRow--;

      if (guardRow == 0) {
        break;
      }

    } while (labGrid[guardRow - 1][guardColumm] == '.' || labGrid[guardRow - 1][guardColumm] == DISCOVERED);

    logPositionsAndVisitedCount(guardRow, guardColumm);
  }

  private void headRight(char[][] labGrid) {
    do {
      labGrid[guardRow][guardColumm] = DISCOVERED;

      if (labGrid[guardRow][guardColumm + 1] != DISCOVERED) {
        visited++;
      }

      labGrid[guardRow][guardColumm + 1] = GUARD_RIGHT;
      guardColumm++;

      if (guardColumm == labGrid[0].length - 1) {
        break;
      }
    } while (labGrid[guardRow][guardColumm + 1] == '.' || labGrid[guardRow][guardColumm + 1] == DISCOVERED);

    logPositionsAndVisitedCount(guardRow, guardColumm);
  }

  private void headDown(char[][] labGrid) {
    do {
      labGrid[guardRow][guardColumm] = DISCOVERED;

      if (labGrid[guardRow + 1][guardColumm] != DISCOVERED) {
        visited++;
      }

      labGrid[guardRow + 1][guardColumm] = GUARD_DOWN;
      guardRow++;

      if (guardRow == labGrid[0].length - 1) {
        break;
      }
    } while (labGrid[guardRow + 1][guardColumm] == '.' || labGrid[guardRow + 1][guardColumm] == DISCOVERED);

    logPositionsAndVisitedCount(guardRow, guardColumm);
  }

  private void headLeft(char[][] labGrid) {
    do {
      labGrid[guardRow][guardColumm] = DISCOVERED;

      if (labGrid[guardRow][guardColumm - 1] != DISCOVERED) {
        visited++;
      }

      labGrid[guardRow][guardColumm - 1] = GUARD_LEFT;
      guardColumm--;

      if (guardColumm == 0 ) {
        break;
      }
    } while (labGrid[guardRow][guardColumm - 1] == '.' || labGrid[guardRow][guardColumm - 1] == DISCOVERED);

    logPositionsAndVisitedCount(guardRow, guardColumm);
  }

  public void logPositionsAndVisitedCount(int guardRow, int currentY) {
    logger.info(String.valueOf(guardRow));
    logger.info(String.valueOf(currentY));
  }
}
