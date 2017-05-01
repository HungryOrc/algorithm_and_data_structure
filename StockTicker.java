/* Give a stream of stock prices, design a data structure to support the following operations:
(1) StockTicker(int k): ---- Constructor of the class StockTicker
Initialize the size of the ticker.
(2) void addOrUpdate(String stock, double price):
add or update the price of a stock to the data structure.
(3) List<Map.Entry<String, double>> top(): 
return the top k price stocks and their current prices. */

/* 思路：
update price -O(log(n)), red black tree is used
get top(k) - O(k) */

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
                // 下面这句表明，在这个 TreeSet 里，是用 股票的价格从小到大的顺序 来排列所有的 entry 的 ！！！
                int res = obj2.getValue().compareTo(obj1.getValue());
                if (res == 0) { // 如果 price 相同，再比较 股票名称
                    return obj2.getKey().compareTo(obj1.getKey());
                }
                return res;
            }
        });
    }

    public void addOrUpdate(String stock,  double price)  {
        AbstractMap.SimpleEntry<String, Double> entry =  new AbstractMap.SimpleEntry<>(stock, price);	
        if (map.containsKey(stock)) {
            treeSet.remove(new AbstractMap.SimpleEntry<>(stock, map.get(stock)));	    				
        }
        map.put(stock, price);
        treeSet.add(entry);     		
    }

    public List<Map.Entry<String, Double>> top() {
        List<Map.Entry<String, Double>> ls = new ArrayList<>();
        int i = 0;
        Iterator<Map.Entry<String, Double>> setIterator = treeSet.iterator();
        while (i < k && setIterator.hasNext()) {
            ls.add(setIterator.next());
            i++;
        }
        return ls;
    }
}
