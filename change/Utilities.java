package change;

public class Utilities {
  // print a given solution in the specified format
  public static String SolutionToString(Solution s, Integer[] denoms) {
    String result = "";
    // loop from highest to lowest
    for (int i = denoms.length - 1; i >= 0; i--) {
      // add denom and corresponding count, if > 0
      if (s.denoms[i] > 0) {

        // don't print a space if the string is empty
        if (result != "") {
          result += " ";
        }

        result += denoms[i] + ":" + s.denoms[i];
      }
    }
    return result;
  }
}