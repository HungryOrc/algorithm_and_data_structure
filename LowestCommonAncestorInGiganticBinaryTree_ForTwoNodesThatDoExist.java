/* 因为这个树实在是太大了，比如有billions of nodes，大到无法放到内存里去，甚至无法放到一个机器的硬盘里去，所以我们要用多台机器处理这个问题。
方法是 Map-Reduce（Laioffer的例题）。
Mapper: distribute the jobs to all the machines we have
Reducer: collect results from each mapper (machine), and then do the aggregation / post-processing

假设我们有32台机器。选32这个数字为例子，是因为它是2的幂次，容易在下面的二叉树的方法里处理。详见下文。
Assume the tree we encounter is relatively balanced.
Since 2^5 = 32, so we have 32 nodes in the 5th level.
假设以这32个nodes为root的subtree分别放到编号为 M1, M2, M3... M32 的机器里
下面分几种情况讨论：

Case 1: 我们要找祖先的两个nodes: A and B，它们都处于本树的最上的5层里面
    Then we can run BFS within the top 5 layers of the tree,
    Call LCA(root, A, B, level_limit = 5).

Case 2: either node A or B is within the top 5 layers, the other is not
    Assume A is in the top 5 layers, B is not. Then we 
    Call Find(M1, B), Find(M2, B)... Find(M32, B)
    For example, if we found node B in the subtree that is managed by the machine M7, then we 
    Call LCA(root, A, M7, level_limit = 5).

Case 3: Neither node A or B is within the top 5 layers. Then we 
    Call LCA(M1, A, B), LCA(M2, A, B)... LCA(M32, A, B)
    
    Case 3.1: A and B are in different machines
        In this case, there must be exactly 2 machines that find non-null, say they are M3 and M7.
        Then we call LCA(root, M3, M7, level_limit = 5)
    
    Case 3.2: A and B are in the same machine
        In this case, only ONE machine returns non-null, say it's machine M7,
        that means both A and B are in the subtree managed by machine M7.
            Case 3.2.1: If M7 returns A or B, then it means one node is the ancestor of the other node
            Case 3.2.2: If M7 returns another node C other than A or B, then it means C is the LCA of A and B   */
