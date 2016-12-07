/* Implement the following operations of a stack using queues:
push(x) -- Push element x onto stack.
pop() -- Removes the element on top of the stack.
top() -- Get the top element.
empty() -- Return whether the stack is empty.

Notes:
You must use only standard operations of a queue,
which means only push to back, peek/pop from front, size, and is empty operations are valid.
Depending on your language, queue may not be supported natively. 
You may simulate a queue by using a list or deque (double-ended queue), 
as long as you use only standard operations of a queue.
You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack). */

class MyStack {
    // 必要的 instance variable
    Queue<Integer> fakeStack = new LinkedList<>();
    
    // 把主要的耗时都放在存上，而非取上，能增进用户体验
    // Push element x onto stack.
    public void push(int x) {
        Queue<Integer> tmpQueue = new LinkedList<>();
        tmpQueue.offer(x);
        while(!fakeStack.isEmpty())
            tmpQueue.offer(fakeStack.poll());
        fakeStack = tmpQueue;
    }

    // Removes the element on top of the stack.
    public void pop() {
        fakeStack.poll();
    }

    // Get the top element.
    public int top() {
        return fakeStack.peek();
    }

    // Return whether the stack is empty.
    public boolean empty() {
        return fakeStack.isEmpty();
    }
}
