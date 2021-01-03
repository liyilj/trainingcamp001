package class03;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 在无序数组中求第K小的数
 *
 * 1）改写快排的方法
 *
 * 2）bfprt算法
 */
public class Code01_FindMinKth_1 {

    public static class MaxHeapComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }

    }

    // 利用大根堆，时间复杂度O(N*logK)
    public static int minKth1(int[] arr, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Code01_FindMinKth.MaxHeapComparator());
        for (int i = 0; i < k; i++) {
            maxHeap.add(arr[i]);
        }
        for (int i = k; i < arr.length; i++) {
            if (arr[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(arr[i]);
            }
        }
        return maxHeap.peek();
    }

    // 改写快排，时间复杂度O(N)
    public static int minKth2(int[] array, int k) {
        int[] arr = copyArray(array);
        return process2(arr, 0, arr.length - 1, k - 1);
    }

    public static int[] copyArray(int[] array) {
        if (array == null || array.length < 1) {
            return null;
        }
        int[] res = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            res[i] = array[i];
        }
        return res;
    }

    public static int process2(int[] arr, int L, int R, int index) {
        if (L == R) {
            return arr[index];
        }
        int pivot = arr[L + (int) (Math.random() * (R - L + 1))];
        int[] range = partition(arr, L, R, pivot);
        if (index >= range[0] && index <= range[1]) {
            return arr[index];
        } else {
            if (index < range[0]) {
                return process2(arr, L, range[0] - 1, index);
            } else {
                return process2(arr, range[1] + 1, R, index);
            }
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
//            int ans3 = minKth3(arr, k);
            if (ans1 != ans2 /*|| ans2 != ans3*/) {
                System.out.println("Oops!");
            }
        }
        System.out.println("test finish");
    }
}
