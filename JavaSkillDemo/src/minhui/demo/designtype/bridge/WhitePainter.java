
package minhui.demo.designtype.bridge;

public class WhitePainter extends BasePainter {

	@Override
	 public void draw() {
      super.getShape().drawShape();
      System.out.println("white");
	}

}
