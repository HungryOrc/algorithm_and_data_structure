/* Find all pairs of elements in a given array that sum to the given target number. Return all the pairs of indices.
Assumptions: The given array is not null and has length of at least 2.

Examples:
A = {1, 3, 2, 4}, target = 5, return [[0, 3], [1, 2]]
A = {1, 2, 2, 4}, target = 6, return [[1, 3], [2, 3]]  */


public class Solution {
  
  public List<List<Integer>> allPairs(int[] array, int target) {
    List<List<Integer>> result = new ArrayList<>();
    HashMap<Integer, List<Integer>> records = new HashMap<>();
    
    for (int i = 0; i < array.length; i++) {
      
      List<Integer> matchedIndexes = records.get(target - array[i]);
      if (matchedIndexes != null) {
        for (int index : matchedIndexes) {
          List<Integer> pair = new ArrayList<>();
          pair.add(index);
          pair.add(i);
          result.add(pair);
        }
      }
      
      List<Integer> sameValueIndexes = records.getOrDefault(array[i], new ArrayList<Integer>());
      sameValueIndexes.add(i);
      records.put(array[i], sameValueIndexes);
    }
    
    return result;
  }
}
