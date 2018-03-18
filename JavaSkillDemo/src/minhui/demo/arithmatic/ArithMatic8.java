
package minhui.demo.arithmatic;

import java.util.ArrayList;

import minhui.demo.DemoInterface;
import minhui.demo.Utils;

/**
 * 如图(picture1)所示的数字三角形，从顶部出发，在每一结点可以选择向左走或得向右走，一直走到底层，要求找出一条路径，使路径上的值的和最大。给出算法的时间复杂度。
 */
public class ArithMatic8 implements DemoInterface {
	int[][] rectangle = { { 7 }, { 3, 8 }, { 8, 1, 0 }, { 2, 7, 4, 4 }, { 4, 5, 2, 6, 5 } };

	@Override
	public void startRun() {

		int deep = 4;
		int sumNum = (int) Math.pow(2, deep);
		System.out.println("sumNum" + sumNum);
		Rout[] routs = new Rout[sumNum];

		for (int i = 0; i <= sumNum - 1; i++) {
			Rout rout = new Rout();
			rout.value = 7;
			rout.path.add(7);
			int sumRemain = 0;
			for (int k = deep - 1; k >= 1; k--) {
				int remain = (int) ((i % (Math.pow(2, k + 1))) / (Math.pow(2, k)));

				rout.steps.add(remain);
				sumRemain = sumRemain + remain;
				System.out.println("i" + i + "k" + k + "sumRemain" + sumRemain);
				rout.value = rout.value + rectangle[deep - k][sumRemain];
				rout.path.add(rectangle[deep - k][sumRemain]);
			}
			int lastRemain = i % 2;
			rout.steps.add(lastRemain);
			sumRemain = sumRemain + lastRemain;
			rout.value = rout.value + rectangle[deep][sumRemain];
			rout.path.add(rectangle[deep][sumRemain]);
			routs[i] = rout;
		}
		Utils.sortData(routs);
		printData(routs[15]);
		for (int i = 0; i < sumNum; i++) {
			printData(routs[i]);
		}
	}

	private void printData(Rout rout) {
		StringBuilder builder = new StringBuilder();
		builder.append("steps are:");
		for (int i = 0; i < rout.path.size(); i++) {
			builder.append("   ").append(rout.path.get(i));
		}
		builder.append("   summer is ").append(rout.value);
		System.out.println(builder.toString());

	}

	class Rout implements Comparable {
		ArrayList<Integer> steps = new ArrayList();
		ArrayList<Integer> path = new ArrayList();
		int value;

		@Override
		public int compareTo(Object o) {

			return value - ((Rout) o).value;
		}

	}

}
