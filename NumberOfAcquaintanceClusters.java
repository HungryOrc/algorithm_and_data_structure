/* 一个熟人的网络，用矩阵表示，1表示认识，0表示不认识。自然这个矩阵是关于从左上到右下的对角线而对称的
然后相互认识的人组成一个圈子，叫cluster，给一个矩阵，问一共有多少个clusters

思路：用dfs做。有点像 number of islands，但又很不相同
因为这个认识网的关系，在矩阵里并不表现为视觉上的联通！
比如 1 和 2 认识，1 也 和 4 认识，但是在 1 这一行里，2 和 4 这两列可不是连在一起的！*/

public class AcquaintanceCluster {

    public static int numOfClusters;    

    public static int acquaintanceCluster(String[] acquaintanceNetwork) {
        if (acquaintanceNetwork == null || acquaintanceNetwork.length == 0) {
            return 0;
        }

        numOfClusters = 0;
        
        int n = acquaintanceNetwork.length;        
        int[][] matrix = new int[n][n];
        
        // 只转录半个矩阵就行了，含从左上到右下的对角线。因为矩阵是对于对角线对称的
        for (int i = 0; i < n; i++) {
            String s = acquaintanceNetwork[i];           
            for (int j = i; j < n; j++) {
                matrix[i][j] = s.charAt(j) - '0';
            }
        }
        
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                
                if (matrix[i][j] != 0) {
			
	            // 这个函数的作用是把所有和 [i][j] 属于同一cluster的 标为1 的cell都 改标为0
	            // 它会把 第i行里的所有 等于1的 j列 的cell 都标为0，在此过程中，不断recurse：
		    // 进一步查看这里面的每一个j所代表的行（行j）里的所有为1的列，都改为0；然后继续 recurse下去
		    // 所以下面这句完成以后，就不必再在i行里寻找1了，一定没有了，所以可以break，到 i+1 行去
                    removeConnections_DFS(matrix, i, j);
                    numOfClusters ++;
			
		    break;
                }
            }
        }
        
        return numOfClusters;
    }

    private static void removeConnections_DFS(int[][] matrix, int i, int j) {
        matrix[i][j] = 0;
        
        for (int k = j; k < matrix.length; k++) {
            if (matrix[j][k] != 0) {
            	removeConnections_DFS(matrix, j, k);
            }
        }
    }
	
	public static void main(String[] args) {
		
		String[] input = new String[5];
		input[0] = "10001";
		input[1] = "01100";
		input[2] = "01100";
		input[3] = "00010";
		input[4] = "10001";
		
		System.out.println(acquaintanceCluster(input)); 
		// answer: 3 clusters
		// {0, 4}, {1, 2}, {3}
	}
}
