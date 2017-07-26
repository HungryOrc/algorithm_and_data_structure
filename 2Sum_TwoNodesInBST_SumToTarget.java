/* 给了一个BST，再加一个 int target，问这个树里面是否存在2个Nodes的value的和 == target

思路：
运用类似于2 Sum的双指针的思路，一个指针从最小开始，从小到大运动，另一个指针从最大开始，从大到小运动。
那么我们设置2个Nodes，一个是nodeLeft，一个是nodeRight。
nodeLeft一开始要指向整棵树的最小的node，即我们从root开始不断地取left，再也取不下去的时候，就是nodeLeft应该在的地方。
nodeRight一开始要指向整棵树的最大的node，即我们从root开始不断地取right，再也取不下去的时候，就是nodeRight应该在的地方。

然后，就涉及到BST的一个特别重要的遍历方式了 ！！！ 它其实是一种 in order 的遍历思想 ！！！
从root开始，先一路到最left，直到走不动，那么此时是min。一路left下来的逐个node都放到stack里去。
然后看当前的node cur有没有right，有则把cur移动到cur.right，然后再不断地移动 cur = cur.left，直到移不动为止，一路上的nodes也都放到stack里去
然后不断地沿着stack向上回溯，走右道，再一路向左走到头。
按照这样一段一段往左走的方式，就把整棵树撕成一绺一绺地 从右上到左下的布条了 ！！！
而这样做的过程中，stack里依次pop出来的nodes，它们的values，是从小到大的升序排列 ！！！  */

