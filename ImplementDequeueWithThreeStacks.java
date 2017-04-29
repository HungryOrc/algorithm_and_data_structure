/* 用多个Stack来实现一个自定义的Dequeue
最直观来说，用2个Stack尾对尾（栈底贴栈底，两个栈顶口向外）就可以实现，如下图：

        leftStack          rightStack
    topL      bottomL   bottomR    topR
    7    6    5    4  ||  3    2    1  
dequeueHead                        dequeueTail

这样的设计，push都没问题，pop的时候只要上面2个Stack不空也没问题；
问题就是如果pop的时候被pop的那一端的那个stack已经空了，比如pop head，结果left stack已经空了，
就得从另一个stack即right stack把所有元素倒到left stack，再pop掉最上面那个元素，再把剩下的元素都倒回到right stack去。
这样，一头空了以后，对这一头的每一次的pop都一定是n的时间复杂度，太慢了。

改进方式是，再加上第三个stack做buffer。每次一头空了以后，就从另一头把一半的元素挪过来。关键是要保持“两头”的相对顺序。如下图：
         leftStack      rightStack      bufferStack
       7 6 5 4 3 2 1 || empty       
   =>          3 2 1 || empty            4 5 6 7 ||
   =>          empty || 3 2 1            4 5 6 7 ||
   =>        7 6 5 4 || 3 2 1                                         */

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
