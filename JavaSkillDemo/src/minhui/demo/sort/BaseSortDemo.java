
package minhui.demo.sort;

import minhui.demo.DemoInterface;
import minhui.demo.Utils;

public abstract class BaseSortDemo implements DemoInterface {

	@Override
	public void startRun() {
		int[] needSort = Utils.getArrays(100);
		// System.out.println("needSort"+getArrayString(needSort));Ï
		long startTime = System.currentTimeMillis();

		System.out.println("starttime:" + startTime);
		sortData(needSort);
		System.out.println(this.getClass().getName() + "endtime:" + (System.currentTimeMillis() - startTime));
		System.out.println(this.getClass().getName() + "  sortresult:  " + Utils.getArrayString(needSort));
	}

	abstract void sortData(int[] needSort);

	public void swapData(int[] data, int i, int j) {
		int temp = data[i];
		data[i] = data[j];
		data[j] = temp;
	}
}
