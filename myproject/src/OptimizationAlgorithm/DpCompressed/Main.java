package OptimizationAlgorithm.DpCompressed;

/**
 * @author xiaoshi on 2018/10/20.
 */
public class Main {

    public static void main(String[] args) {

        int[][] matrix1 = {
                {300, 500, 560, 400, 160},
                {1000, 100, 200, 340, 690},
                {600, 500, 500, 460, 320},
                {300, 400, 250, 210, 760}
        };

        int[][] matrix2 = {
                {300, 500, 2560, 400},
                {1000, 100, 200, 340},
                {600, 500, 500, 460},
                {300, 400, 250, 210},
                {860, 690, 320, 760}
        };

        DpCompressed dp = new DpCompressed();

        System.out.println(dp.getMaxAward(matrix1));

        System.out.println(dp.getMaxAward(matrix2));

    }
}
