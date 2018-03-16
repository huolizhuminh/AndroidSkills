
package minhui.demo;

import minhui.demo.sort.HeapSortDemo;
import minhui.demo.sort.InSertSort;
import minhui.demo.sort.MaoPaoSort;
import minhui.demo.sort.MergeSort;
import minhui.demo.sort.QuickSort;

public class MyDemoTest {

	public static void main(String[] args) {
		System.out.println("start");
		DemoInterface test1 = new MaoPaoSort();
		test1.startRun();

		DemoInterface test2 = new InSertSort();
		test2.startRun();
		//
		DemoInterface test3 = new HeapSortDemo();
		test3.startRun();
		DemoInterface test4 = new QuickSort();
		test4.startRun();
		DemoInterface test5 = new MergeSort();
		test5.startRun();
	}

}
