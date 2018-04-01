
package minhui.demo.designtype.command;

public class ReceiverB implements IReceiver {

	@Override
	public void standup() {
		System.out.println("B standup");
	}

	@Override
	public void sitDown() {
		System.out.println("B sitDown");
	}

	@Override
	public void turnLeft() {
		System.out.println("B turnLeft");
	}

	@Override
	public void trunRight() {
		System.out.println("B trunRight");
	}

}
