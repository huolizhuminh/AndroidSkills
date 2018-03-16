
package minhui.demo.sort;

public class InSertSort extends BaseSortDemo {

	@Override
	void sortData(int[] needSort) {
		SortTreeNode root = null;
		for (int i = 0; i < needSort.length; i++) {
			int value = needSort[i];
			if (root == null) {
               root=new SortTreeNode();
               root.value=value;
			}
			root.addNode(value);
		}
		int[] newValue=root.getValueArray();
		for (int i = 0; i < needSort.length; i++){
			needSort[i]=newValue[i];
		}
	}

	

}
