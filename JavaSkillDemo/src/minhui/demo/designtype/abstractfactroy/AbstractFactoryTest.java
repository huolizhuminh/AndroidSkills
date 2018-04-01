
package minhui.demo.designtype.abstractfactroy;

import minhui.demo.DemoInterface;

public class AbstractFactoryTest implements DemoInterface {

	@Override
	public void startRun() {
      AdiFactory adiFactory = new AdiFactory();
      BaseCloth adiCloth = adiFactory.getCloth();
      System.out.println("adiCloth"+adiCloth.getClothName()+adiCloth.getClothPrice());
      BaseShoe adiShoe = adiFactory.getShoe();
      System.out.println("adiShoe"+adiShoe.getShoeName()+adiShoe.getShoePrice());
      NIkeFactory nIkeFactory = new NIkeFactory();
      BaseCloth nikeCloth= nIkeFactory.getCloth();
      System.out.println("nikeCloth"+nikeCloth.getClothName()+nikeCloth.getClothPrice());
      BaseShoe nikeShoe = nIkeFactory.getShoe();
      System.out.println("nikeShoe"+nikeShoe.getShoeName()+nikeShoe.getShoePrice());
	}

}
