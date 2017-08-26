/* 本题的出处：https://www.codechef.com/problems/BYTESE

2个String s1 和 s2，它们的一个所谓的 “Shuffle”，设为 sff，必须满足如下的要求：
1. sff 含有的chars，正好等于 s1 和 s2 所含有的chars的并集，一个char也不能多一个char也不能少
   注意，s1和s2内部各自可能有重复char，s1和s2之间也可能有重复char，s1和s2本身可能就是相同的String
2. s1 和 s2 内部分别的chars，在 sff 里出现的前后相对顺序，必须保留它们各自在 s1 和 s2 里的前后相对顺序
   如果 sff 里比如说有好几个 'A'，那么我们不care其中的每一个'A'到底是原来属于 s1 还是 s2，
   只要通过某一种归属，能符合这些 'A' 和其它字母的相对顺序能同时满足 s1 和 s2 的原相对顺序，我们就认为 sff 是一个有效的 Shuffle
   
For example, the strings HELLO and WORLD can be shuffled to form HWEOLRLLOD, or HEWORLLLDO, or perhaps simply HELLOWORLD. 
It is not a shuffle if the original order of letters is not preserved. 
For example, the D in WORLD cannot ever appear before the R after being shuffled. 
This means that EHLLOWRDLO, for instance, is not a shuffle of HELLO and WORLD, 
even though it contains all the original letters. 

接下来，我们有所谓的 “Twin Shuffle” 的概念：
A string is a shuffle of twins if it can be formed by shuffling two identical strings.
For example, ABACBDECDE is a shuffle of twins because it can be formed by shuffling ABCDE and ABCDE. 
DBEACBCADE is not a shuffle of twins because it cannot be formed by shuffling two identical strings.

Input:
String named "input": the Shuffled string.

Output:
If the input String is actually a valid Shuffled String of 2 Twin Strings,
Output one of the two identical strings that make up the input string.

If the no such pair of identical strings exist, print "Twins don't exist". (quotes only for clarity).

Assumptions:
1. All the strings will contains only UPPERCASE letters.
2. 

In case, no twins exist output “Twins don’t exist” (quotes only for clarity).





Constraints
1 <= T <= 10

2 <= N <= 106

Example
Input:
2
ABACBDECDE
DBEACBCADE

Output:
ABCDE
Twins don't exist
