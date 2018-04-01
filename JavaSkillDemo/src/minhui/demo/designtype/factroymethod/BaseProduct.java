
package minhui.demo.designtype.factroymethod;


public abstract class BaseProduct {
	public BaseFactory factory;
	public String address;
	public abstract String  getName();

	public abstract String getPrice();

	public void show() {
		System.out.println(" I am a product");
	}
}
