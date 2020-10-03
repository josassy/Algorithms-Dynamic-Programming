package proj2;

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
