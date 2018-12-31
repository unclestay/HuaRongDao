package OptimizationAlgorithm.DpCompressed;

/**
 * @author xiaoshi on 2018/10/20.
 */
public class DpCompressed {

    // define best(i) and M N
    private int[][] best = null;
    private int M = 0;
    private int N = 0;

    // calculate best(i)
    private void calcDp(int[][] matrix) {
        // initialize
        M = matrix.length;
        N = matrix[0].length;
        int minMN = Math.min(M, N);
        int maxMN = Math.max(M, N);
        // just need the minimum length of M and N
        best = new int[2][minMN];
        for(int i = 0; i < M + N - 1; i++) {
            // the start X coordinate of the ith diagonal
            int startX = 0;
            // the start Y coordinate of the ith diagonal
            int startY = i;
            // the number of numbers of the ith diagonal
            int number = i + 1;
            if(i >= N) {
                startX = i + 1 - N;
                startY = N - 1;
            }
            if(i >= minMN) {
                number = minMN;
            }
            if(i >= maxMN) {
                number = M + N - i - 1;
            }
            for(int j = 0; j < number; j++) {
                // start point
                if(i == 0 && j == 0) {
                    best[1][j] = matrix[startX + j][startY - j];
                } else {
                    if (i < N) {
                        // first half part
                        if (j == 0) {
                            // Upper boundary
                            best[1][j] = best[0][j] + matrix[startX + j][startY - j];
                        } else if (j == number - 1) {
                            // Left boundary
                            best[1][j] = best[0][j-1] + matrix[startX + j][startY - j];
                        } else {
                            // state transition
                            best[1][j] = Math.max(best[0][j], best[0][j-1]) + matrix[startX + j][startY - j];
                        }
                    } else {
                        // last half part
                        if(i < M && j == number - 1) {
                            // left boundary
                            best[1][j] = best[0][j] + matrix[startX + j][startY - j];
                        } else {
                            // state transition
                            best[1][j] = Math.max(best[0][j], best[0][j+1]) + matrix[startX + j][startY - j];
                        }
                    }
                }
            }
            // copy the state of best[1] to best[0]
            for(int j = 0; j < number; j++) {
                best[0][j] = best[1][j];
            }
        }
    }

    // get the maximum value
    public int getMaxAward(int[][] matrix) {
        calcDp(matrix);
        return best[0][0];
    }

}