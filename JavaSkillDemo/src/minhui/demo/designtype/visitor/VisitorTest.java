
package minhui.demo.designtype.visitor;

import minhui.demo.DemoInterface;

public class VisitorTest implements DemoInterface{
	
	@Override
	public void startRun() {
		BillA billA = new BillA(1, "zhu");
		BillB billB=new BillB("minhui",77);
		BossAccoutView bossAccoutView=new BossAccoutView();
		ManagerAccountView managerAccountView=new ManagerAccountView();
		bossAccoutView.view(billA);
		bossAccoutView.view(billB);
		managerAccountView.view(billA);
		managerAccountView.view(billB);
		
	}
	
}
