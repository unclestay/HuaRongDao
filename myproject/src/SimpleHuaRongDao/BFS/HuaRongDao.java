package SimpleHuaRongDao.BFS;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;


public class HuaRongDao {

    // define direction
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    private static final int UP = 2;
    private static final int DOWN = 3;

    // define the Auxiliary array
    private static final int[][] dxdy = {{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    // 3x3 matrix
    private int[][] arr;

    // record the position of blank space
    private int x;
    private int y;

    // define the moving array
    private List<Integer> moveArr = new LinkedList<Integer>();

    // define the final state
    private static final Integer WIN_STATE = 123456780;

    // save the searched state
    private Set<Integer> statusSet = new HashSet<Integer>();

    // represent every step of BFS and connected by the lastItem
    private class SearchItem {
        private Integer status;
        private Integer dir;
        private SearchItem lastItem;
        SearchItem(Integer status, Integer dir, SearchItem lastItem) {
            this.status = status;
            this.dir = dir;
            this.lastItem = lastItem;
        }
        public Integer getStatus() {return status;}
        public Integer getDir() {return dir;}
        public SearchItem getLastItem() {return lastItem;}
    }

    // storing queue of BFS
    private List<SearchItem> statusToSearch = new LinkedList<SearchItem>();

    // initialize, using 0 to represent the blank space and find it
    public HuaRongDao(int[][] arr) {
        this.arr = arr;
        getXY();
    }

    // get the coordinate of blank space
    private void getXY() {
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

    // get the backward direction
    private int getBackDir(int direction) {
        switch (direction) {
            case LEFT:
                return RIGHT;
            case RIGHT:
                return LEFT;
            case UP:
                return DOWN;
            case DOWN:
                return UP;
        }
        return 0;
    }

    //  move in a direction without judgment
    //  using canMove method first
    private void move(int direction) {
        int temp;
        temp = arr[x + dxdy[direction][0]][y + dxdy[direction][1]];
        arr[x + dxdy[direction][0]][y + dxdy[direction][1]] = 0;
        arr[x][y] = temp;
        x = x + dxdy[direction][0];
        y = y + dxdy[direction][1];
    }

    // moving forward without judgment
    private void moveForward(int direction) {
        move(direction);
        // record this direction
        moveArr.add(direction);
    }

    // moving backward without judgment
    private void moveBack(int direction) {
        move(getBackDir(direction));
        // remove the recorded direction
        moveArr.remove(moveArr.size() - 1);
    }

    // get the state  that can determine the matrix
    // here we transform it to a tens digit
    public Integer getStatus() {
        int status = 0;
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr.length; j++) {
                status = status * 10 + arr[i][j];
            }
        }
        return status;
    }

    // recover  according to the state
    public void recoverStatus(Integer status) {
        for(int i=0; i<arr.length; i++) {
            for(int j=0; j<arr.length; j++) {
                arr[2 - i][2 - j] = status % 10;
                status = status / 10;
            }
        }
        getXY();
    }

    // searching method
    private boolean search() {
        // if still have some kinds of states to search
        while(statusToSearch.size() > 0) {
            // dequeue
            SearchItem item = statusToSearch.remove(0);
            Integer status = item.getStatus();
            // get the win_state
            if(status.equals(WIN_STATE)) {
                // find and record the path
                recordRoute(item);
                return true;
            }
            // recover array , X and Y according to the state
            recoverStatus(status);
            // Traversing in four directions
            for(int i=0; i<4; i++) {
                // if can move
                if(canMove(i)) {
                    // move forward
                    moveForward(i);
                    status = getStatus();
                    // if have searched this kind of states
                    if (statusSet.contains(status)) {
                        moveBack(i);
                        // move back
                        continue;
                    }
                    // new state added and wait to be searched
                    statusSet.add(status);
                    statusToSearch.add(new SearchItem(status, i, item));
                    moveBack(i);
                }
            }
        }
        return false;
    }

    // solve method
    public boolean solve() {
        Integer status = getStatus();
        // if in win_state, return true
        if(WIN_STATE.equals(status)) {
            return true;
        }
        // record initial state
        statusSet.add(status);
        // add initial state to the searching queue
        statusToSearch.add(new SearchItem(status, null, null));
        return search();
    }

    // find the recorded path
    private void recordRoute(SearchItem item) {
        while(null != item.getLastItem()) {
            moveArr.add(0, item.getDir());
            item = item.getLastItem();
        }
    }

    // print the moving path
    public void printRoute() {
        for(int i=0; i<moveArr.size(); i++) {
            System.out.print(getDirString(moveArr.get(i)));
            System.out.print(" ");
        }
        System.out.println();
    }

    // direction and relative string
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
