
package minhui.demo.designtype.command;

public class ActionA implements IAction {
	IReceiver receiver;

	public void setReceiver(IReceiver receiver) {
		this.receiver = receiver;
	}

	@Override
	public void excute() {
		System.out.println("ActionA");
		receiver.sitDown();
		receiver.standup();
	}

}
