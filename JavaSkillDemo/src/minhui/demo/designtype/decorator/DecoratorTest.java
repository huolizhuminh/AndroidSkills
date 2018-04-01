
package minhui.demo.designtype.decorator;

import minhui.demo.DemoInterface;

public class DecoratorTest implements DemoInterface{

	@Override
	public void startRun() {
       Apple apple = new Apple();
       FruitDecorator fruitDecorator = new FruitDecorator();
       fruitDecorator.setFruit(apple);
       System.out.println("fruitDecorator "+fruitDecorator.getColor()+fruitDecorator.getShape());
	}

}
