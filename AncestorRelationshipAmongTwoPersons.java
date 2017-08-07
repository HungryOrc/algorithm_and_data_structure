/* 看一个人（暂名为ancestor）是不是另一个人（暂名为dude）的祖先。每个人都有自己的姓名，年龄，和孩子们的信息。
但是孩子关系的真实性受到年龄的限制：如果年龄差不超过10岁，则就算标了孩子，也不是真的孩子，所以也不能算祖先关系。
另一方面，姓不能作为是否是祖先的标准。因为就算是妻随夫姓，但是外公外婆还是不随夫姓，但是外公外婆仍然是孩子的祖先。 */


// 用 DFS 做

class Person {
	String firstName;
	String lastName;
	int age;
	HashSet<Person> children;
	
	public Person(String fn, String ln, int a) {
		firstName = fn;
		lastName = ln;
		age = a;
		children = new HashSet<>();
	}
}

public class Solution {

	public boolean isAncestor(Person ancestor, Person dude) {
		if (ancestor.age - dude.age <= 10) {
			return false;
		}
		
		for (Person child : ancestor.children) {
			if (child == dude) { // 别忘了这个
				return true;
			}
			if (isAncestor(child, dude)) { // DFS Recursion
				return true;
			}
		}
		
		return false;
	}
	
  // test
	public static void main(String[] args) {

		Solution solu = new Solution();
		
		Person zq = new Person("q", "z", 80);
		Person mr = new Person("r", "m", 40);
		Person dy = new Person("dy", "z", 20);
		Person ml = new Person("ml", "z", 45);
		
		zq.children.add(dy);
		zq.children.add(ml);
		mr.children.add(dy);
		mr.children.add(ml);
		
		System.out.println(solu.isAncestor(zq, dy)); // true
		System.out.println(solu.isAncestor(mr, dy)); // true
		System.out.println(solu.isAncestor(mr, ml)); // false。年龄差别不够大。虽然标了children关系，其实是后妈
	}
}
