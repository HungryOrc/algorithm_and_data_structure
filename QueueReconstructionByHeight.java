/* Suppose you have a random list of people standing in a queue. 
Each person is described by a pair of integers (h, k), where h is the height of the person 
and k is the number of people in front of this person who have a height greater than or equal to h. 
Write an algorithm to reconstruct the queue.
Note: The number of people is less than 1,100.

Example:
Input:
[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
Output:
[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]] */

// 非常巧妙的思路！！！
// Ref: https://discuss.leetcode.com/topic/60394/easy-concept-with-python-c-java-solution/5
/* Pick out tallest group of people and sort them in a subarray (S). 
Since there's no other groups of people taller than them, therefore each guy's index will be just as same as his k value.
For 2nd tallest group (and the rest), insert each one of them into (S) by k value. So on and so forth.
E.g.
input: [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
subarray after step 1: [[7,0], [7,1]]
subarray after step 2: [[7,0], [6,1], [7,1]]
... */
public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        //pick up the tallest guy first
        //when insert the next tall guy, just need to insert him into kth position
        //repeat until all people are inserted into list
        Arrays.sort(people,new Comparator<int[]>(){
           @Override
           public int compare(int[] o1, int[] o2){
               // 前面的height从大到小排，后面的k从小到大排
               return o1[0]!=o2[0]?-o1[0]+o2[0]:o1[1]-o2[1];
           }
        });
        List<int[]> res = new LinkedList<>();
        for(int[] cur : people){
            res.add(cur[1],cur);       
        }
        return res.toArray(new int[people.length][]);
    }
}
