/* The API: int read4(char *buf) reads 4 characters at a time from a file.
The return value is the actual number of characters read. 
For example, it returns 3 if there is only 3 characters left in the file.
By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.

The read4 API is defined in the parent class Reader4 as:
   int read4(char[] buf); 
The method "read4" reads 4 characters from a file into "buf" char array, 
and implement a function that reads n characters from the file into "buf" char array

Note: The read function will only be called once for each test case. */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    public int read(char[] buf, int n) {
        
        int charsRead_ThisTime = 0;
        int charsWritten_ThisTime = 0;
        int charsWritten_Total = 0;
        char[] curChars = new char[4];
        boolean fileEnded = false;
        
        while (charsWritten_Total < n && !fileEnded)
        {
            // 把代表此次读取的(最多4个)chars的char数组放到 read4 函数的参数里！就能给它赋值了
            charsRead_ThisTime = read4(curChars); 
            if (charsRead_ThisTime < 4)
                fileEnded = true;
            
            charsWritten_ThisTime = Math.min(charsRead_ThisTime, n - charsWritten_Total);
            for (int i = 0; i < charsWritten_ThisTime; i++)
                buf[charsWritten_Total + i] = curChars[i];
                
            charsWritten_Total += charsWritten_ThisTime;
        }
        return charsWritten_Total;
    }
}
