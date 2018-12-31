package SimpleHuaRongDao.DFS;

public class Main {

    public static void main(String[] args) {

        int[][] matrix1 = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 8},
        };
        HuaRongDao Hrd = new HuaRongDao(matrix1);
        Hrd.solve();
        Hrd.printRoute();
        Hrd.print();

        System.out.println();
        int[][] matrix2 = {
                {1, 2, 3},
                {4, 0, 6},
                {7, 5, 8},
        };
        HuaRongDao Hrd2 = new HuaRongDao(matrix2);
        Hrd2.solve();
        Hrd2.printRoute();
        Hrd2.print();

        System.out.println();
        int[][] matrix3 = {
                {3, 4, 1},
                {5, 6, 0},
                {8, 2, 7},
        };
        HuaRongDao Hrd3 = new HuaRongDao(matrix3);
        Hrd3.solve();
        Hrd3.printRoute();
        Hrd3.print();
    }
}