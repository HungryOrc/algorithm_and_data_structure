/* 这一题更具体的解释，见 StrStr_Rabin Karp 那一题

本题的特殊之处在于，String Target 里面会有且仅有一个 *，表示任何字母。即匹配的时候，String source里在这一位出现任何字母都 ok */

public class Solution {

	public final int BASE = 1000000;
	public final int HASH_MAGIC_NUMBER = 31;
	
    public int strStr_WithStart(String source, String target) {
    	if (source == null || target == null) {
    		return -1;
    	} else if (target.length() == 0) {
    		return 0;
    	}
    	
    	int n = source.length();
    	int m = target.length();
    	
    	// *
    	System.out.println("m: " + m);
    	
    	// 算出 31^m，每次去掉最左边的字母的时候要用
    	int topPower = 1;
    	for (int i = 0; i < m; i++) {
    		topPower = (topPower * HASH_MAGIC_NUMBER) % BASE;
    	}
    	
    	// *
    	System.out.println("topPower: " + topPower);
    	
    	
    	// 计算 target 的 Hash Code。注意要刨掉 * 那一位 ！
    	int targetHashCode = 0;
    	int starDigit = 0; // * 所在的 index
    	

    	
    	for (int i = 0; i < m; i++) {
    		if (target.charAt(i) == '*') {
    			starDigit = i;
    			// 注意 ！进一位还是不能少的 ！
    			targetHashCode = (targetHashCode * HASH_MAGIC_NUMBER) % BASE;
    		} else {
    			targetHashCode = (targetHashCode * HASH_MAGIC_NUMBER + target.charAt(i)) % BASE;
    			
    			System.out.println("i: " + i + ", charAt(i): " + target.charAt(i) +
    					", tmp hash code: " + targetHashCode);
    		}
    	}
    	
    	// *
    	System.out.println("starDigit: " + starDigit);
    	System.out.println("targetHashCode: " + targetHashCode);
    	
    	// 计算 * 所在的那一位的 hashCode 的乘数
    	int starPower = 1;
    	for (int i = 0; i < m - 1 - starDigit; i++) {
    		starPower = (starPower * HASH_MAGIC_NUMBER) % BASE;
    	}
    	
    	// *
    	System.out.println("starPower: " + starPower);
    	
    	
    	// 逐个检查source里每一段substring的 Hash Code
    	int hashCode = 0;
    	
    	for (int i = 0; i < n; i++) {
            
            // Step 1, 最右边加一个字母
            hashCode = (hashCode * HASH_MAGIC_NUMBER + source.charAt(i)) % BASE;
            
            // 如果长度还不到 m，则继续加字母
            if (i < m - 1) {
                continue;
            }
            
            // Step 2, 如果长度超过了 m，则最左边要减去一个字母
            if (i >= m) {
                hashCode -= (source.charAt(i - m) * topPower) % BASE; 

                if (hashCode < 0) {
                    hashCode += BASE;
                }
            }
            
            // Step 2, remove the hashCode of the char in the position of * 
            // 如果长度正好是 m（此时即 i == m - 1），或者长度超过 m，
            // 则都要把 * 所在的那一位所对应的字母所贡献的hashCode消除
            if (i >= m - 1) {
	            int posOfStar = i - m + 1 + starDigit;
	            
	            
	            int tmpHashCode = hashCode - (source.charAt(posOfStar) * starPower) % BASE;
	            // 减出来的结果可能 < 0
	            // 那么需要用下面的小trick把hashCode变回成正数
	            if (tmpHashCode < 0) {
	            	tmpHashCode += BASE;
	            }
            
	            
	         // *
	        	System.out.println("i: " + i + ", tmpHashCode: " + tmpHashCode);
	        	
	        	
	            // Step 4, double check, in case there was a HashCode Conflict,
	            // meaning that different Strings had the same HashCode
	        	// 注意 ！ 这里要分别判断被 * 隔开的两段，是否分别相等 ！
	            if (tmpHashCode == targetHashCode) {
	            	int startIndexAtSource = i - m + 1;
	                // substring的右端是不包含的，所以得 +1
	                if (source.substring(startIndexAtSource, startIndexAtSource + starDigit).equals(target.substring(0, starDigit)) &&
	                	source.substring(startIndexAtSource + starDigit + 1, i + 1).equals(target.substring(starDigit + 1, m))) {
	                    return i - m + 1; // the starting index
	                }
	            }
            }
        }
        return -1;
    }
}
