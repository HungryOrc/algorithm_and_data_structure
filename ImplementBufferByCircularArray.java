// Ref: http://www.sanfoundry.com/java-program-circular-buffer/

import java.util.Scanner;
 
class CircularBuffer {
    private int maxSize;
    private int front = 0;  
    private int rear = 0;  
    private int bufLen = 0; // 当前buffer里的实际元素个数
    private char[] buf;    
 
    public CircularBuffer(int size) { // Constructor
        maxSize = size;
        front = 0;
        rear = 0;
        bufLen = 0;
        buf = new char[maxSize];
    }

    public void clear() {
        front = 0;
        rear = 0;
        bufLen = 0;
        buf = new char[maxSize];
    }
    
    // 在 buffer 的尾部插入新元素
    public void insert(char c) {
        if (bufLen < maxSize) {
            // 下面这句，用一句话解决了 折返或者不折返 的两种情况 ！！
            rear = (rear + 1) % maxSize;
            buf[rear] = c;
         
            bufLen++;
        } else
            System.out.println("Error: Buffer is full");
    }
    
    // 在 buffer 的头部删除新元素
    public char delete() {
        if (bufLen > 0) {
            // 下面这句，用一句话解决了 折返或者不折返 的两种情况 ！！
            front = (front + 1) % maxSize;
            return buf[front];
         
            bufLen--;
        } else {
            System.out.println("Error: Buffer is empty");
            return ' ';
        }
    }       

}
