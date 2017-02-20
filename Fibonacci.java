/* Find the Nth number in Fibonacci sequence.

A Fibonacci sequence is defined as follow:
The first two numbers are 0 and 1.
The i th number is the sum of i-1 th number and i-2 th number.
The first ten numbers in Fibonacci sequence is:
0, 1, 1, 2, 3, 5, 8, 13, 21, 34 ...
Notice: The Nth fibonacci number won't exceed the max value of signed 32-bit integer in the test cases.

Example
Given 1, return 0
Given 2, return 1
Given 10, return 34 */

class Solution {
    
    // 方法1: 记忆化。Dynamic Programming (Bottom Up)
    // Time: O(n), Space: O(n)
    public int fibonacci(int n) {
        if (n <= 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        }
        
        int[] fibo = new int[n + 1]; // 这个数组就是记忆化的载体！！
        for (int i = 0; i <= n; i++) {
            fibo[i] = -1;
        }
        fibo[0] = 0;
        fibo[1] = 0;
        fibo[2] = 1;
        
        return findFibo(n, fibo);
    }
    private int findFibo(int n, int[] fibo) {
        if (fibo[n] != -1) {
            return fibo[n];
        } else {
            fibo[n] = findFibo(n - 1, fibo) + findFibo(n - 2, fibo);
            return fibo[n];
        }
    }
    
    
    // 方法2: 逐步替换
    // Time: O(n), Space: O(1)
    public int fibonacci(int n) {
        if (n <= 1) {
            return 0;
        } else if (n == 2) {
            return 1;
        }
        
        int x1 = 0, x2 = 1, x3 = 1;
        for (int i = 3; i <= n; i++) {
            x3 = x1 + x2;
            x1= x2;
            x2 = x3;
        }
        return x3;
    }
}
