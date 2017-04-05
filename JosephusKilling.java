// Josephus 杀人环。即一个环里有n个人，每k个人杀一个，求从始至终的杀人顺序

import java.util.*;

public class JosephusKilling {

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
