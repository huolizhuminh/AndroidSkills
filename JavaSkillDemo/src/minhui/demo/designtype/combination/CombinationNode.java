
package minhui.demo.designtype.combination;

import java.util.ArrayList;
import java.util.List;

public class CombinationNode extends MyNode {
	public CombinationNode(String name, String iD) {
		super(name, iD);
	}

	List<MyNode> nodes = new ArrayList();

	public void addNode(MyNode node) {
		nodes.add(node);
	}

	@Override
	public void printInfo() {
		System.out.println("CombinationNode " + name + "   " + ID);
		for (MyNode node : nodes) {
			node.printInfo();
		}
//		for(int i=0;i<nodes.size();i++){
//			nodes.get(i).printInfo();
//		}
	}

}
