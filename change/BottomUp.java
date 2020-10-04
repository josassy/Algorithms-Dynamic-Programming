package change;

import java.util.Arrays;
import java.util.Collections;

public class BottomUp {

  public static void SolveBottomUp(Integer[] denoms, Integer[] goals) {
    
    // select largest goal
    int maxGoal = Collections.max(Arrays.asList(goals));

    // initialize empty solution table with size of goal + 1
    Solution[] solutionTable = new Solution[maxGoal + 1];

    // initialize first solution entry to 0
    Integer[] costZero = new Integer[denoms.length];
    Arrays.fill(costZero, 0);
    solutionTable[0] = new Solution(0, costZero);

    // loop up to max goal from bottom up, building table
    for (int i = 1; i <= maxGoal; i++) {
      solutionTable[i] = Optimal(solutionTable, denoms, i);
    }
    
    // now that solution table is done, print all the solutions found
    for (int goal : goals) { 
      System.out.print(goal + " cents = " + Utilities.SolutionToString(solutionTable[goal], denoms));
    }
  }

  public static Solution Optimal(Solution[] solutionTable, Integer[] denoms, int goal) {
    if (solutionTable[goal] != null) {
      return solutionTable[goal]; 
    }
    else {
      // iterate through each lower denomination, check for the lowest solution
      Solution bestLowerSolution = new Solution(Integer.MAX_VALUE);
      int denomIndexToIncrement = 0;
      for (int i = 0; i < denoms.length; i++) {
        int solutionIndex = goal - denoms[i];
        if (solutionIndex >= 0 && solutionTable[solutionIndex].cost < bestLowerSolution.cost) {
          bestLowerSolution = solutionTable[solutionIndex];
          denomIndexToIncrement = i;
        }
      }

      // Construct new Solution object, increment denomination selected;
      Solution optimalSolution = new Solution(bestLowerSolution.cost + 1);
      optimalSolution.denoms = bestLowerSolution.denoms;
      optimalSolution.denoms[denomIndexToIncrement]++;

      return optimalSolution;
    }
  }
}