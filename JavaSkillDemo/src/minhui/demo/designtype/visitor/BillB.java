
package minhui.demo.designtype.visitor;

import javax.naming.OperationNotSupportedException;

public class BillB implements IBill {
    String state;
    int ID;
    
    
    
	public BillB(String state, int iD) {
		super();
		this.state = state;
		ID = iD;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public int getID() {
		return ID;
	}



	public void setID(int iD) {
		ID = iD;
	}


   public void operateB(){
	   System.out.println("state"+getState()+"ID"+getID());
   }
	@Override
	public void accept(IAccountView view) {
		view.view(this);
	}

}
