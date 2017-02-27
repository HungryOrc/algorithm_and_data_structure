// 数组里的元素不重复，元素固定为1到9
// 在一个组合里，每个元素最多只能出现一次
// 在一个组合里，总的元素个数限定为 k 个
// 在一个组合里，每个元素的出现顺序如果改变，也还算是同一个组合

/* Find all possible combinations of k numbers that add up to a number n, 
given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.
即一个组合里不可出现重复的数字

Example 1:
Input: k = 3, n = 7
Output: [[1,2,4]]
Example 2:
Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]

很巧妙的方法，把所有组合列出来。
列的顺序是，举例 k = 3 (找3个不重复的数) 的话：
第一位1，第二位2，第三位3-9；第一位1，第二位3，第三位4-9...
第一位2，第二位3，第三位4-9；...
...
Ref: https://discuss.leetcode.com/topic/37962/fast-easy-java-code-with-explanation

Used backtracking to solve this.
Build an array to apply to "subset" template. Each time we add an element to the "list", 
for the next step, target= target - num[i]. 
Since we have already added one element, for the next step, we can only add k-1 elements. 
Since no duplicated elements accept, 
for the next loop, the "start" should point to the next index of current index. 
The list.remove(list.size() - 1) here means, we need to change the element here. 
I know it is hard to understand it, let me give you an example.

When k=3, n=9 (找3个不重复的数，和要为9), my answer works like this:
[1]->[1,2]->[1,2,3]. Since now sum is not 9, no more backtracking, so after list.remove(list.size() - 1), it is [1,2]. 
Then next follows [1,2,4], sum is not 9, repeat process above untill [1,2,6]. 
When go to next backtracking, the list will be added to result, and for this list, no more backtracking.
Now we can go back to a previous backtracking, which is [1,3]->[1,3,4], fail. [1,4,]->[1,4,5], fail. And so one.
So the point of list.remove(list.size() - 1) is, after each "fail" or "success", 
since we don't need to do further attempts given such a condition, we delete the last element, 
and then end current backtracking. Next step is, add the next element to the deleted index, go on attempting. */

public class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) 
    {
        List<List<Integer>> result = new ArrayList<>();
        combination(result, new ArrayList<Integer>(), 1, k, n);
        return result;
    }
    private void combination(List<List<Integer>> result, List<Integer> curAL,
        int curStart, int remainComponents, int remainSum)
    {
        if (remainComponents == 0 && remainSum == 0) {
            List<Integer> validAL = new ArrayList<Integer>(curAL);
            result.add(validAL);
            return;
        }
        for (int i = curStart; i <= 9; i++) {
            curAL.add(i);
            combination(result, curAL, i + 1, remainComponents - 1, remainSum - i);
            curAL.remove(curAL.size() - 1);
        }
    }
}
