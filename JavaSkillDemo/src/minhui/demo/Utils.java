
package minhui.demo;

public class Utils {
	public static int[] getArrays(int i) {
		int[] arrays = new int[i];
		for (int k = 0; k < i; k++) {
			arrays[k] = (int) (Math.random() * 10000000);
		}
		return arrays;
	}

	public static void 
	
	sortData(Comparable[] arr) {

		sortData(arr, arr.length-1);
	}

	public static void sortData(Comparable[] arr,int length) {

		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			adjustHeap(arr, i, arr.length);
		}
		for (int j =length; j > 0; j--) {
			Comparable temp = arr[j];
			arr[j] = arr[0];
			arr[0] = temp;
			adjustHeap(arr, 0, j);
		}
		
	}

	private static void adjustHeap(Comparable[] arr, int i, int length) {
		Comparable temp = arr[i];
		for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
			if (k + 1 < length && arr[k].compareTo(arr[k + 1])<0) {
				k = k + 1;
			}
			if (arr[k].compareTo(temp)>0) {
				arr[i] = arr[k];
				i = k;

			} else {
				break;
			}
		}
		arr[i] = temp;

	}

	public static String getArrayString(int[] arrays) {

		return getArrayString(arrays, 0, arrays.length - 1);
	}
	public static String getArrayString(int[] arrays, int from, int to) {

		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int length = arrays.length;
		boolean result = true;
		int errorIndex = 0;
		int error = 0;
		for (int i = from; i < to; i++) {
		builder.append(String.valueOf(arrays[i])).append(",");
		if (i != 0 && i % 15 == 0) {
				builder.append("\n");
			}
			if (arrays[i] > arrays[i + 1]) {
				result = false;
				error = arrays[i];
				errorIndex = i;
			}
		}
		builder.append(String.valueOf(arrays[to])).append("]");

		return builder.toString();
	}
}
