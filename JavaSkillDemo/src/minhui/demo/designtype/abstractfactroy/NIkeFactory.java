
package minhui.demo.designtype.abstractfactroy;

public class NIkeFactory extends BaseFactory {

	@Override
	public BaseCloth getCloth() {
		
		return new NIkeCloth();
	}

	@Override
	public BaseShoe getShoe() {
		return new NikeShoe();
	}

}
