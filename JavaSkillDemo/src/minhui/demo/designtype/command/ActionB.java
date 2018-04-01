
package minhui.demo.designtype.command;

public class ActionB implements IAction {
	IReceiver receiver;

	public void setReceiver(IReceiver receiver) {
		this.receiver = receiver;
	}

	@Override
	public void excute() {
		System.out.println("ActionB");
		receiver.turnLeft();;
		receiver.trunRight();;
	}

}
