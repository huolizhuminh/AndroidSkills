
package minhui.demo.designtype.factroymethod;

public class FactoryB extends BaseFactory {

	@Override
	public BaseProduct getProduct() {
		ProductB productB = new ProductB();
		productB.address="shanghai";
		productB.factory=this;		
		return productB;
	}

}
