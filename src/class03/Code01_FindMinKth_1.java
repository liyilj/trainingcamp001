package class03;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 在无序数组中求第K小的数
 * <p>
 * 1）改写快排的方法
 * <p>
 * 2）bfprt算法
 */
public class Code01_FindMinKth_1 {

    public static class FindMinComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    // 利用大根堆，时间复杂度O(N*logK)
    public static int minKth1(int[] arr, int k) {
        if (arr == null || arr.length < k || k < 1) {
            return -1;
        }
        PriorityQueue<Integer> heap = new PriorityQueue<>(new FindMinComparator());
        for (int i = 0; i < arr.length; i++) {
            heap.add(arr[i]);
        }
        for (int i = 0; i < k - 1; i++) {
            heap.poll();
        }
        return heap.poll();
    }

    // 改写快排，时间复杂度O(N)
    public static int minKth2(int[] arr, int k) {
        if (arr == null || arr.length < k || k < 1) {
            return -1;
        }
        return process2(copyArray(arr), 0, arr.length - 1, k - 1);
    }

    public static int[] copyArray(int[] arr) {
        if (arr == null || arr.length < 1) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    public static int process2(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[index];
        }
        int pivot = arr[L + (int) Math.random() * (R - L + 1)];

        int[] range = partition(arr, L, R, pivot);

        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return process2(arr, L, range[0] - 1, index);
        } else {
            return process2(arr, range[1] + 1, R, index);
        }
    }

    public static int[] partition(int[] arr, int L, int R, int pivot) {
        int less = L - 1;
        int more = R + 1;
        int cur = L;
        while (cur < more) {
            if (arr[cur] < pivot) {
                swap(arr, cur++, ++less);
            } else if (arr[cur] > pivot) {
                swap(arr, cur, --more);
            } else {
                cur++;
            }
        }
        return new int[]{less + 1, more - 1};
    }

    public static void swap(int[] arr, int a, int b) {
        int tmp = arr[a];
        arr[a] = arr[b];
        arr[b] = tmp;
    }

    // bfprt，时间复杂度O(N)
    public static int minKth3(int[] arr, int k) {
        if (arr == null || arr.length < k || k < 1) {
            return -1;
        }


        return bfprt(copyArray(arr), 0, arr.length - 1, k - 1);
    }

    public static int bfprt(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[index];
        }
        int pivot = chooseM(arr, L, R);
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else if (index < range[0]) {
            return bfprt(arr, L, range[0] - 1, index);
        } else {
            return bfprt(arr, range[1] + 1, R, index);
        }
    }

    public static int chooseM(int[] arr, int L, int R) {
        int len = R - L + 1;
        int groups = len / 5 + (len % 5 > 0 ? 1 : 0);
        int[] marr = new int[groups];
        for (int i = 0; i < groups; i++) {
            int start = L + i * 5;
            int end = Math.min(R, start + 4);
            insertSort(arr, start, end);
            marr[i] = arr[(end + start) / 2];
        }

        return bfprt(marr, 0, groups - 1, groups / 2);
    }

    public static void insertSort(int[] arr, int L, int R) {
        for (int i = L + 1; i <= R; i++) {
            for (int j = i - 1; j >= L && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }


    // for test
    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) (Math.random() * maxSize) + 1];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * (maxValue + 1));
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTime = 1000000;
        int maxSize = 100;
        int maxValue = 100;
        System.out.println("test begin");
        for (int i = 0; i < testTime; i++) {
            int[] arr = generateRandomArray(maxSize, maxValue);
            int k = (int) (Math.random() * arr.length) + 1;
            int ans1 = minKth1(arr, k);
            int ans2 = minKth2(arr, k);
            int ans3 = minKth3(arr, k);
            if (ans1 != ans2 || ans2 != ans3) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
