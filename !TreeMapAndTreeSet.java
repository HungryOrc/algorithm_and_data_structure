/* TreeMap 和 TreeSet

(1) TreeMap 是 Map 接口的常用实现类；TreeSet 是 Set 接口的常用实现类
(2) 虽然 TreeMap 和 TreeSet 实现的接口规范不同，但 TreeSet 底层是通过 TreeMap 来实现的（如同HashSet的实现是通过封装了一个HashMap的成员变量来实现的），
    TreeSet里面绝大部分方法是直接调用TreeMap方法来实现的。
(3) 在HashMap里，key是通过hash函数运算直接映射到对应的slot。
    TreeMap里则是通过查找比较放到一棵二叉树里的一个合适的位置。这个位置则相当于一个slot。这样的话 search time 只能是 O(logN)
(4) TreeMap 的实现是红黑树。

TreeMap 和 TreeSet 的相同点：

都是有序的集合，即它们存储的值都是排好序的。可以用Iterator对它们内部的元素进行遍历，总耗时O(N)
都是非同步集合，因此他们不能在多线程之间共享，不过可以使用方法Collections.synchroinzedMap()来实现同步
它们内部对元素的操作时间复杂度为O(logN)，而 HashMap / HashSet 则为O(1)。

TreeMap 和 TreeSet 的不同点：

最主要的区别就是TreeSet和TreeMap分别实现Set和Map接口。TreeSet只存储一个对象，而TreeMap存储两个对象Key和Value（仅Key对象有序）
TreeSet中不能有重复对象，而TreeMap中可以存在重复对象

*/
