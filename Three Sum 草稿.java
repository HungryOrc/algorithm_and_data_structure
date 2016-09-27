要求返回的是三个数的升序组合，不要求下标所以可以利用上一题的两种方法，要求元组(a, b, c)使得a + b + c = 0，对任意一个元素A[i],只要判断数组中是否存在A[j] + A[k] = target - A[i]，这样便可以转换成 Two sum问题了。由于题目要求返回的集合无重复且元组顺序非递减，可以先对数组进行排序，而后转换成 Two sum 问题。代码如下：

    public List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(num);
        int n = num.length;
        for (int i = 0; i <= n - 3; i++) {
            int j = i + 1;
            int k = n - 1;

            while (j < k) {
                if (num[i] + num[j] + num[k] == 0) {
                    List<Integer> tmp = new ArrayList<Integer>();
                    tmp.add(num[i]);
                    tmp.add(num[j]);
                    tmp.add(num[k]);
                    result.add(tmp);
                    //skip duplicates
                    do {
                        j++;
                    } while (j < k && num[j-1] == num[j]);
                    //skip duplicates
                    do {
                        k--;
                    } while (k > j && num[k] == num[k+1]);

                } else if (num[i] + num[j] + num[k] < 0) {
                    j++;
                } else {
                    k--;
                }
            }
            //skip duplicates
            while (i + 1 <= n - 3 && num[i] == num[i+1])
                i++;
        }

        return result;
    }
该算法的时间复杂度为O(n2)O(n2)
