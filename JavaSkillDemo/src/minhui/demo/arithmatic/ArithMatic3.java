
package minhui.demo.arithmatic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;

import minhui.demo.DemoInterface;
import minhui.demo.Utils;

/**
 * 给一列无序数组，求出中位数并给出算法的时间复杂度。
 */
public class ArithMatic3 implements DemoInterface {
	long times;

	@Override
	public void startRun() {
		int[] arrays = Utils.getArrays(1000000);
		long start = System.currentTimeMillis();
		int i = getMiddle(arrays, 0, arrays.length - 1);
		long cost = (System.currentTimeMillis() - start);
		System.out.println("time:" + cost);
		// System.out.println("arrays:"+Utils.getArrayString(arrays));

		System.out.println("result middle " + i + "result:" + arrays[i]);
		System.out.println("time:" + times);
	}

	private int getMiddle(int[] arrays, int i, int j) {
		// System.out.println("arrays:"+Utils.getArrayString(arrays));
		if (i == arrays.length / 2 || i == (arrays.length + 1) / 2) {
			return i;
		}

		if (j == arrays.length / 2 || j == (arrays.length + 1) / 2) {
			return j;
		}
		int start = getRawMiddle(arrays, i, j);
		int swapTemp = arrays[start];
		arrays[start] = arrays[i];
		arrays[i] = swapTemp;
		// System.out.println("swapTemp "+ swapTemp);

		int middle = i;
		int temp = arrays[i];

		for (int k = i + 1; k <= j; k++) {
			times++;
			if (arrays[k] < temp) {
				if (k == middle + 1) {
					arrays[middle] = arrays[k];
					arrays[middle + 1] = temp;
					middle++;
					continue;
				}
				arrays[middle] = arrays[k];
				arrays[k] = arrays[middle + 1];
				arrays[middle + 1] = temp;
				middle++;
				continue;
			}
		}
		System.out.println("middle " + middle + "value:" + arrays[middle]);
		if (middle == (arrays.length - 1) / 2 || middle == arrays.length / 2) {
			return middle;
		}
		if (middle > (arrays.length + 1) / 2) {
			return getMiddle(arrays, i, middle - 1);
		} else {
			return getMiddle(arrays, middle + 1, j);
		}
	}

	private int getRawMiddle(int[] arrays, int i, int j) {
		if (j - i < 100) {
			return j;
		}
		TreeMap<Integer, Integer> treeMap = new TreeMap();
		int currentIndex = 0;
		for (int k = 0; k < 60; k++) {
			currentIndex = (int) (Math.random() * (j - i) + i);
			treeMap.put(arrays[currentIndex], currentIndex);
		}
		int index = (int) (arrays.length / 2 - i) * 60 / (j - i);
		if (i + j > arrays.length - 1) {
			index = index + 1;
		} else if (i + j < arrays.length - 1) {
			index = index - 1;
		}
		if (index < 0) {
			index = 0;
		} else if (index > 59) {
			index = 59;
		}
		Iterator<Entry<Integer, Integer>> iterator = treeMap.entrySet().iterator();
		int match = 0;
		System.out.println("index:" + index);
		while (iterator.hasNext()) {
			Entry<Integer, Integer> next = iterator.next();
			match++;
			if (match == index) {

				int value = next.getValue();

				System.out.println("value:" + value);
				return value;
			}
		}
		System.out.println("value:" + currentIndex);
		return currentIndex;

	}
	// private int getRawMiddle(int[] arrays, int i, int j) {
	// if (j - i < 100) {
	// return j;
	// }
	// long sum = 0;
	// int min = Integer.MAX_VALUE;
	// int max = 0;
	// int[] index = new int[10];
	// for (int k = 0; k < 10; k++) {
	// times++;
	// int currentIndex = (int) (Math.random() * (j - i) + i);
	// sum = sum + arrays[currentIndex];
	// int currentValue = arrays[currentIndex];
	// if (min > currentValue) {
	// min = currentValue;
	// } else if (max < currentIndex) {
	// max = currentValue;
	// }
	// index[k] = currentIndex;
	// }
	//
	// int average = (int) sum / 10;
	//
	//
	// int minIndex = 0;
	// int minDistance = Integer.MAX_VALUE;
	// for (int m = 0; m < 10; m++) {
	// int currentDistance = Math.abs(arrays[index[m]] - average);
	// if (currentDistance < minDistance) {
	// minDistance = currentDistance;
	// minIndex = m;
	// }
	// }
	//
	// return index[minIndex];
	// }
}
