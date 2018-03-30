
package minhui.demo.mysodusolve;

import java.util.Iterator;
import java.util.TreeSet;

public class SoduNode {
	int value = 0;
	int xPosition;
	int yPosition;
	// private char[] suitValues = new char[9];

	SoduNode[] listNode;
	SoduNode[] rowNode;
	SoduNode[] groupNode;
    static final Integer[] allChar={1,2,3,4,5,6,7,8,9};
    Integer[] getSuitValue() {
    	TreeSet<Integer> notSuits = new TreeSet<>();
		TreeSet<Integer> allValue=	new TreeSet();
		for (int i = 0; i <= 8; i++) {
			if (listNode[i].value != 0) {
				notSuits.add(listNode[i].value);
			}
			if (rowNode[i].value != 0) {
				notSuits.add(rowNode[i].value);
			}
			if (groupNode[i].value != 0) {
				notSuits.add(groupNode[i].value);
			}
			
			allValue.add(allChar[i]);
		}
		int notSuitSize = notSuits.size();
		if (notSuitSize == 9) {
			return null;
		}
		allValue.removeAll(notSuits);
	//	System.out.println("allValue :" + getTreeValue(allValue));
		Integer[]suitValue=new Integer[allValue.size()];
		Iterator<Integer> iterator = allValue.iterator();
		int suitValueIndex=0;
		while(iterator.hasNext()){
			suitValue[suitValueIndex]=iterator.next();
			suitValueIndex++;
		}
	//	System.out.println("suitValue size:"+suitValue.length+" value:"+getSuitsValue(suitValue));
		return suitValue;
	}

	private String getTreeValue(TreeSet<Integer> tree) {
		StringBuilder builder=	new StringBuilder();
		Iterator<Integer> iterator = tree.iterator();
		while(iterator.hasNext()){
			builder.append(iterator.next())
			
			.append("  ");	
		}
		return builder.toString();
	}

	public static String getNodesValue(SoduNode[] listNodes) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < listNodes.length; i++) {
			builder.append(listNodes[i].value).append(" ");
		}
		return builder.toString();
	}
	public static String getSuitsValue(Integer[] suitValue) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < suitValue.length; i++) {
			builder.append(suitValue[i]).append(" ");
		}
		return builder.toString();
	}
}
