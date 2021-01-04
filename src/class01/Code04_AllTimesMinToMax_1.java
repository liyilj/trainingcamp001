package class01;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Code04_AllTimesMinToMax_1 {

    public static int max1(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int minNum = Integer.MAX_VALUE;
                int sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += arr[k];
                    minNum = Math.min(minNum, arr[k]);
                }
                max = Math.max(max, minNum * sum);
            }
        }
        return max;
    }

    public static int max2(int[] arr) {
        if (arr == null || arr.length == 0) {
            return Integer.MIN_VALUE;
        }

        int[] presum = new int[arr.length];
        presum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            presum[i] = presum[i-1] + arr[i];
        }

        Stack<List<Integer>> stack = new Stack<>();
        int[][] records = new int[arr.length][2];
        for (int i = 0; i < arr.length; i++) {
            while (!stack.empty() && arr[stack.peek().get(stack.peek().size()-1)] > arr[i]) {
                    List<Integer> list = stack.pop();
                    for (Integer index : list) {
                        records[index][0] = !stack.empty() ? stack.peek().get(stack.peek().size()-1) : -1;
                        records[index][1] = i;
                    }
            }
            if (!stack.isEmpty() && arr[stack.peek().get(0)] == arr[i]) {
                stack.peek().add(i);
            } else {
                List<Integer> list = new ArrayList();
                list.add(i);
                stack.push(list);
            }
        }
        while (!stack.isEmpty()) {
            List<Integer> list = stack.pop();
            for (Integer index :list) {
                records[index][0] = stack.isEmpty() ? -1: stack.peek().get(stack.peek().size()-1);
                records[index][1] = -1;
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            int[] range = records[i];
            int l = range[0] == -1 ? 0 : range[0];
            int r = range[1] == -1 ? arr.length-1 : range[1]-1;
            max =  Math.max(max, (presum[r] - (l == 0 ? 0 : presum[l])) * arr[i]);
        }
        return max;
    }

    public static int max3(int[] arr) {
        if (arr == null || arr.length < 1) {
            return 0;
        }
        int[] presum = new int[arr.length];
        presum[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            presum[i] = presum[i-1] + arr[i];
        }
        int max = Integer.MIN_VALUE;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                int cur = stack.pop();
                int sum = stack.isEmpty() ? presum[i-1]: presum[i-1] - presum[stack.peek()];
                max = Math.max(max, arr[cur] * sum);
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int cur = stack.pop();
            int sum = stack.isEmpty() ? presum[arr.length-1] : presum[arr.length-1] - presum[stack.peek()];
            max = Math.max(max, arr[cur]* sum);
        }

        return max;
    }

    public static int[] gerenareRondomArray() {
        int[] arr = new int[(int) (Math.random() * 20) + 10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) (Math.random() * 101);
        }
        return arr;
    }

    public static void main(String[] args) {
        int testTimes = 2000000;
        System.out.println("test begin");
        for (int i = 0; i < testTimes; i++) {
            int[] arr = gerenareRondomArray();
            if (max1(arr) != max3(arr)) {
                System.out.println("FUCK!");
                break;
            }
        }
        System.out.println("test finish");
    }
}
