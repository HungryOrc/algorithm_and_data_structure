/* There is a wooden stick with length L >= 1, we need to cut it into pieces, where the cutting positions are defined in an int array A. 
The positions are guaranteed to be in ascending order in the range of [1, L - 1]. 
The cost of each cut is the length of the stick segment being cut. 
Determine the minimum total cost to cut the stick into the defined pieces.

Examples:
L = 10, A = {2, 4, 7}, the minimum total cost is 10 + 4 + 6 = 20 (cut at 4 first then cut at 2 and cut at 7)

这一题的特点：
在一个木头上的任何位置切一刀，
