/* Given a list of numbers with duplicate number in it. Find all unique permutations.

Example:
For numbers [1,2,2] the unique permutations are:
[
  [1,2,2],
  [2,1,2],
  [2,2,1]
] */

public class Solution {
    
    // Recursion: 我的土办法。速度较慢
    public List<List<Integer>> permuteUnique(int[] nums) {
        
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        
        if (nums == null) {
            return results;
        }
        else if (nums.length == 0) {
            results.add(new ArrayList<Integer>());
            return results;
        }
        
        Arrays.sort(nums);
        // construct the original records of each integer's occurrence times in nums
        HashMap<Integer,Integer> originalOccurrences = new HashMap<>();
        for (int n : nums) {
            originalOccurrences.put(n, originalOccurrences.getOrDefault(n, 0) + 1);
        }
        
        findPermutations(nums, originalOccurrences, new HashMap<Integer,Integer>(), new ArrayList<Integer>(), results);
        return results;
    } 
    private void findPermutations(int[] nums,
                                  HashMap<Integer,Integer> originalOccurrences,
                                  HashMap<Integer,Integer> curOccurrences, 
                                  ArrayList<Integer> curList,
                                  List<List<Integer>> results) {
        
        if (curList.size() == nums.length) {
            results.add(new ArrayList<>(curList));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            // 把重复扼杀在摇篮里
            if (i == 0 || nums[i] != nums[i - 1]) {
                int n = nums[i];
                // 不能超过这个数在nums里出现过的次数
                if (curOccurrences.getOrDefault(n,0) < originalOccurrences.get(n)) {
                    // add n
                    curList.add(n);
                    curOccurrences.put(n, curOccurrences.getOrDefault(n,0) + 1);
                    
                    // next step for constructing the permutations
                    findPermutations(nums, originalOccurrences, curOccurrences, curList, results);
                    
                    // remove n
                    curList.remove(curList.size() - 1);
                    curOccurrences.put(n, curOccurrences.get(n) - 1);
                }
            }
        }
    }
    
    
    // Recursion: 九章的方法，速度明显比上面的方法快
    // Ref: http://www.jiuzhang.com/solutions/permutations-ii/
    public List<List<Integer>> permuteUnique(int[] nums) {
    
        ArrayList<List<Integer>> results = new ArrayList<List<Integer>>();
    
        if (nums == null) {
            return results;
        }
        else if(nums.length == 0) {
            results.add(new ArrayList<Integer>());
            return results;
        }

        Arrays.sort(nums);
        ArrayList<Integer> list = new ArrayList<Integer>();
        int[] visited = new int[nums.length]; // all zero
     
        helper(results, list, visited, nums);    
        return results;
    }
    private void helper(ArrayList<List<Integer>> results, 
                        ArrayList<Integer> list, int[] visited, int[] nums) {
        
        if(list.size() == nums.length) {
            results.add(new ArrayList<Integer>(list));
            return;
        }
        
        for(int i = 0; i < nums.length; i++) {
            if (visited[i] == 1 || 
                (i > 0 && nums[i] == nums[i - 1] && visited[i-1] == 0)) {
                continue;
            }
            /*
            上面的判断主要是为了去除重复元素影响。
            比如，给出一个排好序的数组，[1,2,2]，那么第一个2和第二2如果在结果中互换位置，
            我们也认为是同一种方案，所以我们强制要求相同的数字，原来排在前面的，在结果
            当中也应该排在前面，这样就保证了唯一性。当前面的2还没有使用的时候，就
            不应该让后面的2使用。
            */
            list.add(nums[i]);
            visited[i] = 1;
            
            helper(results, list, visited, nums);
            
            list.remove(list.size() - 1);
            visited[i] = 0;
        }
    }
    
    
}
