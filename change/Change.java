package change;

import java.util.Arrays;
import java.util.Collections;

public class Change {

  // USE BOTTOM UP ALGORITHM

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
      solutionTable[i] = OptimalBottomUp(solutionTable, denoms, i);
    }
    
    // now that solution table is done, print all the solutions found
    for (int goal : goals) { 
      System.out.println(goal + " cents = " + Utilities.SolutionToString(solutionTable[goal], denoms));
    }
  }

  public static Solution OptimalBottomUp(Solution[] solutionTable, Integer[] denoms, int goal) {
    // if solution already in solution table, just return that
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
      optimalSolution.denoms = bestLowerSolution.denoms.clone();
      optimalSolution.denoms[denomIndexToIncrement]++;

      return optimalSolution;
    }
  }

  // USE MEMOIZATION

  public static void SolveMemoization(Integer[] denoms, Integer[] goals) {
    
    // select largest goal
    int maxGoal = Collections.max(Arrays.asList(goals));

    // initialize empty solution table with size of goal + 1
    Solution[] solutionTable = new Solution[maxGoal + 1];
    
    // loop through each goal, recursively find and print each solution
    for (int goal : goals) {
      Solution solution =  OptimalMemoization(solutionTable, denoms, goal);
      System.out.println(goal + " cents = " + Utilities.SolutionToString(solution, denoms));
    }
  }

  public static Solution OptimalMemoization(Solution[] solutionTable, Integer[] denoms, int goal) {
    // if solution already in solution table, just return that
    if (solutionTable[goal] != null) {
      return solutionTable[goal]; 
    }
    // otherwise, standard recursion base case with zero cost
    else if (goal == 0) {
      Integer[] costZero = new Integer[denoms.length];
      Arrays.fill(costZero, 0);
      return new Solution(0, costZero);
    }
    else {
      // iterate through each lower denomination, check for the lowest solution recursively
      Solution bestLowerSolution = new Solution(Integer.MAX_VALUE);
      int denomIndexToIncrement = 0;
      for (int i = 0; i < denoms.length; i++) {
        int solutionIndex = goal - denoms[i];
        if (solutionIndex >= 0) {
          // find lower solutions recursively. If the solution found is the lowest cost, select it as best.
          Solution lowerSolution = OptimalMemoization(solutionTable, denoms, solutionIndex);
          if (lowerSolution.cost < bestLowerSolution.cost) {
            bestLowerSolution = lowerSolution;
            denomIndexToIncrement = i;
          }
        }
      }

      // Construct new Solution object, increment denomination selected;
      Solution optimalSolution = new Solution(bestLowerSolution.cost + 1);
      optimalSolution.denoms = bestLowerSolution.denoms.clone();
      optimalSolution.denoms[denomIndexToIncrement]++;

      // Save optimal solution in memoization table
      solutionTable[goal] = optimalSolution;

      return optimalSolution;
    }
  }

  // USE NAIVE RECURSIVE ALGORITHM

  public static void SolveRecursive(Integer[] denoms, Integer[] goals) {    
    // loop through each goal, recursively find and print each solution
    for (int goal : goals) {
      Solution solution =  OptimalRecursive(denoms, goal);
      System.out.println(goal + " cents = " + Utilities.SolutionToString(solution, denoms));
    }
  }

  public static Solution OptimalRecursive(Integer[] denoms, int goal) {
    // recursion base case with zero cost
    if (goal == 0) {
      Integer[] costZero = new Integer[denoms.length];
      Arrays.fill(costZero, 0);
      return new Solution(0, costZero);
    }
    else {
      // iterate through each lower denomination, check for the lowest solution recursively
      Solution bestLowerSolution = new Solution(Integer.MAX_VALUE);
      int denomIndexToIncrement = 0;
      for (int i = 0; i < denoms.length; i++) {
        int solutionIndex = goal - denoms[i];
        if (solutionIndex >= 0) {
          // find lower solutions recursively. If the solution found is the lowest cost, select it as best.
          Solution lowerSolution = OptimalRecursive(denoms, solutionIndex);
          if (lowerSolution.cost < bestLowerSolution.cost) {
            bestLowerSolution = lowerSolution;
            denomIndexToIncrement = i;
          }
        }
      }

      // Construct new Solution object, increment denomination selected;
      Solution optimalSolution = new Solution(bestLowerSolution.cost + 1);
      optimalSolution.denoms = bestLowerSolution.denoms.clone();
      optimalSolution.denoms[denomIndexToIncrement]++;

      return optimalSolution;
    }
  }

}