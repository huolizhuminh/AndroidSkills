
package minhui.demo.designtype.enjoyelement;

import com.sun.prism.shader.Mask_TextureRGB_AlphaTest_Loader;

import minhui.demo.DemoInterface;

public class EnjoymentTest implements DemoInterface {

	@Override
	public void startRun() {
		EnjoymentFactory enjoymentFactory = new EnjoymentFactory();
		for (int i = 0; i < 20; i++) {
			
			WhiteEnjoment whiteEnjoyMent = enjoymentFactory.getWhiteEnjoyMent();
			whiteEnjoyMent.print();
			if(i%2==0){
				enjoymentFactory.releaseWhiteEnjoyMent(whiteEnjoyMent);
			}
		}
	}

}
