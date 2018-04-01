
package minhui.demo.designtype.combination;

public class LeafNode extends MyNode {

	public LeafNode(String name, String iD) {
		super(name, iD);
	}

	@Override
	public void printInfo() {
		System.out.println("Name" + name + "Id" + ID);
	}

}
