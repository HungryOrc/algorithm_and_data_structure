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


// Ref: https://leetcode.com/problems/min-stack/
// 很巧妙的方法！当最新push来的数x小于等于当前的min的时候，
// 就先把当前min push一次，相当于垫在下面，然后再push x。
// 然后pop的时候，如果当前要pop的数等于当前的min，就pop当前数以后，再pop一个，即曾经存在后面垫底的次低min
// 这样只用一个stack和一个int min就行。速度快了很多
class MinStack 
{
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<Integer>();
    
    public void push(int x) {
        // only push the old minimum value when the current 
        // minimum value changes after pushing the new value x
        if(x <= min){          
            stack.push(min);
            min=x;
        }
        stack.push(x);
    }

    public void pop() {
        // if pop operation could result in the changing of the current minimum value, 
        // pop twice and change the current minimum value to the last minimum value.
        if(stack.pop() == min) min=stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}


// 我的方法。用一个stack加一个arrayList。但是速度太慢
public class MinStack {

    /** initialize your data structure here. */
    Stack<Integer> intStack;
    ArrayList<Integer> minNums;
    
    public MinStack() {
        this.intStack = new Stack<Integer>();
        this.minNums = new ArrayList<Integer>();
    }
    
    public void push(int x) {
        this.intStack.push(x);
        
        if (minNums.size()==0 || x > minNums.get(minNums.size()-1))
            minNums.add(x);
        else
        {
            for (int i = 0; i < minNums.size(); i++)
            {
                if (x <= minNums.get(i))
                    minNums.add(i, x);
            }
        }
    }
    
    public void pop() {
        int popped = this.intStack.pop();
        
        for (int i = 0; i < minNums.size(); i++)
        {
            if (popped == minNums.get(i))
                minNums.remove(i);
        }
    }
    
    public int top() {
        return this.intStack.peek();    
    }
    
    public int getMin() {
        return this.minNums.get(0);
    }
}


