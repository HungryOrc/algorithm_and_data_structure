/* Find all pairs of elements in a given array that sum to the pair the given target number. 
Return all the distinct pairs of values.
Assumptions:
The given array is not null and has length of at least 2
The order of the values in the pair does not matter

Examples:
A = {2, 1, 3, 2, 4, 3, 4, 2}, target = 6, return [[2, 4], [3, 3]]  */


public class Solution {
  
  public List<List<Integer>> allPairs(int[] array, int target) {
    List<List<Integer>> result = new ArrayList<>();
    HashSet<Integer> records = new HashSet<>();
    boolean foundHalfTarget = false;
    
    for (int num : array) {
      
      if (!records.contains(num)) {
        if (records.contains(target - num)) {
          List<Integer> pair = new ArrayList<>();
          pair.add(target - num);
          pair.add(num);
          result.add(pair);
        }
        
        records.add(num);
      }
      else {
        // 注意！这里不要用 num == target / 2 ！ 否则会出现 7 / 2 = 3 这样的错误情况
        if (num * 2 == target && foundHalfTarget == false) {
          List<Integer> pair = new ArrayList<>();
          pair.add(num);
          pair.add(num);
          result.add(pair);
          
          foundHalfTarget = true;
        }
      }
    }
    
    return result;
  }
}
