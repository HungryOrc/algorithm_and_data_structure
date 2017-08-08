/* 用 roll点 的方式来决定奖品的掉落（比如打怪装备的掉落）。给的条件是一些装备和它们各自的掉落概率权重，用一个HashMap装起来，
String key 是装备也就是奖品的名字，Integer value 就是装备掉落的权重。
比如map里面装的是：“Sword 30”，“Armor 10”，“Shield 20”的话，意思就是，掉落Sword的概率是 30/60，掉落Armor的概率是 10/60，以此类推。

现在给这么一个map。然后要输出可能的掉落。每次输出的掉落要符合map里规定的权重。自然这些输出每次都可能不同。
下面的代码，为了明确真伪，把每次roll的点数是多少，每种奖品的上限区间是多少，由此得到的掉落奖品是多少，都打印出来了，以便观看。
比如上面的例子，正确的roll点和掉落如下面的几个例子：

Separator 0: UpperBound: 30, Prize: Sword ———— 意思是roll点在 [1, 30] 的话 应该掉落 Sword
Separator 1: UpperBound: 50, Prize: Shield ———— [31, 50] 掉落 Shield
Separator 2: UpperBound: 60, Prize: Armor ———— [51, 60] 掉落 Armor
Total Weight: 60

Roll: 20
Prize: Sword

Roll: 42
Prize: Shield

Roll: 52
Prize: Armor     */


class Separator {
	int upperBound;
	String prize;
	
	public Separator(int ub, String p) {
		upperBound = ub;
		prize = p;
	}
}

public class Solution {

	public String letsRoll(HashMap<String, Integer> prizesAndWeights) {
		
		Separator[] separators = locateSeparators(prizesAndWeights);
		// 为了检查separators的抓取是否正确
		/* for (int i = 0; i < separators.length; i++) {
			System.out.println("Separator " + i + 
				": UpperBound: " + separators[i].upperBound +
				", Prize: " + separators[i].prize);
		} */
		
		String output = getPrize(separators);
		return output;
	}
	
	private Separator[] locateSeparators(HashMap<String, Integer> prizesAndWeights) {
		int n = prizesAndWeights.size();
		Separator[] separators = new Separator[n];
		
		int curUpperBound = 0;
		int i = 0;
		
		for (Map.Entry<String, Integer> entry : prizesAndWeights.entrySet()) {
			String prize = entry.getKey();
			Integer weight = entry.getValue();
			
			curUpperBound += weight;
			
			Separator curSeparator = new Separator(curUpperBound, prize);
			separators[i] = curSeparator;
			i ++;
		}
		return separators;
	}
	
	private String getPrize(Separator[] separators) {
		
		int totalWeight = separators[separators.length - 1].upperBound;
		// System.out.println("Total Weight: " + totalWeight); // 为了检查 total weight
		
		// random() 得到 [0, 1)
		int roll = (int)(Math.random() * totalWeight) + 1;
		// +1 以后得到 [1, totalWeight]
		
		// System.out.println("Roll: " + roll); // 为了检查roll出来的点数到底是多少
		
		// 下面是二分法
		// 在所有的separator里，找最小的一个比roll大的upperBound，所属的separator
		
		int left = 0, right = separators.length - 1;
		while (left + 1 < right) {
			int mid = left + (right - left) / 2;
			if (separators[mid].upperBound < roll) {
				left = mid + 1;
			} else if (separators[mid].upperBound > roll) {
				right = mid;
			} else {
				return separators[mid].prize;
			}
		}
		// 因为要找符合条件的最小的upperBound，所以先检查left，再检查right
		if (separators[left].upperBound >= roll) {
			return separators[left].prize;
		} else { // separators[right].upperBound >= roll
			return separators[right].prize;
		}
	}
	
	// test
	public static void main(String[] args) {

		Solution solu = new Solution();
		
		HashMap<String, Integer> prizesAndWeights = new HashMap<>();
		prizesAndWeights.put("Sword", 30);
		prizesAndWeights.put("Armor", 10);
		prizesAndWeights.put("Shield", 20);
		
		System.out.println("Prize: " + solu.letsRoll(prizesAndWeights));
	}
}
