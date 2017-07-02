/* Given three arrays sorted in ascending order. Pull one number from each array to form a coordinate <x,y,z> in a 3D space. 
Find the coordinates of the points that is k-th closest to <0,0,0>.
We are using euclidean distance here.
Assumptions:
The three given arrays are not null or empty
K >= 1 and K <= a.length * b.length * c.length
Return:
a size 3 integer list, the first element should be from the first array, 
the second element should be from the second array and the third should be from the third array

Examples:
A = {1, 3, 5}, B = {2, 4}, C = {3, 6}
The closest is <1, 2, 3>, distance is sqrt(1 + 4 + 9)
The 2nd closest is <3, 2, 3>, distance is sqrt(9 + 4 + 9)  */


/* 思路：min heap
initial state: x = 1, y = 1, z = 1
expansion / generation rule: expand <x, y, z>
    1. generate <x+1, y, z>
    2. generate <x, y+1, z>
    3. generate <x, y, z+1>
termination condition: pop the smallest elements, when the (k-1)-th smallest element is popped our of the heap, 
    then peek heap to get the k-th smallest element  */
