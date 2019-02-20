/* Serialization is the process of converting a data structure or object into a sequence of bits so that it can be 
stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later 
in the same or another computer environment.
Design an algorithm to serialize and deserialize a binary search tree. There is no restriction on how your 
serialization/deserialization algorithm should work. You just need to ensure that a binary search tree can be 
serialized to a string and this string can be deserialized to the original tree structure.
The encoded string should be as compact as possible.
Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless. */


Time: O(n)
Space: serialize: O(logn) deserialize:O(n)

// 方法1，九章
class Solution {
    // Serialize
    public String serialize(TreeNode root) {
        if (root == null) {
            return "{}";
        }

        ArrayList<TreeNode> queue = new ArrayList<TreeNode>();
        queue.add(root);

        // 把所有的左右children全部加上，不管这里面有没有null node
        // 注意！这里的queue size是不断增加的！！
        // 这里用while似乎更符合直觉，但其实更繁琐，还不如用for，习惯以后更好
        for (int i = 0; i < queue.size(); i++) {
            TreeNode node = queue.get(i);
            if (node == null) {
                continue;
            }
            queue.add(node.left);
            queue.add(node.right);
        }

        // 把queue的结尾的那些null都去掉，一直到结尾不是null
        while (queue.get(queue.size() - 1) == null) {
            queue.remove(queue.size() - 1);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(queue.get(0).val);
        for (int i = 1; i < queue.size(); i++) {
            if (queue.get(i) == null) {
                sb.append(",#");
            } else {
                sb.append(",");
                sb.append(queue.get(i).val);
            }
        }
        sb.append("}");
        return sb.toString();
    }
    
    // Deserialize
    public TreeNode deserialize(String data) {
        if (data.equals("{}")) {
            return null;
        }
        
        // substring 函数的第一个参数是 inclusive 的，第二个参数是 exclusive 的
        // 所以下面这样写，就是把 String data 里的第一个和最后一个字符都去掉了，即花括号
        String[] vals = data.substring(1, data.length() - 1).split(",");
        ArrayList<TreeNode> queue = new ArrayList<TreeNode>();
        
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        queue.add(root);
        
        int index = 0; // 这个参数指示：当前正在处理String数组里的第几个String
        boolean isLeftChild = true;
        
        // 注意！！index 和 i 不是一回事！！！
        // index表示当前的 parent node 是哪个，i 表示当前在查看第几个node的值
        for (int i = 1; i < vals.length; i++) {
            
            // 如果第i个String不表示null node，就做以下的事。如果是null node，就什么也不做
            if (!vals[i].equals("#")) {
                TreeNode node = new TreeNode(Integer.parseInt(vals[i]));
                if (isLeftChild) {
                    queue.get(index).left = node;
                } else {
                    queue.get(index).right = node;
                }
                queue.add(node);
            }
            
            // 当这个node的左右子节点都搞定了，就可以处理下一个node了
            if (!isLeftChild) {
                index++;
            }
            isLeftChild = !isLeftChild;
        }
        return root;
    }
}


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

