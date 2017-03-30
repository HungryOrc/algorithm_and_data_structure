/* Design and implement a data structure for Least Recently Used (LRU) cache. 
It should support the following operations: get and put:
* get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
* put(key, value) - Set or insert the value if the key is not already present. 
  When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.

Could you do both operations in O(1) time complexity?

Example:
LRUCache cache = new LRUCache(2); // capacity = 2
cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.put(4, 4);    // evicts key 1
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4 */

// 这一题比较难
// Ref: https://discuss.leetcode.com/topic/34701/java-easy-version-to-understand/2
/* 1.The key to solve this problem is using a double linked list which enables us to quickly move nodes.
   2.The LRU cache is a hash table of keys and double linked nodes. The hash table makes the time of get() to be O(1). 
   The list of double linked nodes make the nodes adding/removal operations O(1). */

/* Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value); */

class Node {
    // 容量超限的时候，要把HashMap里的相应node去掉，这时候就要在双向链表的尾部找到这个node并取得它所代表的key值，
    // 所以node里也要存key值
    int key; 
    int value;
    Node pre;
    Node next;
    
    public Node(int key, int value) {
    	  this.key = key;
    	  this.value = value;
    }
}

public class LRUCache {

    HashMap<Integer, Node> map; // <key, Node>
    int capicity;
    Node dummyHead, dummyTail;
    
    public LRUCache(int capacity) {
        this.capicity = capacity;
        
        map = new HashMap<>();
      
        dummyHead = new Node(0, 0);
    	  dummyTail = new Node(0, 0);
    	  dummyHead.next = dummyTail;
        dummyHead.pre = null;
    	  dummyTail.pre = dummyHead;
    	  dummyTail.next = null;
    }
    
    // delete a node in any place of the doubly-linked-list, in O(1) time
    // 之所以能O(1)时间，关键在于，我们拿到的是要被删除的node的reference ！！！
    public void deleteNode(Node node) {
    	  node.pre.next = node.next;
    	  node.next.pre = node.pre;
    }
    
    // 最近被访问的node，都要先从list里删除，然后加到list的头部来
    // 其实是加到dummyHead的后面
    public void addToHead(Node node) {
    	  node.next = dummyHead.next; // dummyHead.next 是原来的真实头部节点
    	  node.next.pre = node;
      
    	  node.pre = dummyHead;
    	  dummyHead.next = node;
    }
    
    public int get(int key) {
    	  if (map.get(key) != null) {
      		  Node node = map.get(key);
      	  	int result = node.value;
          
    	    	deleteNode(node); // 从list删除此node
    	    	addToHead(node); // 然后再把此node加到list的头部去
    	    	
            return result;
        
      	} else {
      	    return -1;
        }
    }
    
    public void put(int key, int value) {
      	if (map.get(key) != null) {
      		  Node node = map.get(key);
      		  node.value = value; // node的value要变，但key不变
          
      		  deleteNode(node);
      		  addToHead(node);
        
      	} else {
      		  Node node = new Node(key, value);
       	  	map.put(key, node);
          
      	  	if (map.size() <= capicity) { // list 没满
      		    	addToHead(node); 
      		  } else { // list 满了
    	  		    map.remove(dummyTail.pre.key);
              
    	  		    deleteNode(dummyTail.pre);
    	  		    addToHead(node);
    	  	  }
    	  }
    }
  
}
