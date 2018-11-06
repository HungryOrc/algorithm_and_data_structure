public class DequeueByStacks {
    private Deque<Integer> headStack;
    private Deque<Integer> tailStack;
    private Deque<Integer> bufferStack;
	
    public DequeueByStacks() {
        this.headStack = new ArrayDeque<>();
        this.tailStack = new ArrayDeque<>();
        this.bufferStack = new ArrayDeque<>();
    } 
			
    public void pushHead(int num) {
        headStack.push(num);
    }

    public void pushTail(int num) {
        tailStack.push(num);
    }
	
    private boolean completelyEmpty() {
        return headStack.isEmpty() && tailStack.isEmpty();
    }
	
    public int popHead() {
        if (this.completelyEmpty()) {
            return Integer.MIN_VALUE;
        } else if (!headStack.isEmpty()) {
            return headStack.pop();
        } else {
            dumpHalfAndMaintainSequence(tailStack, headStack);
            return headStack.pop();
        }
    }
	
    // 这个函数与 popHead 是对称的
    public int popTail() {
        if (this.completelyEmpty()) {
            return Integer.MIN_VALUE;
        } else if (!tailStack.isEmpty()) {
            return tailStack.pop();
        } else {
            dumpHalfAndMaintainSequence(headStack, tailStack);
            return tailStack.pop();
        }
    }
	
    // did not implement peek() here, 
    // since in this question, peek() is almost the same procedure as pop()
	
    // 最关键的函数在此！
    private void dumpHalfAndMaintainSequence(Deque<Integer> fromStack, Deque<Integer> toStack) {
	int halfSize = fromStack.size() / 2;
        
	dump(fromStack, this.bufferStack, halfSize);
	dump(fromStack, toStack, fromStack.size());
	dump(this.bufferStack, fromStack, halfSize);
    }
	
    private void dump(Deque<Integer> fromStack, Deque<Integer> toStack, int dumpSize) {
        for (int i = 1; i <= dumpSize; i++) {
            toStack.push(fromStack.pop());
        }
    }

    // main
    // -----------------------------------------------------------------
    public static void main(String[] args) {
	DequeueByStacks myDequeue = new DequeueByStacks();

	myDequeue.pushHead(3); // 3 ||
	myDequeue.pushHead(2); // 2 3 ||
	myDequeue.pushHead(1); // 1 2 3 ||
	myDequeue.pushTail(4); // 1 2 3 || 4
	myDequeue.pushTail(5); // 1 2 3 || 4 5

	System.out.println(myDequeue.popTail()); // 1 2 3 || 4, return 5
	System.out.println(myDequeue.popTail()); // 1 2 3 || , return 4
	System.out.println(myDequeue.popTail()); // 1 || 2, return 3
	System.out.println(myDequeue.popTail()); // 1 || , return 2
	System.out.println(myDequeue.popTail()); // || , return 1
	System.out.println(myDequeue.popTail()); // || , return -1
    }
}
