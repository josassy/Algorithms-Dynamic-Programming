/**
  File:    Main.java
  @author: Josiah R Lansford & Rufus Roby Mathew
  @description: Accept input for change calculation and call each of the desired algorithms.  
*/

package change;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    // Reading the contents of the file
    Scanner in = new Scanner(System.in);
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

    Change.SolveBottomUp(denomArray, goalArray);
    Change.SolveMemoization(denomArray, goalArray);
    Change.SolveRecursive(denomArray, goalArray);

    in.close();
  }
}