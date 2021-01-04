package class02;

public class Code01_FibonacciProblem_1 {

    public static int f1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }

        return f1(n - 1) + f1(n - 2);
    }

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
            res += pre;
            pre = tmp;
        }

        return res;
    }

    public static int f3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[][] base = new int[][]{
                {1, 1},
                {1, 0}
        };

        int[][] res = matrixPower(base, n - 2);
        return res[0][0] + res[0][1];
    }

    public static int[][] matrixPower(int[][] base, int m) {
        int[][] res = new int[base.length][base.length];
        for (int i = 0; i < base.length; i++) {
            res[i][i] = 1;
        }
        int[][] tmp = base;
        for (; m != 0; m >>= 1) {
            if ((m & 1) == 1) {
                res = matrixMulti(res, tmp);
            }
            tmp = matrixMulti(tmp, tmp);
        }
        return res;
    }

    public static int[][] matrixMulti(int[][] m1, int[][] m2) {
        int[][] res = new int[m1.length][m2[0].length];
        for (int i = 0; i < m1.length; i++) {
            for (int j = 0; j < m2[0].length; j++) {
                for (int k = 0; k < m2.length; k++) {
                    res[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return res;
    }

    public static int s1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        return s1(n-1) + s1(n-2);
    }

    public static int s2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int pre = 1;
        int res = 2;
        int tmp = 0;
        for (int i = 3; i <= n; i++) {
            tmp = res;
            res = res + pre;
            pre = tmp;
        }
        return res;
    }

    public static int s3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        int[][] base = new int[][]{
                {1, 1},
                {1, 0}
        };
        int[][] res = matrixPower(base, n-2);

        return 2 * res[0][0] + res[1][0];
    }

    public static int c1(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 3) {
            return 3;
        }

        return c1(n-1) + c1(n-3);
    }

    public static int c2(int n) { //TODO
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 3) {
            return 3;
        }

        int pre = 1;
        int res = 3;
        int tmp = 0;
        for (int i = 4; i < n; i++) {
            tmp = res;
            res = res + pre;

            pre = tmp;
        }
        return res;
    }

    public static int c3(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        if (n == 3) {
            return 3;
        }

        int[][] base = {
                { 1, 1, 0 },
                { 0, 0, 1 },
                { 1, 0, 0 } };

        int[][] res = matrixPower(base, n-3);
        return 3*res[0][0] + 2*res[1][0] +  res[2][0];
    }

    public static void main(String[] args) {
        int n = 19;
        System.out.println(f1(n));
        System.out.println(f2(n));
        System.out.println(f3(n));
        System.out.println("===");

        System.out.println(s1(n));
        System.out.println(s2(n));
        System.out.println(s3(n));
        System.out.println("===");

        System.out.println(c1(n));
        System.out.println(c2(n));
        System.out.println(c3(n));
        System.out.println("===");
    }
}
