/* Java ALWAYS pass by value, NEVER pass by reference. 
This is also true for Objects. When passing an Object to a method as its parameter, 
Java is actually passing the 地址的值 of the Object, 而非reference of the Object. */

public static void main(String[] args) {   	
  Object x = new ...
  function(x);
  ...
}

private void function(Object y) {
  y ...
}
