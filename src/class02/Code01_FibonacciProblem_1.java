package class02;

public class Code01_FibonacciProblem_1 {

    public static int f2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int res = 1;
        int pre = 1;
        int tmp = 0;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    public static int f3(int n) {
        if (n < 1) {
            return 0;
        }

        int[][] tmp = new int[2][2];
        int[][] res = new int[2][2];
        res[0][0] = 1;
        res[1][1] = 1;
        tmp[0][0] = 1;
        tmp[0][1] = 1;
        tmp[1][0] = 1;

        int p = n-2;
        for(; p != 0; p >>=1) {
            if((p & 1) != 0) {
                res = multi(res, tmp);
            }
            tmp = multi(tmp, tmp);
        }

        return res[0][0] + res[1][0];


    }

    public static int[][] multi(int[][] a, int[][] b) {

        int[][] res = new int[a.length][b[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                for (int k = 0; k < b.length; k++) {
                    res[i][j] += a[i][k] * b[k][j];
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 19;
        System.out.println(f2(n));
        System.out.println(f3(n));
        System.out.println("===");

    }
}
