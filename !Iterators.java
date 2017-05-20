/* 关于 Iterator 的思想，有几点很重要：
(1) 不能一开始就用 O(n) 的空间复杂度把所有的数据整理一遍（数据结构上的整理、顺序的整理...... 都不行 ！！！
(2) 不能在第一次操作前用 O(n) 的时间复杂度得到第一次操作的答案。应该是每次有一个request，就消耗O(1)的时间就能得到相应的答案
     那种先耗费 O(n) 的时间得到所有答案，然后存着，然后每次request就立刻返回相应的答案的做法，是不行的 ！！！
     虽然后面这种做法，每一个request的amortized消耗时间也是 O(1)    

关于 Iterator 的实现，要注意：
(1) 一开始可以认为 index 是在 -1 的 ！！！ 第一次 hasNext() 是看 第0个元素是否存在 ！！！ 第一次 next() 是取 第0个元素 ！！！
*/

// Iterator for List
class ListIterator {
    private int current;
    List<String> list;

    public ListIterator(List<String> input) {
        list = input;
        current = 0;
    }
    
    public boolean hasNext() {
        return current < list.size();
    }
    
    public String next() {
        String cur = list.get(current);
        current ++;
        return cur;
    }
}


// Iterator of LinkedList
class ListNode {
    int value;
    ListNode next;
}

class LLIterator {
    ListNode cur;
    public LLIterator(ListNode head) {
        cur = head;
    }

    public boolean hasNext() {
        return cur != null;
    }
    
    public ListNode next() {
        if (cur == null) {
            return null;
        }
        ListNode result = cur;
        cur = cur.next;
        return result;
    }
}


// Iterator of Graph
class GraphNode {
    int value;
    List<GraphNode> neighbors;
}

public class GraphIterator {
    List<GraphNode> graph;
    int index;

    public GraphIterator(List<GraphNode> graph) {
        this.graph = graph;
        this.index = 0;
    }
    
    public boolean hasNext() {
        return index < graph.size();
    }
    
    public GraphNode next() {
        if (graph.hasNext()) {
            return graph.get(index);
            index ++;
        } else {
            return null;
        }
    }
}


// Iterator of Binary Tree
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    public TreeNide(int x) {
        val = x;
    }
}

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


