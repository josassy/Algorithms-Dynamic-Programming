/**
  File:    Solution.java
  @author: Josiah R Lansford & Rufus Roby Mathew
  @description: Represent a Solution to be stored in a solution table.
                Counts the total cost, and the coins in each denomination.
*/

package change;

public class Solution {
  
  // count the total cost
  int cost;

  // count how many coins in each denomination
  Integer[] denoms;

  public Solution(int cost, Integer[] denoms) {
    this.cost = cost;
    this.denoms = denoms;
  }

  public Solution(int cost) {
    this.cost = cost;
  }
}
