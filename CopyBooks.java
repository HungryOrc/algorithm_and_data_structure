/* Given n books and the ith book has A[i] pages. You are given k people to copy the n books.
n books list in a row and each person can claim a continous range of the n books. 
For example one copier can copy the books from ith to jth continously, but he can not copy the 1st book, 
2nd book and 4th book (without 3rd book).
They start copying books at the same time and they all cost 1 minute to copy 1 page of a book. 
What's the best strategy to assign books so that the slowest copier can finish at earliest time?

Example
Given array A = [3,2,4], k = 2.
Return 5( First person spends 5 minutes to copy book 1 and book 2 and second person spends 4 minutes to copy book 3. ) */

public class Solution {
    /* @param pages: an array of integers
     * @param k: an integer
     * @return: an integer */
    
    // 二分法。九章方式
    public int copyBooks(int[] pages, int k) {
        
        if (pages == null || pages.length == 0) {
            return 0;
        }
        
        int minCopyPage = 0; // shall be the max element in the array
        int maxCopyPage = 0; // shall be the sum of the array
        for (int page : pages) {
            if (minCopyPage < page) {
                minCopyPage = page;
            }
            maxCopyPage += page;
        }
        
        while (minCopyPage + 1 < maxCopyPage) {
            int midCopyPage = minCopyPage + (maxCopyPage - minCopyPage) / 2;
            int needCopiers = howManyCopiers(pages, midCopyPage);
            if (needCopiers <= k) {
                maxCopyPage = midCopyPage;
            } else { // >
                minCopyPage = midCopyPage;
            }
        }
        if (howManyCopiers(pages, minCopyPage) <= k) {
            return minCopyPage;
        } else { // howManyCopiers(pages, minCopyPage) <= k
            return maxCopyPage;
        }
    }
    private int howManyCopiers(int[] pages, int copyPage) {
        int numOfCopiers = 0;
        int i = 0;
        while (i < pages.length) {
            int curCopierWorkload = 0;
            while (i < pages.length && 
                   curCopierWorkload + pages[i] <= copyPage) {
                curCopierWorkload += pages[i];
                i ++;
            }
            numOfCopiers ++;
        }
        return numOfCopiers;
    }
}
