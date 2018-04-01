
package minhui.demo.designtype.visitor;

public class BillA implements IBill {
	int ID;
	String name;

	public BillA(int iD, String name) {
		super();
		ID = iD;
		this.name = name;
	}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    public void operateA(){
    	System.out.println("name"+name+"ID"+ID);
    }
	@Override
	public void accept(IAccountView view) {
		view.view(this);
	}

}
