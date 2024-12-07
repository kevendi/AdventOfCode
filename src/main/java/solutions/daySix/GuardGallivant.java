package solutions.daySix;

import solutions.common.Solution;
import java.awt.Point;
import java.util.logging.Logger;

public class GuardGallivant extends Solution {

  private int currentX = 0;
  private int currentY = 0;
  private int visited = 1;

  private static final char GUARD_UP = '^';
  private static final char GUARD_RIGHT = '>';
  private static final char GUARD_LEFT = '<';
  private static final char GUARD_DOWN = 'v';
  private static final char OBSTACLE = '#';

  private static final Logger logger = Logger.getLogger(GuardGallivant.class.getName());

  public SolutionAnswer run() {
    char[][] labGrid = getFileAs2dCharArray("src/main/resources/LabGrid.txt");
    Point startingPoint = getStartingPoint(labGrid);
    this.currentX = startingPoint.x;
    this.currentY = startingPoint.y;

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

    if (currentX == 0) {
      return visited;
    }

    if (labGrid[currentX - 1][currentY] == OBSTACLE) {
      logger.info("Guard heading right");
      headRight(labGrid);
    }

    if (currentY == labGrid[0].length - 1) {
      return visited;
    }

    if (labGrid[currentX][currentY + 1] == OBSTACLE) {
      logger.info("Guard heading down");
      headDown(labGrid);
    }

    if (currentX == labGrid[0].length - 1) {
      return visited;
    }

    if (labGrid[currentX + 1][currentY] == OBSTACLE) {
      logger.info("Guard heading left");
      headLeft(labGrid);
    }

    if (currentY == 0) {
      labGrid[currentX][currentY] = 'D';
      return visited;
    }

    return startCycleAndReturnPositionCount(labGrid);
  }

  private void headUp(char[][] labGrid) {
    do {

      labGrid[currentX][currentY] = 'D';
      labGrid[currentX - 1][currentY] = GUARD_UP;
      currentX--;

      if (currentX == 0) {
        break;
      }

      if (labGrid[currentX - 1][currentY] != 'D') {
        visited++;
      }

    } while (labGrid[currentX - 1][currentY] == '.' || labGrid[currentX - 1][currentY] == 'D');

    logPositionsAndVisitedCount(currentX, currentY);
  }

  private void headRight(char[][] labGrid) {
    do {

      labGrid[currentX][currentY] = 'D';
      labGrid[currentX][currentY + 1] = GUARD_RIGHT;
      currentY++;

      if (currentY == labGrid[0].length - 1) {
        break;
      }

      if (labGrid[currentX][currentY + 1] != 'D') {
        visited++;
      }

    } while (labGrid[currentX][currentY + 1] == '.' || labGrid[currentX][currentY + 1] == 'D');

    logPositionsAndVisitedCount(currentX, currentY);
  }

  private void headDown(char[][] labGrid) {
    do {
      labGrid[currentX][currentY] = 'D';
      labGrid[currentX + 1][currentY] = GUARD_DOWN;
      currentX++;

      if (currentX == labGrid[0].length - 1) {
        break;
      }

      if (labGrid[currentX + 1][currentY] != 'D') {
        visited++;
      }

    } while (labGrid[currentX + 1][currentY] == '.' || labGrid[currentX + 1][currentY] == 'D');

    logPositionsAndVisitedCount(currentX, currentY);
  }

  private void headLeft(char[][] labGrid) {
    do {

      labGrid[currentX][currentY] = 'D';
      labGrid[currentX][currentY - 1] = GUARD_LEFT;
      currentY--;

      if (currentY == 0 ) {
        break;
      }

      if (labGrid[currentX][currentY - 1] != 'D') {
        visited++;
      }

    } while (labGrid[currentX][currentY - 1] == '.' || labGrid[currentX][currentY - 1] == 'D');

    logPositionsAndVisitedCount(currentX, currentY);
  }

  public void logPositionsAndVisitedCount(int currentX, int currentY) {
    logger.info(String.valueOf(currentX));
    logger.info(String.valueOf(currentY));
  }
}
