
package minhui.demo.designtype.command;

import minhui.demo.DemoInterface;

public class CommandTest implements DemoInterface {

	@Override
	public void startRun() {
		ActionA actionA = new ActionA();
		ActionB actionB = new ActionB();
		ReceiverA receiverA = new ReceiverA();
		ReceiverB receiverB = new ReceiverB();
		actionA.setReceiver(receiverA);
		actionA.excute();
		actionA.setReceiver(receiverB);
		actionA.excute();
		actionB.setReceiver(receiverA);
		actionB.excute();
        actionB.setReceiver(receiverB);
        actionB.excute();
	}

}
