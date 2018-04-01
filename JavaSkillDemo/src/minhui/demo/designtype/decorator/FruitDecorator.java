
package minhui.demo.designtype.decorator;

public class FruitDecorator implements Fruit {
    private Fruit needDecoratFood;
    public void setFruit(Fruit fruit){
    	needDecoratFood=fruit;
    }
    
    
    
	@Override
	public String getColor() {
		System.out.println("da la");
		return needDecoratFood.getColor();
	}

	@Override
	public String getShape() {
		return needDecoratFood.getShape();
	}

}
