
package minhui.demo.designtype.command;

public class ReceiverA implements IReceiver {

	@Override
	public void standup() {
		System.out.println("A standup");
	}

	@Override
	public void sitDown() {
		System.out.println("A sitDown");
	}

	@Override
	public void turnLeft() {
		System.out.println("A turnLeft");
	}

	@Override
	public void trunRight() {
		System.out.println("A trunRight");
	}

}
