package solutions.daySix;

import solutions.common.Solution;
import java.awt.Point;

public class GuardGallivant extends Solution {

  public SolutionAnswer run() {
    char[][] labGrid = getFileAs2dCharArray("src/main/resources/LabGrid.txt");
    Point startingPoint = getStartingPoint(labGrid);
    // int distinctPositionCount = getDistinctPositionCount(startingPoint, labGrid);
    return new SolutionAnswer(startingPoint.toString());
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

  public int getDistinctPositionCount(Point startingPoint, char[][] labGrid) {
    int positionCount = 0;
  }
}
