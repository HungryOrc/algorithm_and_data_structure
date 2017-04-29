import java.util.Stack;

public class DequeueByStacks {

	// stack 1
	private Stack<Integer> headStack;
	// stack 2
	private Stack<Integer> tailStack;
	// Stack 3
	private Stack<Integer> bufferStack;
	
	public DequeueByStacks() {
		this.headStack = new Stack<>();
		this.tailStack = new Stack<>();
		this.bufferStack = new Stack<>();
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
			return -1;
		} else if (!headStack.isEmpty()) {
			return headStack.pop();
		} else {
			dumpHalfAndMaintainSequence(tailStack, headStack);
			return headStack.pop();
		}
	}
	
	public int popTail() {
		if (this.completelyEmpty()) {
			return -1;
		} else if (!tailStack.isEmpty()) {
			return tailStack.pop();
		} else {
			dumpHalfAndMaintainSequence(headStack, tailStack);
			return tailStack.pop();
		}
	}
	
	// we do not implement peek() here, since in this case, peek() is almost the same procedure as pop()
	
	private void dump(Stack<Integer> fromStack, Stack<Integer> toStack, int dumpSize) {
		for (int i = 1; i <= dumpSize; i++) {
			toStack.push(fromStack.pop());
		}
	}
	
	// key function! oh yeah!
	private void dumpHalfAndMaintainSequence(Stack<Integer> fromStack, Stack<Integer> toStack) {
		int halfSize = fromStack.size() / 2;
		dump(fromStack, this.bufferStack, halfSize);
		dump(fromStack, toStack, fromStack.size());
		dump(this.bufferStack, fromStack, halfSize);
	}
	
	
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
