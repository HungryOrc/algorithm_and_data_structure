这道题和 Three sum 思路类似，也是转换成 Two sum, 只要在查找过程中更新当前最接近target的三个数的组合即可，最后返回这三个数的和。代码如下：

    public int threeSumClosest(int[] num, int target) {
        Arrays.sort(num);
        int n = num.length;
        int min_diff = num[0] + num[1] + num[2] - target;
        for (int i = 0; i <= n - 3; i++) {
            int j = i + 1;
            int k = n - 1;

            while (j < k) {
                int cur_diff = num[i] + num[j] + num[k] - target;
                if (Math.abs(cur_diff) < Math.abs(min_diff)) {
                    min_diff = cur_diff;
                }

                if (cur_diff == 0) {
                    return target;

                } else if (cur_diff < 0) {
                    j++;
                } else {
                    k--;
                }
            }

        }

        return min_diff + target;
    }
