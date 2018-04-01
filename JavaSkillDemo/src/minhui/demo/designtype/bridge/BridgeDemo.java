
package minhui.demo.designtype.bridge;

import minhui.demo.DemoInterface;

public class BridgeDemo implements DemoInterface {

	@Override
	public void startRun() {
		BlackPainter blackPainter = new BlackPainter();
		WhitePainter whitePainter = new WhitePainter();
		CircleShape circleShape = new CircleShape();
		LineShape lineShape = new LineShape();
		blackPainter.setShape(circleShape);
		blackPainter.draw();
		blackPainter.setShape(lineShape);
		blackPainter.draw();
		whitePainter.setShape(circleShape);
		whitePainter.draw();
		whitePainter.setShape(lineShape);
		whitePainter.draw();

	}

}
