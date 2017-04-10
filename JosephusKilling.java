// Josephus 杀人环。即一个环里有n个人，每k个人杀一个，求从始至终的杀人顺序

import java.util.*;

// 方法1：用循环array做
public class JosephusKilling_ByCircularArray {

	public static ArrayList<Integer> josephus(int n, int everyK) {
		ArrayList<Integer> killingSequence = new ArrayList<>();
		if (n <= 0) {
			return killingSequence;
		}
		
		// int[] people = new int[n];
		boolean[] dead = new boolean[n];
		int deadCount = 0;
		
		int killIndex = 0;
		while(deadCount < n) {
			killIndex = getKillIndex(killIndex, everyK, dead);
			
			dead[killIndex] = true;
			deadCount ++;
			
			killingSequence.add(killIndex + 1); // 答案的计数从1开始
		}
		return killingSequence;
	}
	
	private static int getKillIndex(int startIndex, int remainCount, boolean[] dead) {
		
		int curIndex = startIndex;
		
		while (remainCount > 0 && curIndex < dead.length) {
			if (dead[curIndex] == false) {
				remainCount --;
			}
			curIndex ++;
		}
		
		if (remainCount == 0) { // 此时一定有 curIndex <= dead.length
			return curIndex - 1; // 实地测一下就知道要 -1
		} else { // 此时一定是：remainCount > 0 && curIndex == dead.length
			// 从头来过
			return getKillIndex(0, remainCount, dead);
		}
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> result = JosephusKilling.josephus(10, 3);		
		System.out.println(result);
	}
}


// 方法2：用 Iterator 做
public class JosephusKilling_ByIterator {
	
	// remove one node from every 3 nodes, 
	// and turn back to the start of the list when reaching the end of the list
	public static void removeEveryThreeNodes_Circulation(ArrayList<Integer> list) {
		ArrayList<Integer> result = new ArrayList<>(list);

		int startIndex = 0;
		while(result.size() > 0) {
			startIndex = removeOneNode(result, startIndex, 3);
		}
	}
	
	private static int removeOneNode(ArrayList<Integer> list, int startIndex, int remainCount) {
		Iterator<Integer> intIter = list.iterator();
		
		for (int i = 1; i <= startIndex; i++) { // if startIndex == 0, then we will not move even once
			intIter.next();
		}
		
		int curIndex = startIndex;
		while (remainCount > 0 && intIter.hasNext()) {
			intIter.next();
			remainCount --;
			curIndex ++;
		}
		
		if (remainCount == 0) { // if the count of 3 is completed in this loop
			intIter.remove();
			System.out.print(list + ", ");
			return curIndex - 1;
		}
		
		else { // the count of 3 is not completed, we've reached the end of the list
			return removeOneNode(list, 0, remainCount);
		}
	}
	
	public static void main(String[] args) {
		
		removeEveryThreeNodes_Circulation(new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8)));
		// [1, 2, 4, 5, 6, 7, 8], [1, 2, 4, 5, 7, 8], [2, 4, 5, 7, 8], [2, 4, 7, 8], [4, 7, 8], [4, 7], [7], []
		System.out.println();
	}
}
