 import java.util.Scanner;
 
/** Class Circular Buffer **/
class CircularBuffer
{
    private int maxSize;
    private int front = 0;  
    private int rear = 0;  
    private int bufLen = 0;  
    private char[] buf;    
 
    /** constructor **/
    public CircularBuffer(int size) 
    {
        maxSize = size;
        front = rear = 0;
        rear = 0;
        bufLen = 0;
        buf = new char[maxSize];
    }
    /** function to get size of buffer **/
    public int getSize()
    {
        return bufLen;
    }
    /** function to clear buffer **/
    public void clear()
    {
        front = rear = 0;
        rear = 0;
        bufLen = 0;
        buf = new char[maxSize];
    }
    /** function to check if buffer is empty **/
    public boolean isEmpty() 
    {
        return bufLen == 0;
    }
    /** function to check if buffer is full **/
    public boolean isFull() 
    {
        return bufLen == maxSize;
    } 
    /** insert an element **/
    public void insert(char c) 
    {
        if (!isFull() ) 
        {
            bufLen++;
            rear = (rear + 1) % maxSize;
            buf[rear] = c;
        }
        else
            System.out.println("Error : Underflow Exception");
    }
    /** delete an element **/
    public char delete() 
    {
        if (!isEmpty() ) 
        {
            bufLen--;
            front = (front + 1) % maxSize;
            return buf[front];
        }
        else 
        {
            System.out.println("Error : Underflow Exception");
            return ' ';
        }
    }       
    /** function to print buffer **/
    public void display() 
    {
        System.out.print("\nBuffer : ");
        for (int i = 0; i < maxSize; i++)
            System.out.print(buf[i] +" ");
        System.out.println();    
    }
}
