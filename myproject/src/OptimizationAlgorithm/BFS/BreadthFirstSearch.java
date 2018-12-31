package OptimizationAlgorithm.BFS;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BreadthFirstSearch {

    private int M = 0;
    private int N = 0;

    // define the direction
    private static final int RIGHT = 1;
    private static final int DOWN = 2;

    // represent every step of BFS and connected by lastItem
    private class SearchItem {
        private int curX;
        private int curY;
        private int dir;
        //record the longest path of (curX, curY) as the best(i,j)
        private int value;
        private SearchItem lastItem;
        public SearchItem(int curX, int curY, int dir, int value) {
            this.curX = curX;
            this.curY = curY;
            this.dir = dir;
            this.value = value;
        }
    }

    // the result
    private SearchItem bestItem = null;

    // stored queue for BFS
    private List<SearchItem> statusToSearch = new LinkedList<SearchItem>();

    // to replace the small item in the queue and return to if we can find the relative item in the search queue
    private boolean replaceStatusList(SearchItem newItem) {
        // whether we find it
        boolean find = false;
        // search the item
        for(int i=0; i<statusToSearch.size(); i++) {
            SearchItem searchItem = statusToSearch.get(i);
            if(searchItem.curX == newItem.curX && searchItem.curY == searchItem.curY) {
                find = true;
                // compare the best(i,j)
                if(searchItem.value < newItem.value) {
                    // replaced by the better item
                    statusToSearch.remove(i);
                    statusToSearch.add(i, newItem);
                }
                break;
            }
        }
        return find;
    }

    // Bread First Search
    private void search(int[][] matrix) {

        // Not Null in the search queue
        while(statusToSearch.size() > 0) {
            // get an item from the search queue
            SearchItem searchItem = statusToSearch.remove(0);
            int curX = searchItem.curX;
            int curY = searchItem.curY;
            int curValue = searchItem.value;
            // if we have found it
            if(curX == M - 1 && curY == N - 1) {
                bestItem = searchItem;
            }
            // search down
            if(curX < M - 1) {
                SearchItem newItem = new SearchItem(curX + 1, curY, DOWN, curValue + matrix[curX + 1][curY]);
                newItem.lastItem = searchItem;
                // whether we need to replace it
                if(!replaceStatusList(newItem)) {
                    // if not found in the search queue, add it
                    statusToSearch.add(newItem);
                }
            }
            // search right
            if(curY < N - 1) {
                SearchItem newItem = new SearchItem(curX, curY + 1, RIGHT, curValue + matrix[curX][curY + 1]);
                newItem.lastItem = searchItem;
                // replace check
                if(!replaceStatusList(newItem)) {
                    // if not found in the search queue, add it
                    statusToSearch.add(newItem);
                }
            }
        }
    }

    // get the maximum award
    public int getMaxAward(int[][] matrix) {
        // initialize
        M = matrix.length;
        N = matrix[0].length;
        int value = matrix[0][0];
        SearchItem searchItem = new SearchItem(0, 0, 0, value);
        statusToSearch.add(searchItem);
        // start searching
        search(matrix);
        // return to the best result
        return bestItem.value;
    }

    // print the best path
    public void printBestPath() {
        List<Integer> dirList = new ArrayList<Integer>();
        SearchItem curSearchItem = bestItem;
        // find the path from the lastItem
        while(null != curSearchItem) {
            int dir = curSearchItem.dir;
            if(dir == DOWN) {
                dirList.add(DOWN);
            } else if(dir == RIGHT) {
                dirList.add(RIGHT);
            }
            curSearchItem = curSearchItem.lastItem;
        }
        // print the path
        for(int i = dirList.size() - 1; i >= 0; i--) {
            if(dirList.get(i) == DOWN) {
                System.out.print("down ");
            } else if(dirList.get(i) == RIGHT) {
                System.out.print("right ");
            }
        }
        System.out.println();
    }

}
