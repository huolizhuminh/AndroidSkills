
package minhui.demo.arithmatic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Your goal is to reach the last index in the minimum number of jumps.

For example:
Given array A = [2,3,1,1,4]

The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)
 */
import minhui.demo.DemoInterface;

public class ArithMatic7 implements DemoInterface {
	Step[] steps;
	/**
	 * 思路：倒着来 先解倒数第二个位置到终点的最优解，再解倒数第三个位置到终点的最优解，....最后解出最后一个位置到终点的最优解
	 * 
	 * 
	 * 
	 */
	int[] question;

	@Override
	public void startRun() {
		question = getQuestion();
		System.out.println(getArrayString(question));
		steps = new Step[question.length];
		Step step_0 = new Step();
		step_0.position = 0;
		step_0.step = 0;
		steps[0] = step_0;
		Step step_1 = new Step();
		step_1.position = 1;
		step_1.step = 1;
		step_1.steps.add(1);
		steps[1] = step_1;
		// 从距离终点第二位置开始遍历，一直遍历到起始位置
		for (int i = 2; i < question.length; i++) {
			steps[i] = getMostRationStep(i);
		}
		for(int i=0;i<question.length;i++){
			Step step=steps[i];
			StringBuilder stepsBuilder=	new StringBuilder();
			stepsBuilder.append("[");
			List<Integer> steps=step.steps;
			for(int k=steps.size()-1;k>=0;k--){
				stepsBuilder.append(steps.get(k))
				.append(",");
			}
			stepsBuilder.append("]");
			System.out.println("postion:"+(question.length-i)+",need:"+step.step+",steps:"+stepsBuilder.toString());
			
		}

	}

	private Step getMostRationStep(int i) {
		Step step = new Step();
		int value = question[question.length - 1 - i];
		step.position = i;
		for (int k = 1; k <= value; k++) {
			// 一步即可以跳到
			if (k >= i) {
				step.position = i;
				step.step = 1;
				step.steps.clear();
				step.steps.add(k);
				return step;
			}
			//从后面的值选择最优解
			if (step.step == 0 ||steps[i-k].step+1<step.step) {
				step.step=steps[i-k].step+1;
				step.steps.clear();;
				step.steps.addAll(steps[i-k].steps);
				step.steps.add(k);
			}
		}
		return step;
	}

	private int[] getQuestion() {
		int size = (int) (Math.random() * (10 - 6) + 6);
		int[] question = new int[size];
		for (int i = 0; i < size; i++) {
			question[i] = (int) (Math.random() * 4 + 1);
		}
		return question;
	}

	static class Step {
		int position; // 离尾结点的位置
		int step;// 最快多少步能够达到尾节点
		List<Integer> steps = new ArrayList();
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
