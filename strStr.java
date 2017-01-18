/* For a given source string and a target string, you should output the first index(from 0) of target string in source string.
If target does not exist in source, just return -1. */

// Here is the most naive way to solve this problem: 2-layer for-loops.
// For more advanced ways, for example KMP method, refer to other files whose names contain the key word "strStr". 

class Solution {
    /**
     * Returns a index to the first occurrence of target in source,
     * or -1  if target is not part of source.
     * @param source string to be scanned.
     * @param target string containing the sequence of characters to match.
     */
    public int strStr(String source, String target) 
    {
        if (source == null || target == null)
            return -1;
        if (target.length() == 0)
            return 0;
        
        int j = 0;
        // 注意这里 i 的上限！是 source.length() - target.length() + 1
        for (int i = 0; i < source.length() - target.length() + 1; i++) {
            for (; j < target.length(); j++) {
                if (source.charAt(i + j) != target.charAt(j)) {
                    break;
                }
            }
            if (j == target.length()) {
                return i;
            }
        }
        // no match in the whole source
        return -1;
    }
    
}
