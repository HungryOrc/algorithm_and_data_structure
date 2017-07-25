/* 本 Trie（每个node里存的value是一个char）要实现的操作：
   > 在每个 Trie Node 上存一个 int size，记录从 Trie root 开始到本node为止组成的这个prefix，后面一共有多少个word
     如果到本node为止，恰好形成一个完整的word，那么这个word也要算到这个 size 里去
   > 插入一个 String
   > 查找一个 String 是否在Trie里作为一个完整的word
   > 查找一个 Prefix String 是否存在于Trie里且返回其最后一个node，如果false，则返回null
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
    
    // Delete a word in the trie
    // ------------------------------------------------------------------------------
    // 注意！
    // 如果被删除的word的最后一个char的后面再也没有任何children了，那么这个最后的node也可以remove掉，也可以不remove
    // 如果被删除的word的最后一个char存在于trie中，但它这个node并不是任何word的ending，那么算是要删除的word不存在
   
   
    
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
    public Node findEndNodeOfPrefix(String prefix) {
        return findEndNode(root, prefix, 0);
    }
    private Node findEndNode(Node node, String prefix, int index) {
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
    
    // Returns all the subsequent words starting with this prefix, in a list of Strings,
    // if the prefix is also regarded as a complete word in the Trie, 
    // then this prefix should also be included in the result 
    // ------------------------------------------------------------------------------
    public List<String> getAllStringsWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        
        Node endNode = getEndNode(prefix);
        if (endNode == null) {
            return result;
        }
        
        List<String> substrings = new ArrayList<>();
        getAllSubstrings(endNode, new StringBuilder(), substrings);
        
        for(String sub : substrings) {
            result.add(prefix + sub);
        }
        return result;
    }
    private void getAllSubstrings(Node node, StringBuilder sb, List<String> substrings) {
    	if (node.endOfWord == true) {
    		// 注意 ！！！如果当前node是一个word的end，就要把这个word记录到substrings里面去，
    		// 但是这并不意味着 DFS 到此结束了！相反，DFS 必须继续！！！因为后面可能还继续连着更长的词！！！
    		substrings.add(sb.toString());
    	}
    	
    	for (Node child : node.children.values()) {
    		sb.append(child.value);
    		getAllSubstrings(child, sb, substrings);
    		sb.deleteCharAt(sb.length() - 1);
    	}
    }
   
}
