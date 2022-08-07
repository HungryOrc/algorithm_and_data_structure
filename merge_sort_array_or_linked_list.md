# Merge Sort an Array or a Linked List

## Overview of Merge Sort
Explanation of Merge Sort: https://www.geeksforgeeks.org/merge-sort/

## Merge Sort a Linked List
LeetCode link: https://leetcode.com/problems/sort-list/

### Method 1: Top-Down Merge Sort
* Time: `O(n * logn)`
  * `n` is the number of nodes in the linked list.
* Space: `O(logn)`
  * Because it needs `logn` layers to run this algorithm.

#### C++
```cpp
/**
 * Definition for singly-linked list.
 * struct ListNode {
 *     int val;
 *     ListNode *next;
 *     ListNode() : val(0), next(nullptr) {}
 *     ListNode(int x) : val(x), next(nullptr) {}
 *     ListNode(int x, ListNode *next) : val(x), next(next) {}
 * };
 */

class Solution {
  public:
    ListNode* sortList(ListNode* head) {
        if (head == nullptr || head->next == nullptr) {
            return head;
        }
        
        ListNode* middle = getMiddleNode(head);
        ListNode* left_head = sortList(head);
        ListNode* right_head = sortList(middle);
        
        return merge(left_head, right_head);
    }
    
    ListNode* merge(ListNode* left_head, ListNode* right_head) {
        ListNode dummy_head;
        ListNode* ptr = &dummy_head;
        
        while (left_head != nullptr && right_head != nullptr) {
            if (left_head->val > right_head->val) {
                ptr->next = right_head;
                right_head = right_head->next;
            } else {
                ptr->next = left_head;
                left_head = left_head->next;
            }
            ptr = ptr->next;
        }
        
        if (left_head == nullptr) {
            ptr->next = right_head;
        } else {
            ptr->next = left_head;
        }
        
        return dummy_head.next;
    }
    
    ListNode* getMiddleNode(ListNode* head) {
        ListNode* middle = nullptr;
        
        // Corner case 1.
        if (!head || !head->next) {
            return nullptr;
        } 
        // Corner case 2.
        if (!head->next->next) {
            middle = head->next;
            // Must cut the tail of the left half list! 
            // Or it will Stack Overflow (due to infinite loop)!
            head->next = nullptr;
            return middle;
        }
        
        ListNode* pre_middle = head;
        head = head->next->next;
        while (head && head->next) {
            head = head->next->next;
            pre_middle = pre_middle->next;
        }
        
        middle = pre_middle->next;
        // Must cut the tail of the left half list! 
        // Or it will Stack Overflow (due to infinite loop)!
        pre_middle->next = nullptr;
        return middle;
    }
};
```

#### Java
```java
public class Solution {
    public ListNode mergeSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
      
        // Step 1, get leftEnd and rightHead
        ListNode leftEnd = getLeftEnd(head);
        ListNode rightHead = leftEnd.next;
        leftEnd.next = null;
        
        // Step 2, sort the two halves respectively
        ListNode leftHead = mergeSort(head);
        rightHead = mergeSort(rightHead);
      
        // Step 3, merge two sorted lists
        // 最开始的sort也是在这里发生的，即对于2个长度为1的list的 sort & merge
        return mergeTwoSortedLists(leftHead, rightHead);
    }
  
    private ListNode getLeftEnd(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
      
        ListNode slow = head, fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
  
    private ListNode mergeTwoSortedLists(ListNode h1, ListNode h2) {
        if (h1 == null) {
            return h2;
        }
        if (h2 == null) {
            return h1;
        }
      
        if (h1.value <= h2.value) {
            h1.next = mergeTwoSortedLists(h1.next, h2);
            return h1;
        } else {
            h2.next = mergeTwoSortedLists(h1, h2.next);
            return h2;
        }
    }
}
```


### Method 2: Bottom-Up Merge Sort
* Time: `O(n * logn)`
  * `n` is the number of nodes in the linked list.
* Space: `O(1)`

#### C++

```cpp
aaa
```
