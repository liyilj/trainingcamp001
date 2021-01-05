package class05;

/**
 * 一个str，只能在str后面添加字符串，使str变成回文串，最短添加几个字符，返回添加的字符
 */
public class Code02_AddShortestEnd_1 {

    public static String shortestEnd(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        char[] str = manacherString(s);

        int[] pArr = new int[str.length];
        int C = 0;
        int R = -1;

        for (int i = 0; i < str.length; i++) {
            pArr[i] = R > i ? Math.min(pArr[2 * C - i], R - i) : 1;
			while (i + pArr[i] < str.length && i - pArr[i] > -1) {
			    if (str[i+pArr[i]] == str[i-pArr[i]]) {
			        pArr[i]++;
                } else {
			        break;
                }
            }
			if (R < i + pArr[i]) {
			    R = i + pArr[i];
			    C = i;
            }
			if (R == str.length) {
			    break;
            }

        }
        int len = C - str.length/2;
        char[] res = new char[len];

        for (int i = 0; i < len; i++) {
            res[len - 1 - i] = str[2 * i +1];
        }

        return String.valueOf(res);

    }

    public static char[] manacherString(String str) {
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length() * 2 + 1];
        int index = 0;
        for (int i = 0; i != res.length; i++) {
            res[i] = (i & 1) == 0 ? '#' : charArr[index++];
        }
        return res;
    }

    public static void main(String[] args) {
        String str1 = "abcd123321";
        System.out.println(shortestEnd(str1));
    }

}
