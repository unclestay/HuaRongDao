package OptimizationAlgorithm.DFS;

import java.util.ArrayList;
import java.util.List;


public class DeepFirstSearch {

    // define best(i,j) and M N
    private int[][] best = null;
    private int M = 0;
    private int N = 0;

    // define the constant of direction
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    // current path
    private List<Integer> curPath = null;
    // record the current best path
    private Integer[] bestPath = null;

    // current search point
    private int curX = 0;
    private int curY = 0;
    // current total searched value
    private int value = 0;
    // the recorded best value
    private int maxValue = 0;

    // go in a direction
    private void goDir(int dir, int[][] matrix) {
        // move down
        if(dir == DOWN) {
            curX++;
            value += matrix[curX][curY];
        }
        // move right
        else if(dir == RIGHT) {
            curY++;
            value += matrix[curX][curY];
        }
        // record the moving direction
        curPath.add(dir);
    }

    // move backward
    private void goBackDir(int dir, int[][] matrix) {
        if(dir == DOWN) {
            value -= matrix[curX][curY];
            curX--;
        } else if(dir == RIGHT) {
            value -= matrix[curX][curY];
            curY--;
        }
        // remove the previous direction
        curPath.remove(curPath.size() - 1);
    }

    // deep searching
    private void search(int dir, int[][] matrix) {
        // move forward for a direction
        goDir(dir, matrix);
        // reach the end point
        if(curX == M - 1 && curY == N - 1) {
            if(value > maxValue) {
                // record the best value and path
                maxValue = value;
                bestPath = new Integer[curPath.size()];
                curPath.toArray(bestPath);
            }
        } else if(value <= best[curX][curY]) {
            // stop searching ,wait for moving back and pruning
        } else {
            // update the best(i,j) and record it
            best[curX][curY] = value;
            // moving to the next direction
            if(curX < M - 1) {
                search(DOWN, matrix);
            }
            if(curY < N - 1) {
                search(RIGHT, matrix);
            }
        }
        // move backward
        goBackDir(dir, matrix);
    }

    // get the maximum value
    public int getMaxAward(int[][] matrix) {
        // initialize
        value = matrix[0][0];
        M = matrix.length;
        N = matrix[0].length;
        best = new int[M][N];
        curPath = new ArrayList<Integer>();
        // start searching
        if(M > 1) {
            search(DOWN, matrix);
        }
        if(N > 1) {
            search(RIGHT, matrix);
        }
        // maximum value
        return maxValue;
    }

    // print the best Path
    public void printBestPath() {
        for(int i = 0; i < bestPath.length; i++) {
            if(bestPath[i] == RIGHT) {
                System.out.print("right ");
            } else { 
                System.out.print("down ");
            }
        }
        System.out.println();
    }

}
