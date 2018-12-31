package SimpleHuaRongDao.FirstTry;
import java.util.LinkedList;
import java.util.List;


public class  FirstTry {

    // define the direction
    public static final int LEFT = 1;
    public static final int RIGHT = 2;
    public static final int UP = 3;
    public static final int DOWN = 4;

    // 3x3 matrix
    private int[][] arr;

    // record the position of blank space
    private int x;
    private int y;

    // define the moving array
    private List<Integer> moveArr = new LinkedList<Integer>();

    // initialize, using 0 to represent the blank space and find it
    public FirstTry(int[][] arr) {
        this.arr = arr;
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr.length; j++) {
                if(arr[i][j] == 0) {
                    x = i;
                    y = j;
                }
            }
        }
    }

    // determine we can move to which direction
    public boolean canMove(int direction) {
        switch (direction) {
            case LEFT:
                return y > 0;
            case RIGHT:
                return y < 2;
            case UP:
                return x > 0;
            case DOWN:
                return x < 2;
        }
        return false;
    }

    // move in a direction without judgment
    // using canMove method first
    public void move(int direction) {
        int temp;
        switch (direction) {
            // exchange to left
            case LEFT:
                temp = arr[x][y - 1];
                arr[x][y - 1] = 0;
                arr[x][y] = temp;
                y = y - 1;
                break;
            // exchange to right
            case RIGHT:
                temp = arr[x][y + 1];
                arr[x][y + 1] = 0;
                arr[x][y] = temp;
                y = y + 1;
                break;
            // exchange to up
            case UP:
                temp = arr[x - 1][y];
                arr[x - 1][y] = 0;
                arr[x][y] = temp;
                x = x - 1;
                break;
            // exchange to down
            case DOWN:
                temp = arr[x + 1][y];
                arr[x + 1][y] = 0;
                arr[x][y] = temp;
                x = x + 1;
                break;
        }
    }

    // print the state of the matrix
    public void print() {
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr.length; j++) {
                System.out.print(arr[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

}
