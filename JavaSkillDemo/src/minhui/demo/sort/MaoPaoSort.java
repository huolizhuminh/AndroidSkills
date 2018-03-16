
package minhui.demo.sort;

public class MaoPaoSort extends BaseSortDemo {

	@Override
	void sortData(int[] needSort) {
		int length = needSort.length;
		for (int i = length ; i > 0; i--) {
           for(int j=0;j<i-1;j++){
        	   if(needSort[j]>needSort[j+1]){
        		   swapData(needSort, j, j+1);
        	   }
           }
		}

	}

}
