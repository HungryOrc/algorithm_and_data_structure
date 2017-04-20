/* Given two strings, write a method to decide if one is a permutation of the other.
Try to finish it in O(n) time.

Example
abcd is a permutation of bcad, but abbe is not a permutation of abe */

public class Solution {

    public boolean stringPermutation(String A, String B) {
        
        int[] numOfShownUps = new int[300]; // ASCII table 256个字符
        
        for (char c : A.toCharArray()) {
            // 注意！c什么都不用减！就c自己本色出席就好！！！
            // 每个ASCII字符都有自己内禀的数字序号！！！
            // 这样就不用愁被减去的排名第一的字符应该是谁了
            numOfShownUps[c] ++;
        }
        for (char c : B.toCharArray()) {
            numOfShownUps[c] --;
        }
        
        for (int n : numOfShownUps) {
            if (n != 0) {
                return false;
            }
        }
        return true;
    }
}
