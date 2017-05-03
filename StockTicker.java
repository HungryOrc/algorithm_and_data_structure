/* Give a stream of stock prices, design a data structure to support the following operations:
(1) StockTicker(int k): ---- Constructor of the class StockTicker
Initialize the size of the ticker.
(2) void addOrUpdate(String stock, double price):
add or update the price of a stock to the data structure.
(3) List<Map.Entry<String, double>> top(): 
return the k stocks with highest prices. */

/* 注意！！！这一题不能用 min heap 或者 min tree set 来做！！！（那种先填k个，再对比每一个新来的和当前min谁更大留谁，的方法）
这里面的理由很独特 ！！！
因为股票的价格会变高，也会变低！如果当前在 min heap 里的某个股票，不一定要是min，只要这个股票的新价格来了，而且是大幅下降的话，
就有可能会低于当前 min heap 以外的某个股票的价格。而我们没有记录min heap以外的任何股票的信息，我们最多只记录 k 个股票，
这样就没法处理这种情况。所以不能用这种方法。只能老老实实用看起来最土的 max heap 或者 max tree set 的方法 ！！！ */

import java.util.*;

// 方法1：用 HashMap + PriorityQueue 做。比后面的方法慢一些
// update price - O(logn)
// get top k - O(n*logn) 
public class StockTicker {
	int k;
	HashMap<String, Double> stocks;
	PriorityQueue<Map.Entry<String, Double>> maxHeap;
	
	public StockTicker(int k) {
		this.k = k;
		stocks = new HashMap<>();
		
		maxHeap = new PriorityQueue<>(k, new Comparator<Map.Entry<String, Double>>() {
			@Override
			public int compare(Map.Entry<String, Double> stock1, Map.Entry<String, Double> stock2) {
                
				// 在下面的compareTo函数里，左边的主语是 2，右边的宾语是 1，所以得到的结果是 从大到大小 ！！！
                // 如果主语是 1，宾语是 2，则得到的结果是 从小到大
				int result = stock2.getValue().compareTo(stock1.getValue());
				if (result != 0) {
					return result;
				}
				// if result == 0, meaning the two stocks have the same price
				return stock2.getKey().compareTo(stock1.getKey());
			}
		});
	}
	
	// Time: O(logn)
	public void addOrUpdate(String name, double price) {
		// 特别注意 ！！！
		// Map.Entry 是一种 Interface ！！！不可以被 new ！！！
		// 要 new 的话，必须 new 一个 AbstractMap.SimpleEntry ！！！
		Map.Entry<String, Double> newRecord = new AbstractMap.SimpleEntry<String, Double>(name, price);

		// 如果heap里已有此stock的老记录，则要把这个老记录删除。价格必须更新，老记录没有意义了
		if (stocks.containsKey(name)) { 
			// 特别注意 ！！！
			// 这里的 remove(obj)，是只要括号里的 obj.equals(someObj)，则 remove someObj，即 值相等 ！！！
			// 这里的时间复杂度是 O(logn)
			maxHeap.remove(new AbstractMap.SimpleEntry<>(name, stocks.get(name)));
		}
		
		maxHeap.add(newRecord);
		stocks.put(name, price);
	}
	
	// Time: O(n*logn)
	public List<Map.Entry<String, Double>> top() { // peek k elements on the top
		List<Map.Entry<String, Double>> result = new ArrayList<>();
		// copy max heap, O(n*logn)
		PriorityQueue<Map.Entry<String, Double>> copyMaxHeap = new PriorityQueue<>(maxHeap);
		
		// 小心 ！！heap里的元素可能还不到 k 个！！
		int i = 0;
		while (i < k && !copyMaxHeap.isEmpty()) {
			result.add(copyMaxHeap.poll()); // 这个全过程要 O(k*logn) 的时间
			i++;
		}
		return result;
	}
	
	
	public static void main(String[] args) {
		StockTicker testST = new StockTicker(3);
		
		testST.addOrUpdate("Disney", 30.0);
		testST.addOrUpdate("Lucas Film", 5.67);
		testST.addOrUpdate("WB", 12.51);
		testST.addOrUpdate("Paradox", 14.1);
		testST.addOrUpdate("WB", 1.0);
		
		List<Map.Entry<String, Double>> output = testST.top();
		for (Map.Entry<String, Double> record : output) {
			System.out.println(record.getKey() + ": " + record.getValue());
		}
		/* 输出：注意是按照价格从高到底排列的：
		   Disney: 30.0
           Paradox: 14.1
           Lucas Film: 5.67
		 */
	}

}


// 方法1：用 HashMap + TreeSet 做。快一些
// 主要是 get top k 的时候快很多，因为 TreeSet 是有序的，有自己的 Iterator！！！
// update price - O(logn)
// get top k - O(k)
public class StockTicker {
    private int k;
    private HashMap<String, Double> map; // <name, price>
    private TreeSet<Map.Entry<String, Double>> treeSet;

    public StockTicker(int k) {
        this.k = k;
        map = new HashMap<>();
      
        treeSet = new TreeSet<> (new Comparator<Map.Entry<String, Double>>() {
            @Override
            public int compare(Map.Entry<String, Double> obj1, Map.Entry<String, Double> obj2) {
                // 在下面的compareTo函数里，左边的主语是 2，右边的宾语是 1，所以得到的结果是 从大到大小 ！！！
                // 如果主语是 1，宾语是 2，则得到的结果是 从小到大
                int res = obj2.getValue().compareTo(obj1.getValue());
                if (res == 0) { // if result == 0, meaning the two stocks have the same price
                    return obj2.getKey().compareTo(obj1.getKey());
                }
                return res;
            }
        });
    }

    // Time: O(logn)
    public void addOrUpdate(String name,  double price)  {
        // 特别注意 ！！！
		// Map.Entry 是一种 Interface ！！！不可以被 new ！！！
		// 要 new 的话，必须 new 一个 AbstractMap.SimpleEntry ！！！
        AbstractMap.SimpleEntry<String, Double> entry =  new AbstractMap.SimpleEntry<>(name, price);
        
        if (map.containsKey(name)) {
            // 特别注意 ！！！
			// 这里的 remove(obj)，是只要括号里的 obj.equals(someObj)，则 remove someObj，即 值相等 ！！！
			// 这里的时间复杂度是 O(logn)
            treeSet.remove(new AbstractMap.SimpleEntry<>(name, map.get(name)));	    				
        }
        map.put(name, price);
        treeSet.add(entry);     		
    }

    // Time: O(k)
    public List<Map.Entry<String, Double>> top() {
        List<Map.Entry<String, Double>> ls = new ArrayList<>();
        int i = 0;
        Iterator<Map.Entry<String, Double>> setIterator = treeSet.iterator();
        // 小心 ！！heap里的元素可能还不到 k 个！！
        while (i < k && setIterator.hasNext()) { // 这个全过程要 O(k) 的时间
            ls.add(setIterator.next());
            i++;
        }
        return ls;
    }
}
