/* 这个电话本需要实现以下2个功能：
(1) 加入新的联系人的名称
(2) 给一个名称前缀，return所有的以此prefix开头的联系人的名字，ArrayList<String> */

// 关于Trie的最全的，所有功能的实现，就看 Algorithm Repository 里关于 Trie 的总结文件，在最开头

import java.util.*;

class Node {
    char value;
    HashMap<Character, Node> children;
    boolean endOfWord;
    
    public Node(char c) {
        this.value = c;
        this.children = new HashMap<>();
    }
}

public class Trie {
    Node root;
    
    public Trie(String[] contacts) {
        this.root = new Node(' ');
        
        for (String name : contacts) {
            this.addContact(name);
        }
    }
    
    public void addContact(String name) {
        this.addString(root, name, 0);
    }
    private void addString(Node node, String s, int index) {
        char c = s.charAt(index);
        Node child = node.children.get(c);
        
        if (child == null) {
            child = new Node(c);
            node.children.put(c, child);
        }
        
        if (index == s.length() - 1) {
            child.endOfWord = true;
        } else {
            this.addString(child, s, index + 1);
        }
    }
    
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
    
    private Node getEndNode(String prefix) {
        return this.getEndNode(root, prefix, 0);
    }
    private Node getEndNode(Node node, String prefix, int index) {
    	char c = prefix.charAt(index);
    	Node child = node.children.get(c);
    	if (child == null) {
    		return null;
    	}
    	if (index == prefix.length() - 1) {
    		return child;
    	} else {
    		return getEndNode(child, prefix, index + 1);
    	}
    }
    
    
    public static void main (String[] args) {

    	String[] contacts = new String[]{"g2", "g3", "geg", "geek", "g1", "g"};
    	Trie myTrie = new Trie(contacts);
    	
    	String prefix = "g";
    	List<String> namesAfterPrefix = myTrie.getAllStringsWithPrefix(prefix);
    	for (String s : namesAfterPrefix) {
    		System.out.print(s + " ");
    	}
    	// 注意 ！！！输出结果相对于原来输入的String数组，是将里面的所有Strings排序过了的！！！
    	// 因为在构建Trie的过程中，就无形中进行了排序！！！
    	// prefix = "g", output = "g g1 g2 g3 geek good"
    	// prefix = "ge", output = "geek geg"
    	// prefix = "gem", output = ""
	}

}
