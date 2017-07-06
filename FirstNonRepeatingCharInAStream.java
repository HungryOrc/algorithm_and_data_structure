/* 这一题是 LRU Cache 的变种 ！！！
所以，我们还是从两方面入手：
第一，用map保证对每个record的 O(1) 时间检索 ！！！
    它维持了每个char和与之对应的每个node的对应关系
第二，用doubly linked list保证对每个record的 O(1) 时间移除 ！！！
    它维持了按照时间顺序，迄今为止，所有的只出现过一次的chars所对应的nodes。更早出现的在头部，更晚出现的在尾部。

Given an unlimited stream of chars, at any time, find the 1st non-repeating char from the stream, 
IN O(1) TIME whenever the enquire is called!
If all the input chars in the stream by now are duplicated chars, we return null at this time.
If we haven't got any char from the stream yet by now, we return null.

Example:
input stream: a, a, b, c... Then the 1st non-repeating chars for each time: a, null, b, b...
input stream: a, e, b, b... Then the 1st non-repeating chars for each time: a, null, b, b...  */

/* 思路：
每当来了一个新的char：
Case 1: 如果这个char之前从没出现过，
    新建一个node，其value是这个char
    把这个char和这个node放到map里去
    把这个node放到doubly linked list的尾部
Case 2：如果这个char以前出现过，
    2.1：如果它出现过，且仅出现过一次
        在map里找到这个char对应的node
        把这个node从doubly linked list里面删掉
        在map里还是要留下这个entry！！！ 不能删掉map entry！因为删掉了的话以后就以为这个char从没出现过了！
            我们这里采用的做法是，保留entry，key还是这char，value改为null，以示这个char不是没出现过，而是出现了超过一次 ！！！
    2.2：如果这个char之前出现过不止一次
        我们什么都不做。
        因为这个时候，按我们上面的做法，这个char所对应的node一定不存在于list里；这个char在map里作为一个key是存在的，但是对应的value为null  */

class Node {
	char val;
	Node prev, next;
	
	public Node(char val) {
		this.val = val;
	}
}

public class Detector {

	Map<Character, Node> records;
	
	// for the doubly linked list
	Node dummyHead;
	Node dummyTail;
	
	// constructor of the detector
	public Detector() {
		records = new HashMap<>();
		
		dummyHead = new Node(' ');
		dummyTail = new Node(' ');
		dummyHead.next = dummyTail;
		dummyTail.prev = dummyHead;
	}
	
	// methods of the detector
	// ---------------------------------------------------------------
	
	public void readOneChar(char c) {

		Node curNode = null;
		
		// case 1: char c had never shown up before
		if (!records.containsKey(c)){
			curNode = new Node(c);
			records.put(c, curNode);
			addNodeToTail(curNode);
		}
		// case 2: char c had shown up before, for at least once
		else {
			curNode = records.get(c);
			// case 2.1: char c had shown up exactly once
			if (curNode != null) {
				removeNode(curNode);
				records.put(c, null);
			}
			// case 2.2: char c had shown up for more than once
			else {
				// we do nothing
			}
		}
	}
	
	public Character getCurFirstNonRepeatChar() {
		// if the list of non repeating chars is empty, return null
		if (dummyHead.next == dummyTail) {
			return null;
		} 
		// else return the 1st char in the list
		else {
			return dummyHead.next.val;
		}
	}
	
	private void addNodeToTail(Node node) {
		node.prev = dummyTail.prev;
		node.next = dummyTail;
		
		dummyTail.prev.next = node;
		dummyTail.prev = node;
	}
	
	private void removeNode(Node node) {
		node.prev.next = node.next;
		node.next.prev = node.prev;
	}
	
	
	public static void main(String[] args) {
		Detector det = new Detector();
		
		det.readOneChar('a');
		System.out.println(det.getCurFirstNonRepeatChar());
		det.readOneChar('b');
		System.out.println(det.getCurFirstNonRepeatChar());
		det.readOneChar('a');
		System.out.println(det.getCurFirstNonRepeatChar());
		det.readOneChar('c');
		System.out.println(det.getCurFirstNonRepeatChar());
	}
}
