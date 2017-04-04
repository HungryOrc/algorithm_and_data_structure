/* Implement a data structure, provide two interfaces:
    add(number). Add a new number in the data structure.
    topk(). Return the top k largest numbers in this data structure. k is given when we create the data structure.
这些数据，可以存在数组里，也有可能是在stream里不断流过来的。这些情况下面的解法都能处理

Example:
s = new Solution(3);
>> create a new data structure.
s.add(3)
s.add(10)
s.topk()
>> return [10, 3]
s.add(1000)
s.add(-99)
s.topk()
>> return [1000, 10, 3] */


// 方法：Min Heap + Iterator (of Heap) + Collection.reverseOrder()
// 注意：用 Max Heap 做看起来更方便，其实正好相反！因为每次要去掉最小的那个数的时候，很不方便！
// 初始化PriorityQueue的时候，确实有个参数是capacity，但那个是initial capacity，不具有限制实际size的能力！！
public class Solution {
    
    private int capacity;
    private PriorityQueue<Integer> minHeap;
    
    public Solution(int k) {
        this.capacity = k;
        minHeap = new PriorityQueue<Integer>();
    }
    
    public void add(int number) {
        if (minHeap.size() < this.capacity) {
            minHeap.add(number);
        } else if (number > minHeap.peek()) {
            minHeap.poll(); // remove the top node, namely the min node from the heap
            minHeap.add(number);
        }
    }
    
    public List<Integer> topk() {
        ArrayList<Integer> result = new ArrayList<>();
        
        // 注意 ！！！PriorityQueue 也是有 Iterator 的 ！！！
        Iterator myIter = this.minHeap.iterator();
        while (myIter.hasNext()) {
            // 这样用 Iterator 做，就不会破坏 minHeap 的现有内容
            // 如果用 k 次 poll() 做，就会破坏
            // 注意！！！Iteraor出来的元素的顺序，很可能是整个二叉树(Heap)从上到下，从左到右的顺序！！！
            int num = (int)myIter.next();  // 注意！！.next() 出来的是 Object ！！
            result.add(num);
        }
        
        // 因为上面是min heap，所以iterator出来的是从小到大
        // 而本题要求的是从大到小输出结果
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }   
}
