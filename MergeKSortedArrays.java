/* Given k sorted integer arrays, merge them into one sorted array.

Example:
Given 3 sorted arrays:
[
  [1, 3, 5, 7],
  [2, 4, 6],
  [0, 8, 9, 10, 11]
]
return [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11]. */

/* 思路：
1. Create Min-Heap (Priority Queue) of a custom data type: QueueNode, 
every QueueNode will store the data, the index of the array which it belonged, and the index of it in that array.
2. Take the first element in each array, shift each of them into a QueueNode type respectively,
then add them into the Priority Queue.
Whatever is added into a Priority Queue will be sorted automatically right after its arrival.
Beware of the possibility that some arrays might be empty initially.
3. Poll one element from the min-Heap, namely the Priority Queue, the element will natually be the minimum one.
Insert the data of this element into result list.
4. The extracted node will also contain the array number to which it belongs, and its position in that array.
Insert the next element from that specific array into the Priority Queue. 
But if we have reached the end of that array, then do NOTHING.
5. Keep repeating these, until the Priority Queue is empty. 

时间复杂度：n*k * log(k)
k是array的个数，n是array的大约平均长度。所以n*k是大约的总数据量。
log(k)是把一个元素放到Priority Queue里所需的时间，
也是从Priority Queue里poll了minimum element以后重新validate这个queue的时间。   */


// 注意！！！ implements Comparable<QueueNode>
class QueueNode implements Comparable<QueueNode> {
    public int array, index, value;
    
    public QueueNode(int array, int index, int value) {
        this.array = array;
        this.index = index;
        this.value = value;
    }
    
    // 这里 Override 一个 compareTo(QueueNode) 函数 ！！！
    // 而非 compare(QueueNode, QueueNode) 函数
    @Override
    public int compareTo(QueueNode node) {
        if (this.value > node.value)
            return 1;
        else if (this.value < node.value)
            return -1;
        else
            return 0;
    }
}

public class Solution {

    public List<Integer> mergekSortedArrays(int[][] arrays) {
        PriorityQueue<QueueNode> pq = new PriorityQueue<QueueNode>();
        ArrayList<Integer> result = new ArrayList<>();
        
        int size = 0;
        for (int i = 0; i < arrays.length; i++) {
            size += arrays[i].length;
            if (arrays[i].length > 0) {
                pq.add(new QueueNode(i, 0, arrays[i][0])); // 先把最初的k个装进pq里面去
            }
        }
        
        while (!pq.isEmpty()) {
            QueueNode curNode = pq.poll();
            result.add(curNode.value);
            
            int arrayInd = curNode.array;
            int nextIndex = curNode.index + 1;
            if (nextIndex < arrays[arrayInd].length) {
                pq.add(new QueueNode(arrayInd, nextIndex, arrays[arrayInd][nextIndex]));
            }
        }
        return result;
    }
}
