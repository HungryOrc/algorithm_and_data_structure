/* 本题的出处：https://www.codechef.com/problems/BYTESE

2个String s1 和 s2，它们的一个所谓的 “Shuffle”，设为 sff，必须满足如下的要求：
1. sff 含有的chars，正好等于 s1 和 s2 所含有的chars的并集，一个char也不能多一个char也不能少
   注意，s1和s2内部各自可能有重复char，s1和s2之间也可能有重复char，s1和s2本身可能就是相同的String
2. s1 和 s2 内部分别的chars，在 sff 里出现的前后相对顺序，必须保留它们各自在 s1 和 s2 里的前后相对顺序
   如果 sff 里比如说有好几个 'A'，那么我们不care其中的每一个'A'到底是原来属于 s1 还是 s2，
   只要通过某一种归属，能符合这些 'A' 和其它字母的相对顺序能同时满足 s1 和 s2 的原相对顺序，我们就认为 sff 是一个有效的 Shuffle
   
For example, the strings HELLO and WORLD can be shuffled to form HWEOLRLLOD, or HEWORLLLDO, or perhaps simply HELLOWORLD. 
It is not a shuffle if the original order of letters is not preserved. 
For example, the D in WORLD cannot ever appear before the R after being shuffled. 
This means that EHLLOWRDLO, for instance, is not a shuffle of HELLO and WORLD, 
even though it contains all the original letters. 

接下来，我们有所谓的 “Twin Shuffle” 的概念：
A string is a shuffle of twins if it can be formed by shuffling two identical strings.
For example, ABACBDECDE is a shuffle of twins because it can be formed by shuffling ABCDE and ABCDE. 
DBEACBCADE is not a shuffle of twins because it cannot be formed by shuffling two identical strings.

Input:
String named "input": the Shuffled string.

Output:
If the input String is actually a valid Shuffled String of 2 Twin Strings,
return one of the two identical strings that make up the input string.
If the no such pair of identical strings exist, return "N/A".

Assumptions:
1. All the strings will contains only UPPERCASE letters.
2. The length of the input string is not long, so the running time won't be very long.

Examples:
ABACBDECDE => ABCDE
HAAHAAAA => HAAA
FFFFFF => FFF
HOWHOW => HOW
DBEACBCADE => N/A   */


// 思路：DFS。没有太多特别之处。就是在选择chars组成string的过程中，用一个hashset记录一下哪些index被选上了，这样的话
// 最后就能很方便地知道没有被选上的chars是哪些，就利于比较被选出来的那些chars组成的string和剩下的chars组成的string是否相等，
// 如果相等，就是我们要求的答案。
// 只要找到一个答案，其他的就都不用再找了。所以我们用一个 object List<String> 来储存答案，虽然我们只要一个 String 而已
// 如果到了最后都没有答案，即上述的 List<String> 为空，则返回 "N/A" 

public class Solution {

    public static String twinStringShuffle(String input) {
    	if (input == null || input.length() == 0) {
    		return "N/A";
    	}
    	if (input.length() % 2 == 1) { // 长度为奇数的话，是不可能分解成两个相同的string的
    		return "N/A";
    	}
    	
    	int n = input.length();

    	StringBuilder curWord = new StringBuilder();
    	curWord.append(input.charAt(0));
    	
    	HashSet<Integer> usedIndexes = new HashSet<>();
    	usedIndexes.add(0);
    	
    	List<String> result = new ArrayList<>();
    	
    	findTwinStrings(input, 0, curWord, usedIndexes, result);
    	
    	if (result.size() > 0) {
    		return result.get(0);
    	} else {
    		return "N/A";
    	}
    }

    private static void findTwinStrings(String input, int curIndex,
    	StringBuilder curWord, HashSet<Integer> usedIndexes, List<String> result) {
    	
    	if (result.size() > 0) {
    		return;
    	}
    	
    	if (curWord.length() == input.length() / 2) {
    		if (checkSameStrings(input, usedIndexes, curWord.toString())) {
		    result.add(curWord.toString());
		}
    		return; // 不管中不中，这里都得结束了
    	}
    	
    	int n = input.length();
    	int curLen = curWord.length();
    	// 减枝的一个技巧 ！
	// 如果要找n/2个chars，而current stringBuilder里已经有了m个，那么就还要找 n/2 - m 个，
	// 那么下一个char就不能顶到input string 的结尾处去找了，得预留出 n/2 - m 的空间 ！
    	for (int i = curIndex + 1; i <= n / 2 + curLen; i++) {
    		
    		curWord.append(input.charAt(i));
    		usedIndexes.add(i);
    		
    		findTwinStrings(input, i, curWord, usedIndexes, result);
    		
    		curWord.deleteCharAt(curWord.length() - 1); // 复原
    		usedIndexes.remove(i); // 复原
    	}
    }
    
    private static boolean checkSameStrings(String input, HashSet<Integer> usedIndexes, String target) {
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < input.length(); i++) {
    		if (!usedIndexes.contains(i)) {
    			sb.append(input.charAt(i));
    		}
    	}
    	String leftOverSubsequence = sb.toString();
    	return leftOverSubsequence.equals(target);
    }
}
