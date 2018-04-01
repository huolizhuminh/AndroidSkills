
package minhui.demo.designtype.iterator;


import minhui.demo.DemoInterface;
import minhui.demo.Utils;

public class IteartorTest implements DemoInterface {

	@Override
	public void startRun() {
		int[] arrays = Utils.getArrays(100);
		SortTreeNode sortTreeNode = new SortTreeNode();
		sortTreeNode.value=arrays[0];
		for(int i=1;i<100;i++){
			sortTreeNode.addNode(arrays[i]);
		}
		int[] valueArray = sortTreeNode.getValueArray();
		System.out.println("valueArray"+Utils.getArrayString(valueArray));
		MyIterator iterator = sortTreeNode.iterator();
		while(iterator.hasNext()){
			SortTreeNode next = (SortTreeNode) iterator.next();
			System.out.println("item:"+next.value);
		}
	}

}
