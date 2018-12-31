package SimpleHuaRongDao.FirstTry;


public class Main {

    public static void main(String[] args) {

        int[][] matrix1 = {
                {3, 5, 0},
                {1, 2, 4},
                {6, 7, 8},
        };

        FirstTry FT= new FirstTry(matrix1);
        FT.print();
    }
}