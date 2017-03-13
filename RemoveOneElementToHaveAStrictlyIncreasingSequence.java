/* Given a sequence of integers as an array, 
determine whether it is possible to obtain a strictly increasing sequence by removing no more than one element from the array.
严格大于的意思是，必须 >。如果 >= 是不可以的。
2 ≤ sequence.length ≤ 105, -105 ≤ sequence[i] ≤ 105.

Examples:
For sequence = [1, 3, 2], return true;
For sequence = [1, 3, 2, 1], return false;
For sequence = [1, 2, 1, 2], return false;
For sequence = [10, 1, 2, 3, 4, 5], return true;
For sequence = [1, 2, 3, 4, 3, 6], return true (remove the last "3");
For sequence = [1, 2, 3, 4, 99, 5, 6], return true; */

// 我的方法
// 从上面的各个例子可以看出，解法有时候是应该去掉前面那个大的数，有时候是应该去掉后面那个小的数
// 如果去掉了前面那个大的数，结果再往前一位的数还是比后面那个小的数要大，则就不能采用此法；
// 如果去掉了后面那个小的数，结果再往后一位的数还是比前面那个大的数要小，则就不能采用此法；
// 如果在一个位置上，上面两个方法都无法采用，则证明光去掉一个数是不够的，还得至少再去掉一个数，那么本题就可return false了。
//
// 如果再往前或者再往后，就不追溯了么？别担心。还有一个总的违规计数。如果总的违规计数 > 1，也return false，这就考虑到了
// 相隔几位出现不止一次的违规的情况
public boolean almostIncreasingSequence(int[] sequence) {
    int countOfCurBiggerThanNext = 0;
    
    for (int i = 0; i < sequence.length - 1; i++) {
    
        if (sequence[i] >= sequence[i + 1]) {
            countOfCurBiggerThanNext ++;
            
            if ((i - 1 >= 0 && sequence[i - 1] >= sequence[i + 1]) && 
                (i + 2 < sequence.length && sequence[i + 2] <= sequence[i])) {
                return false;
            }
            
            if (countOfCurBiggerThanNext > 1) {
                return false;
            }
        }
    }
    return true;
}
