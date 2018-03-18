
package minhui.demo.sort;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class SortTreeNode {
	public	SortTreeNode formerNode;
	public	SortTreeNode leftNode;
	public	SortTreeNode rightNode;
	public	boolean isRight = false;
	public	int value;

	public void addNode(int addValue) {

		if (addValue <= value) {
			// 左树为空则新建左树，否则在左树上寻找合适的挂载点
			if (leftNode == null) {

				leftNode = new SortTreeNode();
				leftNode.formerNode = this;
				leftNode.value = addValue;
			} else {

				leftNode.addNode(addValue);
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
		addDataToList(list);
		int[] result = new int[list.size()];
		for (int i = 0; i < result.length; i++) {
			result[i] = (Integer) list.get(i);
		}
		return result;

	}

	private void addDataToList(ArrayList list) {
		if (leftNode != null) {
			leftNode.addDataToList(list);
		}
		list.add(value);
		if (rightNode != null) {
			rightNode.addDataToList(list);
		}
	}
	

	public static SortTreeNode newInstance(int[] datas) {
		SortTreeNode tree=	new SortTreeNode();
		tree.value=datas[0];
		for(int i=1;i<=datas.length-1;i++){
			tree.addNode(datas[i]);
		}
		return tree;
	}

}
