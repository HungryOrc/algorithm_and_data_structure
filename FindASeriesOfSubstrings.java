/* 一个txt文件，里面有若干行，每行是一个String。这个文件用一个 ArrayList<String> 来表示。
然后从 buffer 读进来一系列的字符段，每个字符段看成一个Substring，从buffer读进来的所有内容也用一个 ArrayList<String> 表示。
要在文件的各行里找这些Substring。具体的要求是：
*/


import java.io.*;
import java.util.*;

class FindASeriesOfSubstrings {
  
  public static int lineOfTheFirstBuffer;
  
  public static boolean fileContains(ArrayList<String> fileContent, ArrayList<String> bufferStrings ) {	  
    return findAllSubStrings(fileContent, 0, bufferStrings, 0);
  }
  
  private static boolean findAllSubStrings(ArrayList<String> fileContent, int i,
                					ArrayList<String> bufferStrings, int j) {
    
    if (j == bufferStrings.size() && i <= fileContent.size()) {
      return true;
    }
    
    if (j <= bufferStrings.size() && i == fileContent.size()) {
      return false;
    }

    if (j == 0) {
      lineOfTheFirstBuffer = i;
    }  
    
    // 算法的核心在于下面这个 if 语句 ！！！
    if (findSubstring(fileContent.get(i), bufferStrings.get(j))) {
      return findAllSubStrings(fileContent, i + 1, bufferStrings, j + 1);
    } else {
      return findAllSubStrings(fileContent, lineOfTheFirstBuffer + 1, bufferStrings, 0);
    }
  }
  
  private static boolean findSubstring(String s, String sub) {

    for (int i = 0; i < s.length() - sub.length() + 1; i++) {
      int j = 0;
      for (; j < sub.length(); j++) {
        if (s.charAt(i + j) != sub.charAt(j)) {
          break;
        }
      }
      
      if (j == sub.length()) {
        return true;
      }
    }
    
    return false;
  }
  
  public static void main(String[] args) {
    
    ArrayList<String> fileContent = new ArrayList<String>();
    fileContent.add("aaa");
    fileContent.add("bbbb");
    fileContent.add("ccc");
    fileContent.add("dddd");
    fileContent.add("eeeeee");

    ArrayList<String> bufferStrings = new ArrayList<String>();
    bufferStrings.add("bbb");
    bufferStrings.add("ccc");
    bufferStrings.add("dd");

    System.out.println(fileContains(fileContent, bufferStrings)); // true
    
    fileContent.set(1, "bbb");
    System.out.println(fileContains(fileContent, bufferStrings)); // true
    
    bufferStrings.set(1, "bbbbbbbb");
    System.out.println(fileContains(fileContent, bufferStrings)); // false
  }
}
