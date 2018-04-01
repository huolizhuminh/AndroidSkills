
package minhui.demo.designtype.factroymethod;

import minhui.demo.DemoInterface;

public class FactoryMethodTest implements DemoInterface {

	@Override
	public void startRun() {
    FactoryA factoryA = new FactoryA();
    BaseProduct productA = factoryA.getProduct();
    System.out.println("productA:"+productA.getName()+productA.getPrice());
    FactoryB factoryB = new FactoryB();
    BaseProduct productB = factoryB.getProduct();
    System.out.println("productB:"+productB.getName()+productB.getPrice());
	}

}
