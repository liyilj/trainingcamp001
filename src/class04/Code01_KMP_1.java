package class04;

public class Code01_KMP_1 {

    public static int getIndexOf(String s, String m) {
        if (s == null || m == null || s.length() < m.length()) {
            return -1;
        }

        char[] str = s.toCharArray();
        char[] match = m.toCharArray();

        int[] next = getNextArray(match);
        int x = 0;
        int y = 0;
        while (x < str.length && y < match.length) {
            if (str[x] == match[y]) {
                x++;
                y++;
            } else if (y > 0) {
                y = next[y];
            } else {
                x++;
            }
        }
        return y == match.length ? x - y : -1;
    }

    public static int[] getNextArray(char[] match) {
        if(match.length == 1) {
            return new int[]{-1};
        }
        int[] res = new int[match.length];
        res[0] = -1;
        res[1] = 0;
        int cn = 0;
        int i = 2;
        while (i < match.length) {
            if (match[cn] == match[i-1]) {
                res[i++] = ++cn;
            } else if(cn > 0) {
                cn = res[cn];
            } else {
                res[i++] = 0;
            }
        }
        return res;
    }
    // for test
    public static String getRandomString(int possibilities, int size) {
        char[] ans = new char[(int) (Math.random() * size) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = (char) ((int) (Math.random() * possibilities) + 'a');
        }
        return String.valueOf(ans);
    }

    public static void main(String[] args) {
        int possibilities = 5;
        int strSize = 20;
        int matchSize = 5;
        int testTimes = 5000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            String str = getRandomString(possibilities, strSize);
            String match = getRandomString(possibilities, matchSize);
            if (getIndexOf(str, match) != str.indexOf(match)) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
