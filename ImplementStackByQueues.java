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

// 方法1：常驻一个queue，临时性一个queue。每次都必须要来回倒
class MyStack {
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


// 方法2：常驻2个queue。每次都必须要来回倒
class Stack {
    Queue<Integer> queue1;
    Queue<Integer> queue2;
    
    public Stack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }
    
    // Push a new item into the stack
    public void push(int x) {
        if (queue1.isEmpty()) {
            queue1.offer(x);
            while (!queue2.isEmpty()) {
                queue1.offer(queue2.poll());
            }
        }
        
        else { // queue2.isEmpty()
            queue2.offer(x);
            while (!queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }
        }
    }

    // Pop the top of the stack
    public void pop() {
        if (queue2.isEmpty()) {
            queue1.poll();
        } else {
            queue2.poll();
        }
    }

    // Return the top of the stack
    public int top() {
        if (queue2.isEmpty()) {
            return queue1.peek();
        } else {
            return queue2.peek();
        }
    }

    // Check the stack is empty or not.
    public boolean isEmpty() {
        return (queue1.isEmpty() && queue2.isEmpty());
    }    
}
