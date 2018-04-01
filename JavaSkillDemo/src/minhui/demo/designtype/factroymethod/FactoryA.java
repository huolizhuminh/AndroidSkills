
package minhui.demo.designtype.factroymethod;

public class FactoryA extends BaseFactory {

	@Override
	public BaseProduct getProduct() {
		ProductA producteA = new ProductA();
		producteA.address="shenzhen";
		producteA.factory=this;
		return producteA;
	}

}
