/* Ref: https://codefights.com/skill-test/H7HxypneNn77zFZn2
Define a tower as a straight vertical segment with a bottom end on the X axis.
Consider some towers in pairwise distinct positions. 
A pair of towers is called visible if the segment that connects the top points of those towers doesn't cross any other tower 
(but may touch the tops of some towers).
Given positions on which the towers stand and heights of the towers, find the number of visible pairs of towers.

[input] array.integer position
Constraints: 3 ≤ position.length ≤ 10, -1000 ≤ position[i] ≤ 1000.
[input] array.integer height
Array of positive integers. ith elements of both arrays correspond to the same tower.
Constraints: height.length = position.length, 1 ≤ height[i] ≤ 250.

Example
For position = [3, 0, 6, 10] and height = [2, 1, 4, 6], the output should be
countVisibleTowerPairs(position, height) = 5.
Let's number towers from left to right, starting with 1, then these 5 tower pairs will be visible: 
(0, 1), (0, 2), (0, 3), (1, 2), (2, 3). */

// 我的暴力 n^3 办法。一定有更智慧的方法，但我还没想到？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？？
public int countVisibleTowerPairs(int[] position, int[] height) {
    
    // for every row, the 1st element is pos, the 2nd element is height
    int[][] posAndHeight = new int[position.length][2];
    for (int i = 0; i < position.length; i++) {
        posAndHeight[i][0] = position[i];
        posAndHeight[i][1] = height[i];
    }
    
    Arrays.sort(posAndHeight, new Comparator<int[]>() {
        @Override
        public int compare(int[] ph1, int[] ph2) {
            if (ph1[0] < ph2[0]) {
                return -1;
            } else if (ph1[0] > ph2[0]) {
                return 1;
            } else { // ==
                return 0;
            }
        }
    });
    
    // all the adjacent vertical lines form a valid pair
    int result = position.length - 1;
    
    for (int i = 0; i <= position.length - 3; i++) {
        
        for (int j = i + 2; j <= position.length - 1; j++) {
            
            int[] left = posAndHeight[i];
            int[] right = posAndHeight[j];
            
            boolean pokeOut = false;
            for (int k = i + 1; k <= position.length - 2; k++) {
                int[] poke = posAndHeight[k];
                if (pokeOut(left, right, poke)) {
                    pokeOut = true;
                    break;
                }
            }
            // if none of the vertical lines between left and right pokes out higher than the slope formed by left and right
            if (pokeOut == false) {
                result ++;
            }
        }
    }
    
    return result;
}

private boolean pokeOut(int[] left, int[] right, int[] poke) {
    double slope_LeftToRight = (double)(right[1] - left[1]) / (double)(right[0] - left[0]);
    double slope_LeftToPoke = (double)(poke[1] - left[1]) / (double)(poke[0] - left[0]);
    
    if (slope_LeftToPoke > slope_LeftToRight) {
        return true;
    } else { // <=
        return false;
    }
}
