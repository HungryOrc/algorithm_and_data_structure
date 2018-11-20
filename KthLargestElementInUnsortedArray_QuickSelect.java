/* Find the kth largest element in an unsorted array. 
Note that it is the kth largest element in the sorted order, not the kth distinct element.
For example, Given [3,2,1,5,6,4] and k = 2, return 5.

Note: You may assume k is always valid, 1 ≤ k ≤ array's length. */





// 方法2：把元素都放到 max heap 里面去，最后从heap里pop出来 k 个
// 时间：O(n logn)，这种方法过度处理了


// 方法3：排序整个数组，从小到大。最后取后面的k个
// 时间：O(n logn)，这种方法过度处理了
