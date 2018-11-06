/* As the title described, you should only use two stacks to implement a queue's actions.
The queue should support push(element), pop() and top() where pop is pop the first(a.k.a front) element in the queue.
Both pop and top methods should return the value of first element.

Example
push(1)
pop()     // return 1
push(2)
push(3)
top()     // return 2
pop()     // return 2
Challenge 
implement it by two stacks, do not use any other data structure and push, pop and top should be O(1) by AVERAGE. */

// 用 stack1 存push进来的东西，用 stack2 存将要（按queue的规则）pop出去的东西
// 两个stack之间没有重复存的东西
// 这样做，push、pop、top的时间复杂度都是 O(1) 
public class MyQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }
    
    public void push(int element) {
        stack1.push(element);
    }
    
    private void pushEverythingFromStack1ToStack2() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
    }

    public int pop() {
        if (stack2.isEmpty()) {
            pushEverythingFromStack1ToStack2();
        }
        return stack2.pop();
    }

    // peek()
    public int top() {
        if (stack2.isEmpty()) {
            pushEverythingFromStack1ToStack2();
        }
        return stack2.peek();
    }
    
    public boolean empty() {
        return(stack2.isEmpty() && stack1.isEmpty());
    }
}
