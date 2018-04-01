
package minhui.demo.designtype.appearance;

import minhui.demo.DemoInterface;
import minhui.demo.designtype.appearance.facesystem.Face;

public class AppearanceTest implements DemoInterface {

	@Override
	public void startRun() {
		Face face = new Face();
		face.start();
	}

}
