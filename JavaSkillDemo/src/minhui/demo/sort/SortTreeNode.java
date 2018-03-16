
package minhui.demo.sort;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SortTreeNode {
	SortTreeNode formerNode;
	SortTreeNode leftNode;
	SortTreeNode rightNode;
	boolean isRight = false;
	int value;

	public void addNode(int addValue) {

		if (addValue <= value) {
			// 左树为空则新建左树，否则在左树上寻找合适的挂载点
			if (leftNode == null) {

				leftNode = new SortTreeNode();
				leftNode.formerNode = this;
				leftNode.value = addValue;

			} else {

				leftNode.addNode(addValue);
				;

			}

		} else {
			// 右树为空则新建左树，否则在右树上寻找合适的挂载点
			if (rightNode == null) {
				rightNode = new SortTreeNode();
				rightNode.formerNode = this;
				rightNode.value = addValue;
				rightNode.isRight = true;
			} else {
				rightNode.addNode(addValue);
			}
		}

	}

	public int[] getValueArray() {

		ArrayList list = new ArrayList<Integer>();
		addData(list);
		int[] result = new int[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = (Integer) list.get(i);
		}
		return result;

	}

	private void addData(ArrayList list) {
		if (leftNode != null) {
			leftNode.addData(list);
		}
		list.add(value);
		if (rightNode != null) {
			rightNode.addData(list);
		}
	}

}
