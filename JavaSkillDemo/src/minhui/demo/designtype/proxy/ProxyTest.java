
package minhui.demo.designtype.proxy;

import java.lang.reflect.Proxy;

import minhui.demo.DemoInterface;

public class ProxyTest implements DemoInterface {

	@Override
	public void startRun() {
     StaticTicketSellerProxy staticTicketSellerProxy = new StaticTicketSellerProxy();
     staticTicketSellerProxy.buyTicket(3);
     ITicketSeller newProxyInstance = (ITicketSeller) Proxy.newProxyInstance(this.getClass().getClassLoader(),new Class[]{ITicketSeller.class}, new DynamicSellerProxy());
     newProxyInstance.buyTicket(3);
	}

}
