/* Java ALWAYS pass by value, NEVER pass by reference. 
This is also true for Objects. When passing an Object to a method as its parameter, 
Java is actually passing the 地址的值 of the Object, 而非reference of the Object.

具体解释见下面这个例子：
function里面的形参y所得到的，是x的地址的值。具体来说，在function里，是定义了一个新的 Object y，
然后让 y 指向 x 所指向的对象。即 Object y = x
所以，在这个情况下，既然y和x指向同一个对象，那么对 y 的值所进行的任何改动，都会同样地改变x的值，因为它们是一回事

但是 ！！！ 注意 ！！！
如果在被调用的函数里，我们改变 y 所指向的对象，那么对 y 的任何改动，都不会再影响 x 了 ！！！！！     */

public static void main(String[] args) {   	
  Object x = new ...
  function(x);
  ...
}

private void function(Object y) {
  y ...
}


/* 举例：

(1) 下面这个例子，原变量 one 和形参 two 始终指向同一个 object，即同一个地址，
    所以print two's node value所打印出来的value，还是one的value。
    且直接打印one的value，也还是one的原值 1   */
class Node {
	int val;
	Node next;
	
	public Node(int val) {
		this.val = val;
	}
}

public class Solution {
    public static void main(String[] args) {   	
    	Node one = new Node(1);
    	printNodeValue(one); // 通过函数打印出来的node值，还是 1
    	System.out.println(one.val); // 直接打印出来one的node值，还是 1
    }
    
    private static void printNodeValue(Node two) {
    	System.out.println(two.val);
    } 
}

/*
(2) 对于 two 的任何修改（下面体现为 two.val = 2 这一句），都会反过来影响原来的变量 one：   */
public class Solution {
    public static void main(String[] args) {   	
    	Node one = new Node(1);
    	printNodeValue(one); // 通过函数打印出来的node值，成了 2
    	System.out.println(one.val); // 直接打印出来one的node值，也成了 2，即one的值被two带着改变了 ！！！
    }
    
    private static void printNodeValue(Node two) {
      two.val = 2;
    	System.out.println(two.val);
    } 
}

/*
(3) 如果让 two 指向其他的object，比如下文中的 three，则 two 与 one 就不再指向同一个object了 ！！！
    在此以后，对 two 的任何改变，都不会再影响 one 了 ！！！   */
public class Solution {
    public static void main(String[] args) {   	
    	Node one = new Node(1);
    	printNodeValue(one); // 通过函数打印出来的node值，成了 3
    	System.out.println(one.val); // 直接打印出来one的node值，还是 1 ！！！即 one 与 two 脱离了关系 ！！！
    }
    
    private static void printNodeValue(Node two) {
    	Node three = new Node(3);
    	two = three; // two 改为指向了一个新的 object three ！
    	System.out.println(two.val);
    } 
}


// ---------------------------------------------------------------------------------------------------------

/* 既然 Java 里对于 object 的传递，都是其地址的值，而非其 Reference （C++ 就可以做到传 Reference），
那么我们如果想做到：
(1) 不仅形参的值的改变，能影响原输入变量的值；
(2) 而且形参所指向的object改变的话，也能影响原输入变量所指向的 object ！！！
    就像上面那个例子那样，形参 two 指向了新建的 Node three 以后，原来输入的 one 所指向的 Node 也要变成 three ！！！

要做到这么牛逼的效果，有2种常用的方法。总的思路都是  在  外  面  再  包  一  层：

(1) 做一个长度为1的数组，把要传入的参数装进去再传入。比如建一个 Node[] nodeArray = new Node[1]，然后把 one 装进去，
    然后把 nodeArray 而非 one 作为参数传到 helper function 里去。

(2) 自定义一个 Result Class，把要传入的参数作为一个 field 放到这个class里，再把这个class作为参数传到 helper function 里去  */
