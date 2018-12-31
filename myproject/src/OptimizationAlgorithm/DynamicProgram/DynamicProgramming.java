package OptimizationAlgorithm.DynamicProgram;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaoshi on 2018/10/20.
 */
public class DynamicProgramming {

    // define best(i,j) and M N
    private int[][] best = null;
    private int M = 0;
    private int N = 0;

    // define the constant of direction
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    // store the best path
    private List<Integer> bestPath = null;

    // calculate best(i,j)
    private void calcDp(int[][] matrix) {
        // initialize
        M = matrix.length;
        N = matrix[0].length;
        best = new int[M][N];
        // calculate
        for(int i = 0; i < M; i++) {
            for(int j = 0; j < N; j++) {
                // boundary
                if(i == 0 && j == 0) {
                    best[i][j] = matrix[i][j];
                } else if(i == 0) {
                    best[i][j] = best[i][j - 1] + matrix[i][j];
                } else if(j == 0) {
                    best[i][j] = best[i - 1][j] + matrix[i][j];
                } else {
                    // state transition
                    best[i][j] = Math.max(best[i - 1][j], best[i][j - 1]) + matrix[i][j];
                }
            }
        }
    }

    // get the maximum value
    public int getMaxAward(int[][] matrix) {
        // calculate state
        calcDp(matrix);
        // calculate the best path
        calcBestPath();
        // return the best value
        return best[matrix.length - 1][matrix[0].length - 1];
    }

    // Calculate the best path from back to front
    private void calcBestPath() {
        bestPath = new ArrayList<Integer>();
        // total number of path is M + N - 2
        int curX = M - 1;
        int curY = N - 1;
        // rely on best(i,j) to calculate the best path
        for(int i = 0; i < M + N - 2; i++) {
            if(curX == 0) {
                curY--;
                bestPath.add(RIGHT);
            } else if(curY == 0) {
                curX--;
                bestPath.add(DOWN);
            } else {
                if(best[curX - 1][curY] > best[curX][curY - 1]) {
                    curX--;
                    bestPath.add(DOWN);
                } else {
                    curY--;
                    bestPath.add(RIGHT);
                }
            }
        }
    }

    // print the best path
    public void printBestPath() {
        // backward printing
        for(int i = bestPath.size() - 1; i >= 0; i--) {
            if(bestPath.get(i) == RIGHT) {
                System.out.print("right ");
            } else {
                System.out.print("down ");
            }
        }
        System.out.println();
    }

}
