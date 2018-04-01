
package minhui.demo.designtype.bridge;

public class BlackPainter extends BasePainter {

	@Override
	 public void draw() {
      super.getShape().drawShape();
      System.out.println("black");
	}

}
