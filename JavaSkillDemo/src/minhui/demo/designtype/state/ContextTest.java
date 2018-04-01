
package minhui.demo.designtype.state;

import minhui.demo.DemoInterface;

public class ContextTest implements DemoInterface {

	@Override
	public void startRun() {
		Context context = new Context();
		context.setState(new InitState(context));
		for (int i = 0; i < 9; i++) {
			int k = (int) (Math.random() * 5);
			System.out.println("k"+k);
			for (int m = 1; m <= k; m++) {
				if (k % 2 == 0) {
					context.forwade();
				} else {
					context.back();
				}
			}
		}
	}

}
