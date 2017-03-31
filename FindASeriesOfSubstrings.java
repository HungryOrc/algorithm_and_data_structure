/* 一个txt文件，里面有若干行，每行是一个String。这个文件用一个 ArrayList<String> 来表示。
然后从 buffer 读进来一系列的字符段，每个字符段看成一个Substring，从buffer读进来的所有内容也用一个 ArrayList<String> 表示。
要在文件的各行里找这些Substring。具体的要求是：
比如buffer读进来的Substrings一共有3个，“aa”、“bbb”和“c”，则必须满足类似如下条件才算在文件里找到了所有这些Substring：
文件里的某一个第 i 行（文件里的一行用ArrayList里的一个String来指代）含有 “aa”，并且 
文件里的第 i + 1 行 含有 “bbb”，并且 
文件里的第 i + 2 行 含有 “c” 

我的思路：
很朴素。从文件的第一行开始，逐行往下找。记录下文件里match第一个buffer String的行数，比如是行 k，
然后 k+1 行与第二个buffer String比，k+2 行与第三个buffer String比…… 
如果没比到buffer ArrayList的末尾就崩了，
那就从文件的第 k+1 行重新开始，比第一个 buffer String，然后 k+2 行比第二个 buffer String…… */

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
