/* Given an array of strings, produce a single string as follows:
Repeat the following steps while there is more than one string in the array:
1. find the shortest string in the array (if there are several strings of the same length take the leftmost one);
2. find the shortest string among the rest (if there are several strings of the same length take the rightmost one);
3. extract the chosen strings from the array;
4. append the result of their concatenation (the second string should be added to the end of the first string) 
to the right end of the array.
5. After the algorithm has finished, there will be a single string left in the array. Return that string.

Constraints:
1 ≤ init.length ≤ 10,
0 ≤ init[i].length ≤ 25.

Example
For input = ["aaa", "dd", "bbbbb"], the output should be "bbbbbddaaa"
For input = ["a", "abc", "abcc", "aaa", "z", "", "qw"], the output should be "abcaaaabccqwaz" */

public String concatenationProcess(String[] init) {
    if (init.length == 1) {
        return init[0];
    }
    
    int maxPossibleLengthOfConcatenatedString = 10 * 25;
    ArrayList[] lengthsOfStrings = new ArrayList[maxPossibleLengthOfConcatenatedString];
    for (int i = 0; i < maxPossibleLengthOfConcatenatedString; i++) {
        lengthsOfStrings[i] = new ArrayList<String>();
    }
    
    int numOfStrings = init.length;
    // it's possible that we have "", namely 0-length Strings in the String Array init!
    for (String s : init) {
        int len = s.length();
        lengthsOfStrings[len].add(s);
    }
    
    String result = "";
    String firstHalf = "", secondHalf = "";
    String curPair = "";
    boolean firstNumInThePair = true;
    int managedStrings = 0;
    
    for (int i = 0; i < lengthsOfStrings.length; i++) {
        
        ArrayList<String> al = lengthsOfStrings[i];
        
        while (al.size() > 0) {
            managedStrings ++;
            
            if (firstNumInThePair) {
                firstHalf = al.remove(0);
                firstNumInThePair = false;
            }
            
            else {
                secondHalf = al.remove(al.size() - 1);
                curPair = firstHalf + secondHalf;
                lengthsOfStrings[curPair.length()].add(curPair);
                result = curPair;
                curPair = "";
                firstNumInThePair = true;
            } 
        }
    }
    return result;
}
