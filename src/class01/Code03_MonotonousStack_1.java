package class01;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 单调栈
 */
public class Code03_MonotonousStack_1 {

	public static int[][] getNearLessNoRepeat(int[] arr) {
		if (arr == null && arr.length < 1) {
			return null;
		}
		int[][] res = new int[arr.length][2];
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) {
				int ind = stack.pop();
				res[ind][0] = !stack.isEmpty() ? stack.peek(): -1;
				res[ind][1] = i;
			}
			stack.push(i);
		}

		while (!stack.isEmpty()) {
			int ind = stack.pop();
			res[ind][0] = !stack.isEmpty() ? stack.peek() : -1;
			res[ind][1] = -1;
		}
		return res;
	}

	// arr [3, 2, 1, 4, 5]
	//      0  1  2  3  4
	
	//  [
	//    0 :  [-1,  1  ]
	//    1 :  [-1,  2  ]
	
	//  ] 
	//
	public static int[][] getNearLess(int[] arr) {
		if (arr == null && arr.length < 1) {
			return null;
		}
		int[][] res = new int[arr.length][2];
		Stack<List<Integer>> stack = new Stack<>();
		for (int i = 0; i < arr.length; i++) {
			while (!stack.isEmpty() && arr[stack.peek().get(stack.peek().size()-1)] > arr[i]) {
				List<Integer> ind = stack.pop();
				for (Integer cur : ind) {
					res[cur][0] = !stack.isEmpty() ? stack.peek().get(stack.peek().size()-1): -1;
					res[cur][1] = i;
				}
			}
			if (!stack.isEmpty() && arr[stack.peek().get(stack.peek().size()-1)] == arr[i]) {
				stack.peek().add(i);
			} else {
				List<Integer> list = new ArrayList<>();
				list.add(i);
				stack.push(list);
			}
		}

		while (!stack.isEmpty()) {
			List<Integer> ind = stack.pop();
			for (Integer cur :ind) {
				res[cur][0] = !stack.isEmpty() ? stack.peek().get(stack.peek().size() -1) : -1;
				res[cur][1] = -1;
			}
		}
		return res;
	}

	// for test
	public static int[] getRandomArrayNoRepeat(int size) {
		int[] arr = new int[(int) (Math.random() * size) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i;
		}
		for (int i = 0; i < arr.length; i++) {
			int swapIndex = (int) (Math.random() * arr.length);
			int tmp = arr[swapIndex];
			arr[swapIndex] = arr[i];
			arr[i] = tmp;
		}
		return arr;
	}

	// for test
	public static int[] getRandomArray(int size, int max) {
		int[] arr = new int[(int) (Math.random() * size) + 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = (int) (Math.random() * max) - (int) (Math.random() * max);
		}
		return arr;
	}

	// for test
	public static int[][] rightWay(int[] arr) {
		int[][] res = new int[arr.length][2];
		for (int i = 0; i < arr.length; i++) {
			int leftLessIndex = -1;
			int rightLessIndex = -1;
			int cur = i - 1;
			while (cur >= 0) {
				if (arr[cur] < arr[i]) {
					leftLessIndex = cur;
					break;
				}
				cur--;
			}
			cur = i + 1;
			while (cur < arr.length) {
				if (arr[cur] < arr[i]) {
					rightLessIndex = cur;
					break;
				}
				cur++;
			}
			res[i][0] = leftLessIndex;
			res[i][1] = rightLessIndex;
		}
		return res;
	}

	// for test
	public static boolean isEqual(int[][] res1, int[][] res2) {
		if (res1.length != res2.length) {
			return false;
		}
		for (int i = 0; i < res1.length; i++) {
			if (res1[i][0] != res2[i][0] || res1[i][1] != res2[i][1]) {
				return false;
			}
		}

		return true;
	}

	// for test
	public static void printArray(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		int size = 10;
		int max = 20;
		int testTimes = 2000000;
		for (int i = 0; i < testTimes; i++) {
			int[] arr1 = getRandomArrayNoRepeat(size);
			int[] arr2 = getRandomArray(size, max);
			if (!isEqual(getNearLessNoRepeat(arr1), rightWay(arr1))) {
				System.out.println("Oops!");
				printArray(arr1);
				break;
			}
			if (!isEqual(getNearLess(arr2), rightWay(arr2))) {
				System.out.println("Oops!");
				printArray(arr2);
				break;
			}
		}
	}
}
