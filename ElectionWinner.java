/* 输入一个String的数组，里面是无规则排列的名单，很可能有重复。名单是比如100个人对于总统候选人的投票记录。候选人可能只有寥寥几人。
这个数组的样子比如是：Tom, Jerry, Donald, Micky, Donald, Tom, Tom...
然后要求得票最多的候选人的名字。如果有并列第一，则取字母顺序  最  后  的那一个 */

public class Solution {

    static String electionWinner(String[] votes) {

        // <candidate name, vote count for him>
        HashMap<String, Integer> records = new HashMap<>();
        
        for (String name : votes) {
            records.put(name, records.getOrDefault(name, 0) + 1);
        }
        
        // 这个AL记录得票数并列第一的候选人们
        ArrayList<String> winners = new ArrayList<>();
        int maxVote = 0;
        
        // 注意 Map.Entry<,> 和 entrySet 的语法 ！！！
        for (Map.Entry<String, Integer> entry : records.entrySet()) {
            String name = entry.getKey();
            int vote = entry.getValue();
            
            if (vote > maxVote) {
                maxVote = vote;
                winners.clear(); // 用clear比再new一次好！！能少一些我尚不掌握缘由的bug！！
                winners.add(name);
            } else if (vote == maxVote) {
                winners.add(name);
            }
        }
        
        // 注意！！！字符串数组的默认排序规则，就是按照字符串之间的字母顺序！可以覆盖字符串长度不同的情况！！！
        Collections.sort(winners); 
        
        return winners.get(winners.size() - 1); // 取最后一个人
    }

}
