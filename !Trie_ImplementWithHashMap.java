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
