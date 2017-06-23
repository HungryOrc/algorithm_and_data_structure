/* There is a wooden stick with length L >= 1, we need to cut it into pieces, where the cutting positions are defined in an int array A. 
The positions are guaranteed to be in ascending order in the range of [1, L - 1]. 
The cost of each cut is the length of the stick segment being cut. 
Determine the minimum total cost to cut the stick into the defined pieces.

Examples:
L = 10, A = {2, 4, 7}, the minimum total cost is 10 + 4 + 6 = 20 (cut at 4 first then cut at 2 and cut at 7)

这一题的特点：
在一个木头上的任何位置切一刀，这一刀的cost都和本段木头在切割前的长度成正比
所以，按不同的顺序切开一个木头，最后的累计总cost是不同的
然后这一题要求在所有标记的长度上最终都必须切开。比如上面的例子，是要求最终在2，4, 7这三个位置上都要切开
