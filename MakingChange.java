package proj2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class MakingChange {

  public static void main(String[] args) {

    
    // Reading the contents of the file
    Scanner in;
    try {
      in = new Scanner(new File("input.txt"));
    }
    catch (FileNotFoundException e) {
      System.out.println("file not found");
      return;
    }
    int n = in.nextInt(); //number of denominations

    //Denomations Array
    Integer[] denomArray = new Integer[n]; 
    for (int i = 0; i < n; i++) {
      denomArray[i] = in.nextInt();
    }

    //getting in the number of total goals
    int k = in.nextInt(); 
    
    // Array of goals to solve for
    Integer[] goalArray = new Integer[k]; 
    for (int i = 0; i < k; i++) { 
      goalArray[i] = in.nextInt(); //getting in all the goals  from the list. 
    }

    SolveBottomUp(denomArray, goalArray);
  }

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
      System.out.print(goal + " cents = " + SolutionToString(solutionTable[goal], denoms));
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

  // print a given solution in the specified format
  public static String SolutionToString(Solution s, Integer[] denoms) {
    String result = "";
    for (int i = 0; i < denoms.length; i++) {
      // add denom and corresponding count
      result += denoms[i] + ":" + s.denoms[i];
      
      // if more denoms to print, add a space
      if (i < denoms.length - 1) {
        result += " ";
      }
    }
    return result;
  }
}