
package minhui.demo.designtype.adapter;

import minhui.demo.DemoInterface;

public class AdapterTest implements DemoInterface {

	@Override
	public void startRun() {
     Level classAdapter = new ClassAdapter();
     System.out.println("levelImplement "+classAdapter.getHigh()+classAdapter.getLow());
     ObjectAdapter objectAdapter = new ObjectAdapter();
     System.out.println("objectAdapter "+objectAdapter.getHigh()+objectAdapter.getLow());
     InterfaceAdapter interfaceAdapter = new InterfaceAdapter();
     System.out.println("interfaceAdapter "+interfaceAdapter.getHigh()+interfaceAdapter.getLow());
	}

}
