/* Design a data structure that supports the following methods:
(1) insert(int n): add integer n to a stream of integers.
(2) getFirstUniqueInteger(): return the first unique integer in the stream if found, else return -1.

Example:
for input sequence:
insert(2) // 2
insert(2) // -1
insert(3) // 3
insert(4) // 3
insert(3) // 4 */

/* 思路：
建立一个双链表，从前到后按时间存出现过的数。自定义一个 class ListNode，来装 integer值，prev，next，以及是否重复了的boolean值
用一个 HashMap 来装：<出现的integer值，此integer被放在的node> */



