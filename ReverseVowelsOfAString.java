/* Write a function that takes a string as input and reverse only the vowels of a string.
All the small letter vowels and capital letter vowels count. But the vowels does not include the letter "y".

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "cold mountain", return "cild mauntoon".
*/

public class Solution {
    
    public String reverseVowels(String s) {
        
        char[] charArray = s.toCharArray();
        ArrayList<Integer> vowelSlots = new ArrayList<>();
        ArrayList<Character> vowels = new ArrayList<>();
        
        HashSet<Character> vowelTypes = new HashSet<>();
        vowelTypes.add('a');
        vowelTypes.add('e');
        vowelTypes.add('i');
        vowelTypes.add('o');
        vowelTypes.add('u');
        vowelTypes.add('A');
        vowelTypes.add('E');
        vowelTypes.add('I');
        vowelTypes.add('O');
        vowelTypes.add('U');
        
        for (int i = 0; i < s.length(); i++)
        {
            if (vowelTypes.contains(charArray[i]))
            {
                vowelSlots.add(i);
                vowels.add(charArray[i]);
            }
        }
        
        if (vowelSlots.size() < 2)
            return s;
        else 
        {
            for (int j = 0; j < vowelSlots.size(); j++)
                charArray[vowelSlots.get(j)] = vowels.get(vowels.size()-1-j);
        }
        return new String(charArray);
    }
}
