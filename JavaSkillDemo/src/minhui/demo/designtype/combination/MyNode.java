
package minhui.demo.designtype.combination;

public abstract class MyNode {
	public String name;
	public String ID;

	public MyNode(String name, String iD) {
		super();
		this.name = name;
		ID = iD;
	}

	public abstract void printInfo();
}
