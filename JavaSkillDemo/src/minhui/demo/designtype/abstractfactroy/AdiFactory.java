
package minhui.demo.designtype.abstractfactroy;

public class AdiFactory extends BaseFactory {

	@Override
	public BaseCloth getCloth() {
		return new AdiCloth();
	}

	@Override
	public BaseShoe getShoe() {
		return new AdiShoe();
	}

}
