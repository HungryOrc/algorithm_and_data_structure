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

上面这个例子，木料一开始是10米长，要求必须在2米，4米，7米这三处都切开
如果第一刀切2米处，则这一刀的cost为10；
然后第二刀切原先的4米处的话，这一刀的cost为8，因为10-2=8；
第三道切原先的7米处，这一刀的cost为6，因为切完第二刀之后，7米处所在的那一截的长度为6
那么总cost就是 10+8+6 = 24    */
