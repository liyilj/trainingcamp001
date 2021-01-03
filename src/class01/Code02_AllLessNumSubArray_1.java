package class01;

import java.util.LinkedList;

public class Code02_AllLessNumSubArray_1 {

    public static int getNum(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        LinkedList<Integer> qmax = new LinkedList<>();
        LinkedList<Integer> qmin = new LinkedList<>();
        int res = 0;
        int L = 0;
        int R = 0;
        while (L < arr.length) {
            while (R < arr.length) {
                while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[R]) {
                    qmax.pollLast();
                }
                qmax.addLast(R);
                while (!qmin.isEmpty() && arr[qmin.peekLast()] >= arr[R]) {
                    qmin.pollLast();
                }
                qmin.addLast(R);

                if (arr[qmax.peekFirst()] - arr[qmin.peekFirst()] > num) {
                    break;
                }
                R++;
            }

            res += R - L;

            if(qmax.peekFirst() == L) {
                qmax.pollFirst();
            }
            if(qmin.peekFirst() == L) {
                qmin.pollFirst();
            }

            L++;
        }
        return res;
    }

    // for test
    public static int[] getRandomArray(int len) {
        if (len < 0) {
            return null;
        }
        int[] arr = new int[len];
        for (int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * 10);
        }
        return arr;
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr != null) {
            for (int i = 0; i < arr.length; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[] arr = getRandomArray(30);
        int num = 5;
        printArray(arr);
        System.out.println(getNum(arr, num));

    }
}
