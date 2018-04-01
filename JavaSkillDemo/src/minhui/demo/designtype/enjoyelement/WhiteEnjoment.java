
package minhui.demo.designtype.enjoyelement;

public class WhiteEnjoment implements IEnjoyMent {
	int tag;

	public WhiteEnjoment() {
		tag = (int) (Math.random() * 1000);
	}

	@Override
	public void print() {
		System.out.println("BlackEnjoment" + tag);
	}

}
