/* A periodic sequence S is defined as follows:
S[0], A, B and M are all given positive integers;
S[i] for i > 0 is equal to (A * S[i - 1] + B) mod M.
integer S0: A positive integer representing S[0]. Constraints: 1 ≤ S0 ≤ 100.
integer A: Constraints: 2 ≤ A ≤ 100.
integer B: Constraints: 2 ≤ B ≤ 100.
integer M: Constraints: 5 ≤ M ≤ 100.
Find the period of S, i.e. the smallest integer T such that for each i > k (for some integer k): S[i] = S[i + T].

Example
For S0 = 11, A = 2, B = 6 and M = 12, the output should be
periodicSequence(S0, A, B, M) = 2.
The sequence would look like this: 11, 4, 2, 10, 2, 10, 2, 10, 2, 10....
For S0 = 1, A = 2, B = 3 and M = 5, the output should be
periodicSequence(S0, A, B, M) = 4.
The sequence would look like this: 1, 0, 3, 4, 1, 0, 3, 4, 1, 0, 3, 4.... */

// 我的方法
// 一开始可能有一段混乱期。但关键在于：每个数都要 mod M，所以混乱期的长度不会超过M，最终到达稳态以后的周期也不会超过M
// 所以我们先搞M次，pass掉混乱期
// 然后再搞M次，以充分记录下（必然超过）一个周期内所有可能出现的数值的个数
int periodicSequence(int S0, int A, int B, int M) {
    
    int Si = S0;
    for (int i = 1; i <= M; i++) {
        Si = calcNext(Si, A, B, M);
    }
    
    HashSet<Integer> allPossibleValuesInStableLoop = new HashSet<>();
    for (int i = 1; i <= M; i++) {
        Si = calcNext(Si, A, B, M);
        allPossibleValuesInStableLoop.add(Si);
    }
    return allPossibleValuesInStableLoop.size();
    
}

private int calcNext(int curNum, int A, int B, int M) {
    return ((A * curNum + B) % M);
}
