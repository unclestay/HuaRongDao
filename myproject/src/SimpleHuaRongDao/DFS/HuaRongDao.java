package SimpleHuaRongDao.DFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class HuaRongDao {

    // define the direction
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int UP = 3;
    private static final int DOWN = 4;

    // 3x3 matrix
    private int[][] arr;

    // record the blank space
    private int x;
    private int y;

    // define the moving array
    private List<Integer> moveArr = new LinkedList<Integer>();

    // define the final state
    private static final Integer WIN_STATE = 123456780;

    // store the searched state
    private Set<Integer> statusSet = new HashSet<Integer>();

    // initialize, using 0 to represent the blank space and find it
    public HuaRongDao(int[][] arr) {
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
    private boolean canMove(int direction) {
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

    //  move in a direction without judgment
    //  using canMove method first
    private void move(int direction) {
        int temp;
        switch (direction) {
            // exchange the left number and the space
            case LEFT:
                temp = arr[x][y - 1];
                arr[x][y - 1] = 0;
                arr[x][y] = temp;
                y = y - 1;
                break;
            // 空格和右侧数字交换
            case RIGHT:
                temp = arr[x][y + 1];
                arr[x][y + 1] = 0;
                arr[x][y] = temp;
                y = y + 1;
                break;
            // 空格和上方数字交换
            case UP:
                temp = arr[x - 1][y];
                arr[x - 1][y] = 0;
                arr[x][y] = temp;
                x = x - 1;
                break;
            // 空格和下方数字交换
            case DOWN:
                temp = arr[x + 1][y];
                arr[x + 1][y] = 0;
                arr[x][y] = temp;
                x = x + 1;
                break;
        }
        // 该方向记录
        moveArr.add(direction);
    }

    // backward of a direction without judgment
    // using canMove method first
    private void moveBack(int direction) {
        int temp;
        switch (direction) {
            // 空格和左侧数字交换
            case LEFT:
                temp = arr[x][y + 1];
                arr[x][y + 1] = 0;
                arr[x][y] = temp;
                y = y + 1;
                break;
            // 空格和右侧数字交换
            case RIGHT:
                temp = arr[x][y - 1];
                arr[x][y - 1] = 0;
                arr[x][y] = temp;
                y = y - 1;
                break;
            // 空格和上方数字交换
            case UP:
                temp = arr[x + 1][y];
                arr[x + 1][y] = 0;
                arr[x][y] = temp;
                x = x + 1;
                break;
            // 空格和下方数字交换
            case DOWN:
                temp = arr[x - 1][y];
                arr[x - 1][y] = 0;
                arr[x][y] = temp;
                x = x - 1;
                break;
        }
        // 记录的移动步骤出栈
        moveArr.remove(moveArr.size() - 1);
    }

    // get the state  that can determine the matrix
    // here we transform it to a tens digit
    private Integer getStatus() {
        int status = 0;
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr.length; j++) {
                status = status * 10 + arr[i][j];
            }
        }
        return status;
    }

    // searching method
    private boolean search(int direction) {
        // if can move && avoid stack overflow
        if(canMove(direction)&& moveArr.size()<100) {
            // move in this direction
            move(direction);
            // state after moving
            Integer status = getStatus();
            // if reaching the final state, return true
            if(WIN_STATE.equals(status)) {
                return true;
            }
            // if reaching previous state, return false
            if(statusSet.contains(status)) {
                // this is a false move, move back
                moveBack(direction);
                return false;
            }
            // store the current state to statusSet
            statusSet.add(status);
            // continue search in four directions
            boolean searchFourOk = search(RIGHT) || search(DOWN) || search(LEFT) || search(UP);
            if(searchFourOk) {
                return true;
            } else {
                // this is a wrong movement, remove its record.
                moveBack(direction);
                return false;
            }
        }
        return false;
    }

    // solve method
    public boolean solve() {
        Integer status = getStatus();
        // if we are in the final state, return true
        if(WIN_STATE.equals(status)) {
            return true;
        }
        // record the first state
        statusSet.add(status);
        // search in four directions
        return search(RIGHT) || search(DOWN) || search(LEFT) || search(UP);
    }

    // print the moving path
    public void printRoute() {
        for(int i=0; i<moveArr.size(); i++) {
            System.out.print(getDirString(moveArr.get(i)));
            System.out.print(" ");
        }
        System.out.println();
    }

    // get the direction and its relative string
    private String getDirString(int dir) {
        switch (dir) {
            case LEFT:
                return "left";
            case RIGHT:
                return "right";
            case UP:
                return "up";
            case DOWN:
                return "down";
        }
        return null;
    }

    // print the current state
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
