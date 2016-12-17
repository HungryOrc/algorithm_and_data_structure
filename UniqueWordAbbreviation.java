/* An abbreviation of a word follows the form <first letter><number><last letter>. Below are some examples of word abbreviations:

a) it                      --> it    (no abbreviation)

     1
b) d|o|g                   --> d1g

              1    1  1
     1---5----0----5--8
c) i|nternationalizatio|n  --> i18n

              1
     1---5----0
d) l|ocalizatio|n          --> l10n

Assume you have a dictionary and given a word, find whether its abbreviation is unique in the dictionary. 
A word's abbreviation is unique if no other word from the dictionary has the same abbreviation.

Example: 
Given dictionary = [ "deer", "door", "cake", "card" ]
isUnique("dear") -> false
isUnique("cart") -> true
isUnique("cane") -> false
isUnique("make") -> true */

// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");

public class ValidWordAbbr {

    String[] dic;
    HashSet<String> wordSet; // 字典里可能出现两次或两次以上相同的词！相同的词的缩写只能算一个！
    HashMap<String, Integer> abbrMap;
    
    public ValidWordAbbr(String[] dictionary) {
        this.dic = dictionary;
        this.makeAbbrMap();
    }

    public boolean isUnique(String word) {
        String abbr = this.makeAbbr(word);
        // 如果字典里不存在这个缩写，则为unique
        if (!this.abbrMap.containsKey(abbr))
            return true;
        // 如果字典里存在这个缩写且只有一次出现，且字典里拥有这个缩写的词与当前所给word相同，则为unique
        else if (this.wordSet.contains(word) && this.abbrMap.get(abbr)==1)
            return true;
        else 
            return false;
    }
    
    private void makeAbbrMap()
    {
        this.wordSet = new HashSet<String>();
        this.abbrMap = new HashMap<String, Integer>();
        
        for (String s : this.dic)
        {
            // 如果这个词已经在字典里出现过了
            if (this.wordSet.add(s) == false) // 注意！这个if里的add已经add了这个s到wordSet里去！
                continue;
            else
            {
                String abbr = this.makeAbbr(s);
                this.abbrMap.put(abbr, abbrMap.containsKey(abbr) ? abbrMap.get(abbr)+1 : 1);
            }
        }
    }
    
    private String makeAbbr(String s)
    {
        StringBuilder abbr = new StringBuilder();
        if (s.length() <= 2)
            return s;
        else
        {
            abbr.append(s.charAt(0));
            abbr.append(s.length()-2);
            abbr.append(s.charAt(s.length()-1));
            return abbr.toString();
        }
    }
    
}

