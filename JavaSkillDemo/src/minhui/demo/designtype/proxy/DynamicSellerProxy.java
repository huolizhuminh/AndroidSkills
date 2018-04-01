
package minhui.demo.designtype.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class DynamicSellerProxy implements InvocationHandler{
	TicketSeller imp;
	public  DynamicSellerProxy(){
		imp=new TicketSeller(); 
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("method:"+method);
		if(method.getName().endsWith("buyTicket")){
			System.out.println("buy ticket form dynamic proxy num is"+args[0]);
			method.invoke(imp, args);
		}
		return null;
	}

}
