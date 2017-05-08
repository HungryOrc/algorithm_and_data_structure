/* 一个数组并未排序，长度未知。里面可能有正数以及负数。求从里面任意取出3个数，其乘积的最大值 max product，简称 maxP

思路：把数组从小到大排列，然后分几种情况讨论：

    X    X    X    X  ...  X    X    X
   min  2ndMin         3rdMax 2ndMax max
   
(1) 最后也就是最大的三个数都是正数 
   1.1 如果最前也就是最左的三个数都是负数，则 maxP 一定等于 最大的三个数之积
   1.2 如果最前的三个数里有一个负数（那么这个负数只能是最小的那个数），则 maxP 还是一定是 最大的三个数之积
   1.3 如果最前的三个数里有2个负数，则要考察一下最大的那个数和这两个负数的乘积，和最大的三个数的乘积，谁更大
   1.4 如果最前的三个数 \ 四个数 \ 五个数 都是负数，结论和 1.3 还是一样的
(2) 最后的三个数里，有一个负数（那么这个负数只能是 3rdMax）
   这种情况下要得到最大乘积，首先得努力把它弄成正数。易得 max * min * 2ndMin 是我们能得到的最大的正数
   （注意这里因为 3rdMax 已经是负数了，所以它之前的所有数都一定是负数）
(3) 最后的三个数里，有2个负数（必然是 3rdMax 和 2ndMax）
   这种情况下，整个数组里只有最后一个数(max)为正数。我们还是要努力使得最终乘积为正数，所以必然要带上这个 max，再加2个负数
   因为负数越小绝对值越大，所以这种情况下，一定是 max * min * 2ndMin 能得到最大的乘积
(4) 最后的三个数里，三个都是负数。那么整个数组一定都是负数
   这种情况下，最终的乘积一定是负数。我们只能力图让它的绝对值最小。这样必然就应该是 max * 2ndMax * 3rdMax

所以说一千道一万，我们可以发现，皇帝最后总是在两家之间二选一：
要么是 max * 2ndMax * 3rdMax，要么是 max * min * 2ndMin    */

// 以下的范例暂时 copy 的别人的 c++ 代码 ！！！
// Ref: http://www.geeksforgeeks.org/find-maximum-product-of-a-triplet-in-array/

/* 方法1：先把数组排序，然后直接用上面的结论
O(nlogn) Time, O(1) Space
Sort the array using some efficient in-place sorting algorithm in ascending order.
Return the maximum of product of last three elements of the array and product of first two elements and last element. */
int maxProduct(int arr[], int n)
{
    // if size is less than 3, no triplet exists
    if (n < 3)
        return -1;
 
    // Sort the array in ascending order
    sort(arr, arr + n);
 
    // Return the maximum of product of last three
    // elements and product of first two elements
    // and last element
    return max(arr[0] * arr[1] * arr[n - 1],
               arr[n - 1] * arr[n - 2] * arr[n - 3]);
}


/* 方法2：进一步提高时间效率，不排序数组，然后一次过！
注意 ！！！在不排序的数组里，One Pass 得到最大的几个数分别是多少，最小的几个数分别是多少的做法 ！！！
O(n) Time, O(1) Space
Scan the array and compute Maximum, second maximum and third maximum element present in the array.
Scan the array and compute Minimum and second minimum element present in the array.
Return the maximum of product of Maximum, second maximum and third maximum and product of Minimum, second minimum and Maximum element.
Note – Step 1 and Step 2 can be done in single traversal of the array. */
int maxProduct(int arr[], int n)
{
    // if size is less than 3, no triplet exists
    if (n < 3)
        return -1;
 
    // Initialize Maximum, second maximum and third
    // maximum element
    int maxA = INT_MIN, maxB = INT_MIN, maxC = INT_MIN;
 
    // Initialize Minimum and second mimimum element
    int minA = INT_MAX, minB = INT_MAX;
 
    for (int i = 0; i < n; i++)
    {
        // Update Maximum, second maximum and third
        // maximum element
        if (arr[i] > maxA)
        {
            maxC = maxB;
            maxB = maxA;
            maxA = arr[i];
        }
 
        // Update second maximum and third maximum element
        else if (arr[i] > maxB)
        {
            maxC = maxB;
            maxB = arr[i];
        }
 
        // Update third maximum element
        else if (arr[i] > maxC)
            maxC = arr[i];
 
        // Update Minimum and second mimimum element
        if (arr[i] < minA)
        {
            minB = minA;
            minA = arr[i];
        }
 
        // Update second mimimum element
        else if(arr[i] < minB)
            minB = arr[i];
    }
 
    return max(minA * minB * maxA,
               maxA * maxB * maxC);
}
