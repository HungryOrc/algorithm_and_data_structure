/* Java ALWAYS pass by value, NEVER pass by reference. 
This is also true for Objects. When passing an Object to a method as its parameter, 
Java is actually passing the <strong>value of the address</strong> of the Object, <strong>not the reference</strong> of the Object. */

public static void main(String[] args) {   	
  Object x = new ...
  function(x);
  ...
}

private void function(Object y) {
  y ...
}
