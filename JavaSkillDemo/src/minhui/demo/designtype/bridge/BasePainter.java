
package minhui.demo.designtype.bridge;

public abstract class BasePainter {
	Shape shape;

	public Shape getShape() {
		return shape;
	}

	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public abstract void draw();

}
