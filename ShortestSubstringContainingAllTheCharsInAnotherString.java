/* 有一个String s，还有一个String target，求s里面最短的substring，要求这个substring里含有target里的所有chars
target里面可能有重复的chars
返回 我们要求的 substring     */

public class Solution {

	public String shortestSubstringContainingEverything(String s, String target) {
		int start = 0, end = 0;
		int finalStart = 0, finalEnd = 0;
		
		HashMap<Character, Integer> recordsInTarget = new HashMap<>();	
		for (char c : target.toCharArray()) {
			Integer times = recordsInTarget.get(c);
			if (times == null) {
				recordsInTarget.put(c, 1);
			} else {
				recordsInTarget.put(c, times + 1);
			}
		}
		
		int numOfDifferentCharsInTarget = recordsInTarget.size();
		
		int minLength = s.length();
		
		for (int i = 0; i < s.length(); i++) {
			char curChar = s.charAt(i);
			
			// 即还没有找完target里的所有字母，且有的字母可能在target里出现不止一次
			if (numOfDifferentCharsInTarget > 0) {
				Integer times = recordsInTarget.get(curChar);
				if (times != null) {
					recordsInTarget.put(curChar, times - 1);
					// 注意！times可能会被扣到负数 ！！
					// 但我们只看它被扣到零的那一次，扣到零的时候把计数 -1
					if (times - 1 == 0) {
						numOfDifferentCharsInTarget --;
					}
				}
			}

			// 即target里的所有字母都出现过了足够多次
			if (numOfDifferentCharsInTarget == 0) { 
						
				while (numOfDifferentCharsInTarget == 0) {
					end = i;
					
					// 更新 min length，start，end
					if (end - start + 1 < minLength) {
						minLength = end - start + 1;
						finalStart = start;
						finalEnd = end;
					}
					
					// 在左端扣掉一个char，一直要这么做，直到target里的某一个char没有被hold住
					char removeChar = s.charAt(start);
					start ++;
					
					Integer times = recordsInTarget.get(removeChar);
					if (times == null) {
						continue;
					} else {
						recordsInTarget.put(removeChar, times + 1);
						if (times + 1 == 1) {
							numOfDifferentCharsInTarget = 1;
						}
					}
				}
			}
		}
		return s.substring(finalStart, finalEnd + 1);
	}
}
