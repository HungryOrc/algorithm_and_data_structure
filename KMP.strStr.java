/*
KMP 算法讲解
用 O(n) 时间完成在一个 String 里寻找一个 Sub String 的算法
https://en.wikipedia.org/wiki/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm

Rollback Array T 举例：
i		00	01	02	03	04	05	06	07	08	09	10	11	12	13	14	15	16	17	18	19	20	21	22	23
W[i]	P	A	R	T	I	C	I	P	A	T	E		I	N		P	A	R	A	C	H	U	T	E
T[i]	-1	0	0	0	0	0	0	0	1	2	0	0	0	0	0	0	1	2	3	0	0	0	0	0

 */
public class KMP_Algorithm {

	// source: the string in which we look for the substring
	// word: the substring, or namely the word, we are looking for
	// return: an integer, the zero-based position in source at which the word is found
	// returning -1 means no occurrence of the word in the source
	public static int KMP(String source, String word)
	{
      // leave this trivial case just for leetcode test cases...
      // if (needle.equals(""))
          // return 0;
            
		  int m = 0; // the beginning of the current match in source
	    int i = 0; // the position of the current character in the word
	    int[] T = new int[word.length()]; // the "rollback array" of the word
	    findRollbackArray(word, T); // pupulate the array T
	    
	    char[] w = word.toCharArray();
	    char[] s = source.toCharArray();

	    while (i < w.length && m + i < s.length)
	    {
	        if (w[i] == s[m + i])
	        {
	        	// reached the end of word, namely the search was successful
	            if (i == w.length - 1) 
	                return m;

	            i ++;
	        }
	        else // w[i] != s[m+i]
	        {
	            if (T[i] > -1) // not the first char in the word
	            {
	            	// reset the starting point of the search in the source
	            	m = m + i - T[i];
	            	// reset the current char to be compared in the word
	            	i = T[i];
	            }
	            else
	            {
	            	m ++;
	            	i = 0;
	            }
	        }
	    }       
	    // if we reach here, we have searched all of S unsuccessfully)
	    return -1; // -1 means failure
	}
	
	// 计算任何一个给定的String: word 的 Rollback Array: T
	public static void findRollbackArray(String word, int[] T)
	{
		// the current position we are computing in T
		int pos = 2; 
		
		/* 我们当前在 word 里处理的 char，再往前的一个 char 是与 index 为 cnd 的word里的char作比较
		比如下面这个 Rollback Array:
		i		00	01	02	03	04	05	06	07	08	09	10	11	12	13	14	15	16	17	18	19	20	21	22	23
		W[i]	P	A	R	T	I	C	I	P	A	T	E		I	N		P	A	R	A	C	H	U	T	E
		T[i]	-1	0	0	0	0	0	0	0	1	2	0	0	0	0	0	0	1	2	3	0	0	0	0	0
		这里面的 17 号char为R，R的前一个char是A，A的T[i]等于1，这个 1 就是 R 的 cnd */
		int cnd = 0; 
		
		if (T.length >= 1)
			T[0] = -1;
		if (T.length >= 2)
			T[1] = 0;
		if (T.length <= 2)
			return;

		char[] w = word.toCharArray();
		
		while (pos < w.length)
		{
	        //first case: the substring continues
	        if (w[pos-1] == w[cnd])
	        {
	            T[pos] = cnd + 1;
	            cnd++;
	            pos++;
	        }
	        // second case: it can not continue, but we can fall back
	        else if (cnd > 0) // and w[pos-1] != w[cnd]
	        {
	        	// 全算法的最精华在这一句！！！
	        	// 对比的index(即cnd)，被对比的index的rollback量(即T[cnd])所代替！！！
	            cnd = T[cnd];
	            
	            T[pos] = 0;
	            
	            // So we do NOT increase pos here! Since we are not done with pos yet!
	        }
	        // third case: we have run out of candidates, note that we now have cnd == 0
	        else // w[[pos-1] != w[cnd] && cnd == 0
	        {
	        	T[pos] = 0;
	        	pos++;
	        }
		}
	}
	
	
	public static void main(String[] args) {
		
		String source = "TRY PARTICIPATE IN PARACHUTE, IT WILL THROW THE GUT OUT OF YOU!";
		String word = "PARTICIPATE IN PARACHUTE";
		
		int[] T = new int[word.length()];
		findRollbackArray(word, T);
		
		System.out.println("The Rollback Array of the given word \"" + word + "\" is:");
		for (int i : T)
			System.out.print(i + " ");
		System.out.println();
		
		System.out.println("The index of the 1st occurence of the word in the source is: "
				+ KMP(source, word));
	}

}
