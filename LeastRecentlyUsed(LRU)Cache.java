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
        dummyTail.pre = dummyHead;
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
          
      	  	if (map.size() <= capacity) { // list 没满
      		    	addToHead(node); 
      		  } else { // list 满了
    	  		    map.remove(dummyTail.pre.key);
              
    	  		    deleteNode(dummyTail.pre);
    	  		    addToHead(node);
    	  	  }
    	  }
    }
}



// 备注：Laioffer 的方法。大同小异。但是key和value的类型用了通用类型，node也作为了一个内部的static class来写。具体写法值得借鉴

public class Solution<K, V> {
  
  // each node contains the key, value pair,
  // and it is also a doubly linked list node
  // --------------------------------------------------------
  static class Node<K, V> {
    K key;
    V value;
    Node<K, V> next;
    Node<K, V> prev;
    
    Node(K key, V value) {
      this.key = key;
      this.value = value;
    }
    
    void update(K key, V value) {
      this.key = key;
      this.value = value;
    }
  }
  
  // fields of this LRU Cache class
  // --------------------------------------------------------
  private final int limit;
  
  // for the doubly linked list
  private Node<K, V> head;
  private Node<K, V> tail;
  
  private Map<K, Node<K, V>> map;
  
  // Constructor of LRU Cache
  // --------------------------------------------------------
  public Solution(int limit) {
    this.limit = limit;
    this.map = new HashMap<>();
  }
  
  // methods of this LRU Cache class
  // --------------------------------------------------------
  public void set(K key, V value) {
    Node<K, V> node = null;
    
    // case 1: if the key already exists in the cache, we need to update its value,
    // and move it to the head of the doubly linked list
    if (map.containsKey(key)) {
      node = map.get(key);
      node.value = value;
      remove(node);
    }
    else if (map.size() < limit) {
      
      // case 2: if the key is not in the cache and we still have space,
      // we can add a new node to the head of the list
      node = new Node<K, V>(key, value);
    }
    else {
      
      // case 3: if the key is not in the cache, but we don't have space,
      // we need to evict the tail and reuse the node at the tail,
      // use it to maintain the new key-value pair, and put it to the head of the list
      node = tail;
      remove(node);
      node.update(key, value);
    }
    append(node);
  }
  
  public V get(K key) {
    Node<K, V> node = map.get(key);
    
    if (node == null) {
      return null;
    }
    
    remove(node);
    append(node);
    return node.value;
  }
  
  private Node<K, V> remove(Node<K, V> node) {
    map.remove(node.key);
    
    if (node.prev != null) {
      node.prev.next = node.next;
    }
    if (node.next != null) {
      node.next.prev = node.prev;
    }
    if (node == head) {
      head = head.next;
    }
    if (node == tail) {
      tail = tail.prev;
    }
    
    node.next = node.prev = null;
    return node;
  }
  
  private Node<K, V> append(Node<K, V> node) {
    map.put(node.key, node);
    
    if (head == null) {
      head = tail = node;
    }
    else {
      node.next = head;
      head.prev = node;
      head = node;
    }
    return node;
  }
}
