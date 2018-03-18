
package minhui.demo.arithmatic;

import minhui.demo.DemoInterface;
import minhui.demo.Utils;
import minhui.demo.sort.SortTreeNode;

/**
 * 输入一棵二元查找树，将该二元查找树转换成一个排序的双向链表。 要求不能创建任何新的结点，只调整指针的指向。
 */
public class ArithMatic1 implements DemoInterface {

	@Override
	public void startRun() {
		int datas[] = Utils.getArrays(10);
		SortTreeNode tree = SortTreeNode.newInstance(datas);
		SortTreeNode startNode = tree;
		// 找到最左点
		for (;;) {
			if (startNode.leftNode == null) {
				break;
			}
			startNode = startNode.leftNode;
		}
		SortTreeNode endNode = turnToLinkedList(tree, null);
		StringBuilder builder = new StringBuilder();
		while (startNode != null) {
			builder.append(startNode.value).append(",");
			startNode = startNode.rightNode;
		}
		System.out.println("result:" + builder.toString());
		StringBuilder newbuilder = new StringBuilder();
		while (endNode != null) {
			newbuilder.append(endNode.value).append(",");
			endNode = endNode.leftNode;
		}
		System.out.println("result:" + newbuilder.toString());
	}

	private SortTreeNode turnToLinkedList(SortTreeNode currentNode, SortTreeNode former) {
		SortTreeNode leftTree = currentNode.leftNode;
		SortTreeNode rightTree = currentNode.rightNode;
		SortTreeNode lastNode = former;
		if (leftTree != null) {
			lastNode = turnToLinkedList(leftTree, former);
		}
		currentNode.leftNode = lastNode;
		if (lastNode != null) {
			lastNode.rightNode = currentNode;
		}
		lastNode = currentNode;
		System.out.println("current:" + currentNode.value);
		if (rightTree != null) {
			lastNode = turnToLinkedList(rightTree, lastNode);
		}
		return lastNode;

	}

}
