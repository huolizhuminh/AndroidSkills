
package minhui.demo.designtype.visitor;

public class BossAccoutView implements IAccountView {

	@Override
	public void view(BillA bill) {
		bill.operateA();
	}

	@Override
	public void view(BillB bill) {
		bill.operateB();
	}

}
