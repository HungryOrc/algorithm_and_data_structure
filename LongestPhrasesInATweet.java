/* 一个tweet里有多个单词，每个单词有各自的长度即各自含有多少个letters。要找一个tweet里相邻的一组单词（即所谓的一个phrase），
就像一个sub array，要满足：它们的总长度 <= k，然后这些单词的总个数最大 */

public class Solution {
    
    public int maxWords (int[] wordLengths, int k) {
        // ignore the corner cases
      
        int maxNumOfWords = 0;
        int totalLenOfWords = 0;
      
        Queue<Integer> queueOfLens = new LinkedList<>();
      
        for (int len : wordLengths) {
            
            if (totalLenOfWords + len <= k) {
                queueOfLens.offer(len);
                totalLenOfWords += len;
            }
            else { // totalLenOfWords + len > k
                maxNumOfWords = Math.max(maxNumOfWords, queueOfLens.size());
                
                while (totalLenOfWords + len > k) {
                    totalLenOfWords -= queueOfLens.poll();
                }
              
                totalLenOfWords += len;
                queueOfLens.offer(len);
                maxNumOfWords = Math.max(maxNumOfWords, queueOfLens.size());
            }
        }
        maxNumOfWords = Math.max(maxNumOfWords, queueOfLens.size());
        return maxNumOfWords;
    }
}
