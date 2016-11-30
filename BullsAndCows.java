/* You are playing the following Bulls and Cows game with your friend: 
You write down a number and ask your friend to guess what the number is. 
Each time your friend makes a guess, you provide a hint that indicates 
how many digits in said guess match your secret number 
exactly in both digit and position (called "bulls") and how many digits match the secret number but 
locate in the wrong position (called "cows"). 
Your friend will use successive guesses and hints to eventually derive the secret number.

For example:
Secret number:  "1807"
Friend's guess: "7810"
Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.)

Write a function to return a hint according to the secret number and friend's guess, 
use A to indicate the bulls and B to indicate the cows. In the above example, your function should return "1A3B".
Please note that both secret number and friend's guess may contain duplicate digits, for example:
Secret number:  "1123"
Friend's guess: "0111"
In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return "1A1B".
You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal. */

public class Solution {

    // 很巧妙的方法！
    // 既然只会出现 0-9 这样的数字，那么就用一个10 slots的array来存 0-9 的出现次数。然后关键是：
    // one-pass处理，在secret里出现一次，则相应位 +1 ！！在guess里出现一次，则相应位 -1 ！！
    // Ref: https://discuss.leetcode.com/topic/28463/one-pass-java-solution
    /* The idea is to iterate over the numbers in secret and in guess and count all bulls right away. 
     For cows maintain an array that stores count of the number appearances in secret and in guess. 
     Increment cows when either number from secret was already seen in guest or vice versa. */
    public String getHint(String secret, String guess)
    {
        int bulls = 0;
        int cows = 0;
        int[] numbers = new int[10];
        for (int i = 0; i<secret.length(); i++)
        {
            int s = Character.getNumericValue(secret.charAt(i));
            int g = Character.getNumericValue(guess.charAt(i));
            
            if (s == g) 
                bulls++;
            else
            {
                // ！精华在以下结构！！！好好体会思想！！！
                if (numbers[s] < 0) // ！secret当前要降临的地方正好有一个坑！！！遂插入完成一次配对
                    cows++;
                if (numbers[g] > 0) // ！guess当前要降临的地方正好有一个土堆！！！遂铲平完成一次配对
                    cows++;
                numbers[g] --; // ！guess挖坑！！！
                numbers[s] ++; // ！secret填坑！！！
            }
        }
        return bulls + "A" + cows + "B";
    }
    
    
    // 朴素的办法
    public String getHint(String secret, String guess) {
        
        int numOfBulls = 0;
        int numOfCows = 0;
        HashMap<Character, Integer> charsInSecret = new HashMap<>();
        ArrayList<Character> charsInGuess = new ArrayList<>();
        
        for (int i = 0; i < guess.length(); i++)
        {
            char curCharInGuess = guess.charAt(i);
            char curCharInSecret = secret.charAt(i);
                
            if (curCharInGuess == curCharInSecret)
                numOfBulls ++;
            else
            {
                charsInSecret.put(curCharInSecret, charsInSecret.getOrDefault(curCharInSecret, 0) + 1);
                charsInGuess.add(curCharInGuess);
            }
        }
        
        for (char c : charsInGuess)
        {
            if (charsInSecret.containsKey(c) && charsInSecret.get(c)>=1)
            {
                charsInSecret.put(c, charsInSecret.get(c)-1);
                numOfCows ++;
            }
        }
        return numOfBulls + "A" + numOfCows + "B";
    }
    
    
}
