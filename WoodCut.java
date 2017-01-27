/* Given n pieces of wood with length L[i] (integer array). 
Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length. 
What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.
Notice: You couldn't cut wood into float length.
If you couldn't get >= k pieces, return 0.

Runtime requirement: O(n log Len), where Len is the longest length of the wood.

Example: For L=[232, 124, 456], k=7, return 114. */

/* Ref: http://www.jiuzhang.com/solutions/wood-cut/
二分思路：
1 2 3 ... 113 114 115 116 ...
O O O ...  O   O   X   X  ...

二分的下限是 1
对于上限，注意！是max(nums)，不是min(nums)
这里很容易想成是min！但要想到，不是每一块都一定要被切开的！！*/

public class Solution {
    /** 
     *@param L: Given n pieces of wood with length L[i]
     *@param k: An integer
     *return: The maximum length of the small pieces. */
    
    public int woodCut(int[] L, int k) {
        
        if (L == null || L.length == 0) {
            return 0;
        }
        
        int minPieceLen = 1;
        int maxPieceLen = 0;
        // find the max length of one piece after the cuttings, which equals to
        // the longest wood length in the initial array
        for (int len : L) {
            if (len > maxPieceLen) {
                maxPieceLen = len;
            }
        }
        
        // 用二分法确定，把array里的木头们切成k块的话，每一块最多有多长
        while (minPieceLen + 1 < maxPieceLen) {
            int midPieceLen = minPieceLen + (maxPieceLen - minPieceLen) / 2;
            int numOfPieces = canCutInto(L, midPieceLen);
            if (numOfPieces >= k) {
                minPieceLen = midPieceLen;
            } else { // < k
                maxPieceLen = midPieceLen;
            }
        }
        if (canCutInto(L, maxPieceLen) >= k) {
            return maxPieceLen;
        } else if (canCutInto(L, minPieceLen) >= k) {
            return minPieceLen;
        } else {
            return 0;
        }
    }
    private int canCutInto(int[] L, int pieceLen) {
        int numOfPieces = 0;
        for (int len : L) {
            numOfPieces += len / pieceLen;
        }
        return numOfPieces;
    }
}
