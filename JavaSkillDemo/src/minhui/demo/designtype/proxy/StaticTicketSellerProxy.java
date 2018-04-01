
package minhui.demo.designtype.proxy;
/**
 * 静态一般用于实际被代理者比较重的情况，维护比较复杂。而代理者又要再被代理者提供的API上实现一下自己的方法
 * 静态代理与装饰者模式-对象装饰的差别是，静态代理被代理的对象是内部得到的，而对象装饰中被装饰的对象是从外部传入的
 */
public class StaticTicketSellerProxy implements ITicketSeller {
	ITicketSeller ticketSeller;

	public StaticTicketSellerProxy() {
		ticketSeller = new TicketSeller();
	}

	@Override
	public void buyTicket(int num) {
		System.out.println("get ticket form proxy");
		ticketSeller.buyTicket(num);
	}

}
