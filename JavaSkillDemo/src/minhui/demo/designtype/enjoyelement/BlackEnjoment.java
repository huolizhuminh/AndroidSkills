
package minhui.demo.designtype.enjoyelement;

public class BlackEnjoment implements IEnjoyMent {
	int tag;

	public BlackEnjoment() {
		tag = (int) (Math.random() * 1000);
	}

	@Override
	public void print() {
		System.out.println("BlackEnjoment" + tag);
	}

}
