/* Reverse bits of a given 32 bits unsigned integer.
For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), 
return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up: If this function is called many times, how would you optimize it?
Related problem: Reverse Integer */

// Ref: https://discuss.leetcode.com/topic/9764/java-solution-and-optimization
public class Solution {
    
    // you need to treat n as an unsigned value
    // The Java solution is straightforward, just bitwise operation:
    public int reverseBits(int n) {
        
        int reversed = 0;
        for (int curDigit = 0; curDigit < 32; curDigit ++)
        {
            reversed += n & 1; // 取n的最右一位，加到reversed的最右一位上
            n >>>= 1; // 用unsigned shift，左边补0。如果用>>，那就是signed shift，左边第一位的1会留在第一位
            if (curDigit < 31) // 除了最后一位意以外，每次都要把结果往左挪一位，以以迎接下一位
                reversed <<= 1;
        }
        return reversed;
    }
    
    /* How to optimize if this function is called multiple times? 
     We can divide an int into 4 bytes, and reverse each byte then combine into an int. 
     For each byte, we can use cache to improve performance. */
    // cache
private final Map<Byte, Integer> cache = new HashMap<Byte, Integer>();
public int reverseBits(int n) {
    byte[] bytes = new byte[4];
    for (int i = 0; i < 4; i++) // convert int into 4 bytes
        bytes[i] = (byte)((n >>> 8*i) & 0xFF);
    int result = 0;
    for (int i = 0; i < 4; i++) {
        result += reverseByte(bytes[i]); // reverse per byte
        if (i < 3)
            result <<= 8;
    }
    return result;
}

private int reverseByte(byte b) {
    Integer value = cache.get(b); // first look up from cache
    if (value != null)
        return value;
    value = 0;
    // reverse by bit
    for (int i = 0; i < 8; i++) {
        value += ((b >>> i) & 1);
        if (i < 7)
            value <<= 1;
    }
    cache.put(b, value);
    return value;
}
}
