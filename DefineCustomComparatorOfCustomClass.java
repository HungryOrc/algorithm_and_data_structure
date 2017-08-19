// 要求：先按player的score降序排，同score的再按player的姓名的字母顺序排

class Player {
    String name;
    int score;
    
    Player(String name, int score){
        this.name = name;
        this.score = score;
    }
}

class PlayerComparator implements Comparator<Player> {
	@Override
	public int compare(Player p1, Player p2) {
		if (p1.score == p2.score) {
			return p1.name.compareTo(p2.name);
		} else {
			// 注意 ！！！ Integer才有 compareTo 函数 ！！！
			// 像 int 之类的 primitive type 是没有 compareTo 函数的 ！！！
			return ((Integer)p2.score).compareTo((Integer)p1.score);
		}
	}
	
	// 这个class的constructor我们就不造了 ！！
}

// test
public class Solution {
	
	public static void main(String[] args) {

		Player p1 = new Player("Amy", 100);
		Player p2 = new Player("David", 100);
		Player p3 = new Player("Heraldo", 60);
		Player p4 = new Player("Akash", 76);
		Player p5 = new Player("Aleksa", 150);
		
		Player[] players = new Player[5];
		players[0] = p1;
		players[1] = p2;
		players[2] = p3;
		players[3] = p4;
		players[4] = p5;
		
		PlayerComparator myComp = new PlayerComparator(); // default constructor of the custom comparator class
		
		Arrays.sort(players, myComp);
		
		for (Player p : players) {
			System.out.print(p.name + ", ");
			System.out.println(p.score);
		}
	}
}
