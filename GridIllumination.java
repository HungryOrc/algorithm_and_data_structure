N乘以N的矩阵。左下角是(1, 1)，右上角是(N, N)。
list lamps存了L个灯的坐标。每个灯的照明范围与国际象棋的皇后相同。
一个狗头人小队要随机查看一些cell的照明情况。查看时，要关闭本cell上的灯以及本cell周围的8个cell上的灯（if any），
然后看本cell是否还能被任何别的lamp照到。查完此cell后，再把所有暂时被关掉的灯打开。
list queries 给出我们要查看的M个cells的坐标。
N <= 10^9
L <= 10^5
M <= 10^5
返回一个ArrayList<String>，逐个列出对queries里的各个cell的检查结果，
"LIGHT"表示仍被照明，"DARK"表示无照明。


public ArrayList<String> checkIllumination(long N, lamps, queries) {



}
