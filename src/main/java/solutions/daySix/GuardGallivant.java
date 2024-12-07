package solutions.daySix;

import solutions.common.Solution;
import java.awt.Point;
import java.util.logging.Logger;

public class GuardGallivant extends Solution {

  private static final Logger logger = Logger.getLogger(GuardGallivant.class.getName());

  public SolutionAnswer run() {
    char[][] labGrid = getFileAs2dCharArray("src/main/resources/LabGrid.txt");
    Point startingPoint = getStartingPoint(labGrid);
    int visitedPositions = getVisitedPositions(startingPoint, labGrid);
    return new SolutionAnswer(String.valueOf(visitedPositions));
  }

  public Point getStartingPoint(char[][] labGrid) {

    int rowCoordinate = 0;

    for (char[] labRow : labGrid) {
      rowCoordinate++;
      int columnCoordinate = 0;
      for (char labSquare : labRow) {
        columnCoordinate++;
        if (labSquare == '^') {
          return new Point(rowCoordinate, columnCoordinate);
        }
      }
    }

    return new Point(0, 0);
  }

  public int getVisitedPositions(Point startingPoint, char[][] labGrid) {
    int visited = 1;

    int currentX = startingPoint.x;
    int currentY = startingPoint.y;

    char guardUp = labGrid[currentX][currentY];
    char guardDown = 'v';
    char guardRight = '>';
    char guardLeft = '<';

    char obstacle = '#';

    logPositionsAndVisitedCount(currentX, currentY, visited);
    logger.info("Guard heading up");

    do {
      // keep heading up while next char up is a valid space
      labGrid[currentX][currentY + 1] = guardUp;
      currentY++;
      visited++;
    } while (labGrid[currentX][currentY + 1] == '.');

    logPositionsAndVisitedCount(currentX, currentY, visited);
    logger.info("Guard heading right");

    if (labGrid[currentX][currentY + 1] == obstacle) {
      // head right if space up is an obstacle
      do {
        // head right as long as next space right is a valid space
        labGrid[currentX + 1][currentY] = guardRight;
        currentX++;
        visited++;
      } while (labGrid[currentX + 1][currentY] == '.');
    } else {
      return visited;
    }

    logPositionsAndVisitedCount(currentX, currentY, visited);
    logger.info("Guard heading down");

    if (labGrid[currentX + 1][currentY] == obstacle) {
      // head down if space right is an obstacle
      do {
        // head down as long as next space down is a valid space
        labGrid[currentX][currentY - 1] = guardDown;
        currentY--;
        visited++;
      } while (labGrid[currentX][currentY - 1] == '.');
    } else {
      return visited;
    }

    logPositionsAndVisitedCount(currentX, currentY, visited);
    logger.info("Guard heading left");

    if (labGrid[currentX][currentY - 1] == obstacle) {
      // if next space down is an obstacle head left
      do {
        // head left as long as next space left is a valid space
        labGrid[currentX - 1][currentY] = guardLeft;
        currentX--;
        visited++;
      } while (labGrid[currentX - 1][currentY] == '.');
    } else {
      return visited;
    }
    logPositionsAndVisitedCount(currentX, currentY, visited);
    return visited;
  }

  public void logPositionsAndVisitedCount(int currentX, int currentY, int visited) {
    logger.info(String.valueOf(currentX));
    logger.info(String.valueOf(currentY));
    logger.info(String.valueOf(visited));
  }
}
