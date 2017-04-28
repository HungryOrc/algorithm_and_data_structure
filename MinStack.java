/* Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
getMin() -- Retrieve the minimum element in the stack.

Example:
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin();   --> Returns -3.
minStack.pop();
minStack.top();      --> Returns 0.
minStack.getMin();   --> Returns -2.

Your MinStack object will be instantiated and called as such:
MinStack obj = new MinStack();
obj.push(x);
obj.pop();
int param_3 = obj.top();
int param_4 = obj.getMin(); */

// 方法1：在每个数下面，垫一个之前的min值
public class MinStack {
    int minValue;
    Stack<Integer> intStack;
    
    public MinStack() {
        this.minValue = Integer.MAX_VALUE;
        this.intStack = new Stack<>();
    }

    public void push(int number) {
        // 关键点！！每一个push要push两个东西！
        // 先push进来本number到来之前这个Stack里的min值！
        this.intStack.push(this.minValue); 
        // 然后才是push我们要push的number
        this.intStack.push(number);
        
        // 最后再更新min值
        this.minValue = Math.min(minValue, number);
    }

    public int pop() {
        int poppedValue = this.intStack.pop();
        int prevMin = this.intStack.pop();
        this.minValue = prevMin;
        return poppedValue;
    }

    public int min() {
        return this.minValue;
    }
}


// 方法2：与 方法1 同理的一个版本。用 2个Stack，
// 一个 Stack 装数值，一个Stack装相应的每个数值到来之前的min值。这两个Stack同步的push和pop
public class Solution {
  
  Stack<Integer> values;
  Stack<Integer> mins;
  
  public Solution() {
    this.values = new Stack<>();
    this.mins = new Stack<>();
  }
  
  public int pop() {
    if (values.isEmpty()) {
      return -1;
    }
    
    mins.pop();
    return values.pop();
  }
  
  public void push(int element) {
    values.push(element);

    if (mins.isEmpty() || element < mins.peek()) {
      mins.push(element);
    } else {
      mins.push(mins.peek());
    }
  }
  
  public int top() {
    if (values.isEmpty()) {
      return -1;
    }
    
    return values.peek();
  }
  
  public int min() {
    if (mins.isEmpty()) {
      return -1;
    }
    
    return mins.peek();
  }  
}


// 方法3：改进上面的方法2，优化装min值的那个Stack的空间效率
/* 很巧妙 ！！！不容易想到 ！！！Laioffer 的方法

   Stack1  ||   3        1       2       -6       -8      -8     10   ...
   Stack2  || <3,1>    <1,2>           <-6,4>   <-8,5>                ...
   
Element in Stack2 = <minValue,  从Stack1的size为多少开始,Stack1的min就是这个minValue了>
要实现上面这个设计，关键在于 push的时候怎么做，pop的时候怎么做 */

class minAndSize { // helper class
  public int min, size;
  
  public minAndSize(int min, int size) {
    this.min = min;
    this.size = size;
  }
}

public class Solution {
  
  Stack<Integer> values;
  Stack<minAndSize> minsAndSizes;
  
  public Solution() {
    this.values = new Stack<>();
    this.minsAndSizes = new Stack<>();
  }
  
  public int pop() {
    if (values.isEmpty()) {
      return -1;
    }
    
    if (minsAndSizes.peek().size == values.size()) {
      minsAndSizes.pop();
    } else { // peek().size < values.size()
      // do nothing
    }
    
    return values.pop();
  }
  
  public void push(int element) {
    values.push(element);

    // if the stack was empty before this push
    if (minsAndSizes.isEmpty()) {
      minsAndSizes.push(new minAndSize(element, 1));
      return;
    }
    
    minAndSize prevRecord = minsAndSizes.peek();
    if (element < prevRecord.min) {
      minsAndSizes.push(new minAndSize(element, values.size()));
    } else {
      // do nothing
    }
  }
  
  public int top() {
    if (values.isEmpty()) {
      return -1;
    }
    
    return values.peek();
  }
  
  public int min() {
    if (minsAndSizes.isEmpty()) {
      return -1;
    }
    
    return minsAndSizes.peek().min;
  }
  
}
