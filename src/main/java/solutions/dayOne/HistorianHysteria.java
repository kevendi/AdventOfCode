package solutions.dayOne;

import solutions.common.Task;

import java.util.List;

public class HistorianHysteria extends Task {
    @Override
    public TaskSolution run() {
        List<int[]> locationPairs = getLocationPairsFromFile("src/main/resources/HistorianList.txt");
        return new TaskSolution("Not An Answer");
    }
}
