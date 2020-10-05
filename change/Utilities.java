package change;

public class Utilities {
  // print a given solution in the specified format
  public static String SolutionToString(Solution s, Integer[] denoms) {
    String result = "";
    for (int i = 0; i < denoms.length; i++) {
      // add denom and corresponding count, if > 0
      if (s.denoms[i] > 0) {
        result += denoms[i] + ":" + s.denoms[i];
        
        // if more denoms to print, add a space
        if (i < denoms.length - 1) {
          result += " ";
        }
      }
    }
    return result;
  }
}