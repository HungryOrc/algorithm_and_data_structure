/* 给几个亲戚，以及他们之间的亲属关系，注意这些关系都是单向关系！比如：A，B，C，D这四个人是一家人，然后
B是A的brother，C是B和A的mother，D是B的friend。这其实就是一个有向图。要求print出来所有从A出发到C的亲戚路径，可能不止一条哦。
就上面的例子来说应该print：
A brother B, B mother C, 
A mother C,                                       */


// 思路：DFS，算法上没什么特别的。数据结构的使用上，特别是 Person类 的定义和 HashMap<Person, String> relatives 的定义值得注意一下

class Person {
	String name;
	HashMap<Person, String> relatives; // <object of the relative, relation of the relative>
	public Person(String name) {
		this.name = name;
		this.relatives = new HashMap<>();
	}
}

public class Solution {

	public List<String> findPath(Person source, Person target) {
		List<String> result = new ArrayList<>();
		
		dfs(source, target, new StringBuilder(), new HashSet<Person>(), result);	
		return result;
	}
	
	private void dfs(Person cur, Person target, 
			StringBuilder sb, HashSet<Person> visited, List<String> result) {
		
		if (cur == target) {
			result.add(sb.toString());
			return;
		}
		
		for (Map.Entry<Person, String> entry : cur.relatives.entrySet()) {
			Person person = entry.getKey();
			String relation = entry.getValue();
			
			if (!visited.contains(person)) {
				
				visited.add(person);
				sb.append(cur.name);
				sb.append(" ");
				sb.append(relation);
				sb.append(" ");
				sb.append(person.name);
				sb.append(", ");
				
				dfs(person, target, sb, visited, result);
				
				visited.remove(person);
				sb.delete(sb.length()
					- cur.name.length() - 1
					- relation.length() - 1
					- person.name.length() - 2, 
					sb.length());
			}
		}
	}
	
	public static void main(String[] args) {
		Solution solu = new Solution();
		
		Person a = new Person("A");
		Person b = new Person("B");
		Person c = new Person("C");
		Person d = new Person("D");
		
		a.relatives.put(b, "brother");
		b.relatives.put(c, "mother");
		a.relatives.put(c, "mother");
		b.relatives.put(d, "friend");
		
		List<String> result = solu.findPath(a, c);
		
		for (String s : result)
			System.out.println(s);		
	}
}
