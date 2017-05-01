/* Give a stream of stock prices, design a data structure to support the following operations:

StockSticker(int k) Initialize the size of the ticker.
void addOrUpdate(String stock, double price) add or update the price of a stock to the data structure.
List<Map.Entry<String, double>> top() return the top k price stocks and their current prices. */

/* 思路：
update price -O(log(n)), red black tree is used
get top(k) - O(k) */

public class StockTicker {	    
  private int k;
  private HashMap<String, Double> map;
  private TreeSet<Map.Entry<String, Double>> set;

  public StockTicker(int k) {
    this.k = k;
    map = new HashMap<String,Double>();
    set = new TreeSet<> (new Comparator<Map.Entry<String, Double>>() {
    @Override
    public int compare(
        java.util.Map.Entry<String, Double> obj1,
        java.util.Map.Entry<String, Double> obj2) {
      int res = obj2.getValue().compareTo(obj1.getValue());
      if (res == 0)
        return obj2.getKey().compareTo(obj1.getKey());	
      return res;
    }

    });
  }

  public void addOrUpdate(String stock,  double price)  {
    AbstractMap.SimpleEntry<String, Double> entry =  new AbstractMap.SimpleEntry<>(stock,  price);	
    if (map.containsKey(stock)) {
        set.remove(new AbstractMap.SimpleEntry<>(stock, map.get(stock)));	    				
    }
    map.put(stock, price);
    set.add(entry);     		
  }

  public List<Map.Entry<String, Double>> top() {
    List<Map.Entry<String, Double>> ls = new ArrayList<>();
    int i = 0;
    Iterator<Map.Entry<String, Double>> setIterator = set.iterator();
    while (i  <  k && setIterator.hasNext()) {
      ls.add(setIterator.next());
      i++;
    }
    return ls;
  }
}
