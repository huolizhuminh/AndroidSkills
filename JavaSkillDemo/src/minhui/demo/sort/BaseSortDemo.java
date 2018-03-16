
package minhui.demo.sort;

import minhui.demo.DemoInterface;

public abstract class BaseSortDemo implements DemoInterface {

	@Override
	public void startRun() {
		int[] needSort = getArrays(10000);
		// System.out.println("needSort"+getArrayString(needSort));
		long startTime = System.currentTimeMillis();

		System.out.println("starttime:" + startTime);
		sortData(needSort);
		System.out.println(this.getClass().getName() + "endtime:" + (System.currentTimeMillis() - startTime));
		System.out.println(this.getClass().getName() + "  sortresult:  " + getArrayString(needSort));
	}

	abstract void sortData(int[] needSort);

	private int[] getArrays(int i) {
		int[] arrays = new int[i];
		for (int k = 0; k < i; k++) {
			arrays[k] = (int) (Math.random() * 1000000);
		}
		return arrays;
	}

	String getArrayString(int[] arrays) {

		return getArrayString(arrays, 0, arrays.length - 1);
	}

	String getArrayString(int[] arrays, int from, int to) {

		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int length = arrays.length;
		boolean result = true;
		int errorIndex = 0;
		int error = 0;
		for (int i = from; i < to; i++) {
//			builder.append(String.valueOf(arrays[i])).append(",");
//			if (i != 0 && i % 15 == 0) {
//				builder.append("\n");
//			}
			if (arrays[i] > arrays[i + 1]) {
				result = false;
				error = arrays[i];
				errorIndex = i;
			}
		}
		builder.append(String.valueOf(arrays[to])).append("]")
	.append("]:result:").append(String.valueOf(result))
				.append(String.valueOf(errorIndex)).append("eroro").append(String.valueOf(error))
				;

		return builder.toString();
	}

	public void swapData(int[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
}
