
package minhui.demo.designtype.iterator;

import java.lang.reflect.Array;
import java.util.ArrayList;


import com.sun.org.apache.regexp.internal.recompile;

public class SortTreeNode {
	public SortTreeNode formerNode;
	public SortTreeNode leftNode;
	public SortTreeNode rightNode;
	public boolean isRight = false;
	public int value;
	int size = 1;

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
		size++;

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
		SortTreeNode tree = new SortTreeNode();
		tree.value = datas[0];
		for (int i = 1; i <= datas.length - 1; i++) {
			tree.addNode(datas[i]);
		}
		return tree;
	}

	public MyIterator<SortTreeNode> iterator() {
		return new SortTreeIterator();
	}

	class SortTreeIterator implements MyIterator<SortTreeNode> {
		SortTreeNode currentPostion = SortTreeNode.this;
		int maxIteratorSize = size;
		int iteratorsize = 0;
		boolean hasIterator = false;

		@Override
		public boolean hasNext() {
			return iteratorsize < maxIteratorSize;
		}

		@Override
		public SortTreeNode next() {
			iteratorsize++;
			System.out.println("iterator " + iteratorsize);
			if (currentPostion.formerNode == null && !hasIterator) {
				hasIterator = true;
				currentPostion = currentPostion.getMostLeft();
				return currentPostion;

			}
			currentPostion = currentPostion.getNext();
			return currentPostion;
		}

	}

	SortTreeNode getMostLeft() {
		if (leftNode != null) {
			return leftNode.getMostLeft();
		}
		return this;
	}

	public SortTreeNode getNext() {
		if (rightNode != null) {
			return rightNode.getMostLeft();
		}
		if (!isRight) {
			return formerNode;
		}
		return getLeftParent();
	}

	private SortTreeNode getLeftParent() {
		if (formerNode == null) {
			return null;
		}
		if (!formerNode.isRight) {
			return formerNode.formerNode;
		}
		return formerNode.getLeftParent();
	}

}
