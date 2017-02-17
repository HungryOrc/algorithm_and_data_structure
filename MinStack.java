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


