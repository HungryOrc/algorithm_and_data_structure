/* 本 Trie（每个node里存的value是一个char）要实现的操作：
   > 在每个 Trie Node 上存一个 int size，记录从 Trie root 开始到本node为止组成的这个prefix，后面一共有多少个word
     如果到本node为止，恰好形成一个完整的word，那么这个word也要算到这个 size 里去
   > 插入一个 String
   > 查找一个 String 是否在Trie里作为一个完整的word
   > 查找一个 Prefix String 是否存在于Trie里，如果true，则返回其最后一个node，如果false，则返回null
   > 返回一个 Prefix String 在Trie里的所有 后继(Subsequent) Strings，用 List<String> 来表示
*/

// Trie Node class
class Node {
    char value;
    HashMap<Character, Node> children;
    boolean endOfWord; // 注意！end of word以后，后面还可能继续有别的word！例：Trie里同时存 car 和 card 的情况
    int size; // number of all descendants, including this node itself if it is an end of a word
    
    public Node(char c) {
        this.value = c;
        this.children = new HashMap<>();
    }
}

public class Trie {
    Node root;

    // Constructor of Trie
    // ------------------------------------------------------------------------------
    public Trie() {
        root = new Node(' ');
    }
    
    // Inserts a word into the trie
    // ------------------------------------------------------------------------------
    public void insert(String word) {
        root.size ++;
        insert(root, word, 0);
    }
    private void insert(Node node, String word, int index) {
        if (index == word.length()) {
            return;
        }
        
        char c = word.charAt(index);
        Node child = node.children.get(c);
        
        if (child == null) {
            child = new Node(c);
            node.children.put(c, child);
        }
        
        child.size ++;
        if (index == word.length() - 1) {
            child.endOfWord = true;
        } else {
            insert(child, word, index + 1);
        }
    }
    
    // Returns if the word is in the trie
    // ------------------------------------------------------------------------------
    public boolean searchWord(String word) {
        return search(root, word, 0);
    }
    private boolean search(Node node, String word, int index) {
        char c = word.charAt(index);
        Node child = node.children.get(c);
        if (child == null) {
            return false;
        } else {
            if (index == word.length() - 1) {
                if (child.endOfWord == true) { // 这个child node也确实是之前加入过的一个word的结尾
                    return true;
                } else {
                    return false;
                }
            }
            return search(child, word, index + 1);
        }
    }
    
    // Returns the ending Node if the given Prefix String in the Trie,
    // return null if the prefix does not exist in this Trie
    // ------------------------------------------------------------------------------
    public boolean findEndNodeOfPrefix(String prefix) {
        return findEndNode(root, prefix, 0);
    }
    private boolean findEndNode(Node node, String prefix, int index) {
        char c = prefix.charAt(index);
        Node child = node.children.get(c);
        if (child == null) {
            return null;
        } else {
            if (index == prefix.length() - 1) {
                return child;
            }
            return findEndNode(child, prefix, index + 1);
        }
    }
    
    // Returns the number of subsequent words starting with the current prefix,
    // if the current node is also an end of a word, then this word also counts
    // ------------------------------------------------------------------------------
    public int numOfSubsequentWords(Node node) {
        return node.size;
    }
}
