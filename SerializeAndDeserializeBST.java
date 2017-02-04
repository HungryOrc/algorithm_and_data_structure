/* Serialization is the process of converting a data structure or object into a sequence of bits so that it can be 
stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later 
in the same or another computer environment.
Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your 
serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be 
serialized to a string and this string can be deserialized to the original tree structure.
The encoded string should be as compact as possible.
Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless. */


// 方法1，九章解法
// 效果描述：http://www.lintcode.com/en/help/binary-tree-representation/
// 答案代码：http://www.jiuzhang.com/solutions/binary-tree-serialization/








// ---------------------------------------------------------------------------------------------------------

/* 方法2，别人的另外一种解法：Java PreOrder + Queue solution
Ref: https://leetcode.com/problems/serialize-and-deserialize-bst/

my solution is pretty straightforward and easy to understand, not that efficient though. And the serialized tree is compact.
Pre order traversal of BST will output root node first, then left children, then right:
root left1 left2 leftX right1 rightX

Pre order traversal is BST's serialized string. 
To deserialized it, use a queue to recursively get root node, left subtree and right subtree.
I think time complexity is O(NlogN), worst case complexity should be O(N^2), when the tree is really unbalanced.

// Your Codec object will be instantiated and called as such:
    Codec codec = new Codec();
    codec.deserialize(codec.serialize(root));

// Definition for a binary tree node:
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    } */
 
public class Codec {
    private static final String SEP = ",";
    private static final String NULL = "null";
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) return NULL;
        Stack<TreeNode> st = new Stack<>();
        st.push(root);
        while (!st.empty()) {
            root = st.pop();
            sb.append(root.val).append(SEP);
            if (root.right != null) st.push(root.right);
            if (root.left != null) st.push(root.left);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    // pre-order traversal
    public TreeNode deserialize(String data) {
        if (data.equals(NULL)) return null;
        String[] strs = data.split(SEP);
        Queue<Integer> q = new LinkedList<>();
        for (String e : strs) {
            q.offer(Integer.parseInt(e));
        }
        return getNode(q);
    }
    
    // some notes:
    //   5
    //  3 6
    // 2   7
    private TreeNode getNode(Queue<Integer> q) { //q: 5,3,2,6,7
        if (q.isEmpty()) return null;
        TreeNode root = new TreeNode(q.poll());//root (5)
        Queue<Integer> samllerQueue = new LinkedList<>();
        while (!q.isEmpty() && q.peek() < root.val) {
            samllerQueue.offer(q.poll());
        }
        //smallerQueue : 3,2   storing elements smaller than 5 (root)
        root.left = getNode(samllerQueue);
        //q: 6,7   storing elements bigger than 5 (root)
        // 目前q里只剩下比root大的元素了
        root.right = getNode(q);
        return root;
    }
}

