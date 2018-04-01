
package minhui.demo.designtype.proxy;

public class TicketSeller implements ITicketSeller {

	@Override
	public void buyTicket(int num) {
		System.out.println("sell " + num + "from ticketseller");
	}

}
