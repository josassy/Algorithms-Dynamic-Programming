package change;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
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
    BottomUp.SolveBottomUp(denomArray, goalArray);
  }
}