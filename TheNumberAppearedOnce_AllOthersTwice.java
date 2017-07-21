// Given an array of integers, every element appears twice except for one. Find that single one

public class SingleNumber {

    // 方法：按位“异或”
    // Runtime: O(n), Space: no extra space
    // Reference: https://discuss.leetcode.com/topic/1916/my-o-n-solution-using-xor
    
    /* 关于异或的几个常用式子：A ^ A = 0, A ^ B = B ^ A, A ^ A ^ B = A ^ B ^ A = B, (A^B) ^ C = A ^ (B^C)
    // 
    // 对于“结合律” (A^B) ^ C = A ^ (B^C) 的证明：
    // （简化表达：用 AB 或 A*B 表示“与”即 A&B，用 A+B 表示“或”即 A|B）
    // A ^ B = A' & B + A & B'，因为 A'&B就是A为0而B为1所带来的最后异或的1的位，A&B'就是A为1而B为0所带来的最后异或的1的位
    // 所以：(a⊕b)⊕c
        = (a'b + ab')⊕c
        = (a'b + ab')'c + (a'b + ab')c'
        = (a'b)' (ab')' c + a'bc' + ab'c'
        = (a + b')(a' + b) c + a'bc' + ab'c'
        = abc + a'b'c + a'bc' + ab'c'
        a⊕(b⊕c)
        = a'(b⊕c) + a(b⊕c)'
        = a'(b'c + bc') + a(b'c + bc')'
        = a'b'c + a'bc' + a(b'c)'(bc')'
        = a'b'c + a'bc' + a(b + c')(b' + c)
        = a'b'c + a'bc' + abc + ab'c'
    //
    // 对于 A ^ A ^ B = A ^ B ^ A = B 的证明：
    // 一方面：A ^ A = 0, 0 ^ B = B
    // 另一方面：A ^ B 的结果设为C，C的每一位中，是 1 的意味着在这一位上 A与B不同，是 0 的意味着在这一位上 A与B相同
    // 然后 C ^ A，我们还是逐个考察C的每一位：
    // 如果C是1，A是0，则^得1，相当于还原了B在这一位的值，因为B曾经在这一位与A在这一位的0异或而得C在这一位的1
    // 如果C是1，A是1，则^得0，相当于还原了B在这一位的值，因为B曾经在这一位与A在这一位的1异或而得C在这一位的1
    // 如果C是0，A是0，则^得0，相当于还原了B在这一位的值，因为B曾经在这一位与A在这一位的0异或而得C在这一位的0
    // 如果C是0，A是1，则^得1，相当于还原了B在这一位的值，因为B曾经在这一位与A在这一位的1异或而得C在这一位的0
    // 所以 A ^ B ^ A = B
    // 同理可得：
    // A ^ C ^ B ^ A ^ C = B = (A ^ C) ^ B ^ (A ^ C) = (A ^ C) ^ B ^ (C ^ A) = A ^ C ^ B ^ C ^ A
    // A ^ C ^ C ^ B ^ A = B
    // A ^ C ^ D ^ C ^ B ^ D ^ A = ((A^C)^D) ^ C ^ B ^ D ^ A = D ^(A^C)^C^B ^ D ^ A = D ^ (A^C^C^B) ^ D ^ A = A^C^C^B ^ A = B
    */ 
    public int findSingleNumber_ByBitwise (int[] givenNums) {        
        int singleNumber = givenNums[0];
        for (int i = 1; i < givenNums.length; i ++) {
            singleNumber ^= givenNums[i];
        }
        return singleNumber;
    }
}
