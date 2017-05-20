/* Design an iterator over a binary search tree with the following rules:
Elements are visited in ascending order (i.e. an in-order traversal)
next() and hasNext() queries run in O(1) time in average.

Challenge: Extra memory usage O(h), h is the height of the tree.
Super Star: Extra memory usage O(1).

Example
For the following binary search tree, in-order traversal by using iterator is [1, 6, 10, 11, 12]

   10
 /    \
1      11
 \       \
  6       12

/* Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 
 * Example of iterating a tree:
 * BSTIterator iterator = new BSTIterator(root);
 * while (iterator.hasNext()) {
 *    TreeNode node = iterator.next();
 *    // do something to each node
 *    // ...
 * } */


// 方法1：用一个 Stack 来处理。很巧妙 ！！！ 这个解法要记住 ！！！
// 特别注意 ！！！ 关于 Iterator 的思维方式，即忌讳之处，参考我在本Git目录里的相应总结文章，命名可能为 !Iterator.java 
public class TreeIterator {
    private Stack<TreeNode> stack;
    
    public TreeIterator(TreeNode root) {
        this.stack = new Stack<>();
        TreeNode cur = root;
        
        // 这里只是把 root 的各级 直系left子孙 都预先放到stack里去 ！！！是 O(tree height) 的操作 ！！！
        // 这个预处理的时间远低于 O(n)，是可以接受的
        while (cur != null) {
            stack.push(cur);
            if (cur.left != null) {
                cur = cur.left;
            } else {
                break;
            }
        }
    }

    public boolean  hasNext() {
        return !stack.isEmpty();
    }
    
    // 特别注意 ！！！ 这是一种非常巧妙的 DFS 的实现方式 ！！！
    // 可以做到：
    // * 不重复取node
    // * 每次除了pop出来一个node以外，也许能再push几个node进stack里，也许1个node也push不了（被pop的node没有right的情况下）
    // * 最后一定能遍历完所有的nodes
    // * 只要还没遍历完，这个stack就一定不会空
    public int next() {
        TreeNode nodeAtStackTop = stack.pop(); // 这个是作为本次的答案的node
        TreeNode cur = nodeAtStackTop;
        
        if (cur.right != null) {
            cur = cur.right;
            
            while (cur != null) {
                stack.push(cur);
                if (cur.left != null) {
                    cur = cur.left;
                } else {
                    break;
                }
            }
        }
        
        return nodeAtStackTop.val;
    }
}


// 方法2：我的土方法。先把所有nodes都放到一个 ArrayList 里面去。这样做其实非常不好 ！！！ 不可取 ！！！ 因为：
// 此方法在开始iterate之前，使用了 O(n) 的空间，和 O(n) 的时间，来预处理所有的nodes，这是不可接受的 ！！！
public class BSTIterator {
    
    public ArrayList<TreeNode> treeNodeAL;
    int sizeOfAL;
    int curIndex;
    
    //@param root: The root of binary tree.
    public BSTIterator(TreeNode root) {
        this.treeNodeAL = new ArrayList<TreeNode>();
        // 先放一个dummy node作为所有tree nodes的previous head，
        // 便于iterator进行next操作
        this.treeNodeAL.add(new TreeNode(Integer.MIN_VALUE));
        inorderTraversal(root, this.treeNodeAL);
        
        this.sizeOfAL = this.treeNodeAL.size();
        
        this.curIndex = 0;
    }

    private void inorderTraversal(TreeNode curNode, ArrayList<TreeNode> treeNodeAL) {
        if (curNode == null) {
            return; // do nothing
        }
        
        inorderTraversal(curNode.left, treeNodeAL);
        treeNodeAL.add(curNode);
        inorderTraversal(curNode.right, treeNodeAL);
    }

    //@return: True if there has next node, or false
    public boolean hasNext() {
        if (this.curIndex == this.sizeOfAL - 1) {
            return false;
        } else {
            return true;
        }
    }
    
    //@return: return next node
    public TreeNode next() {
        this.curIndex ++;
        return this.treeNodeAL.get(curIndex);
    }
}
