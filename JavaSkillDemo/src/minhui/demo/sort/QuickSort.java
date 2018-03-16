
package minhui.demo.sort;

public class QuickSort extends BaseSortDemo {

	@Override
	void sortData(int[] needSort) {
			quickSortData(needSort,0,needSort.length-1);
	}

	private void quickSortData(int[] needSort, int start, int endData) {
		//System.out.println("start:"+start+"end"+endData);
		//两个数据直接比较
		if(endData-start==1){
			if(needSort[start]>needSort[start+1]){
				swapData(needSort,start,start+1);
			}
		}
		int temp=needSort[start];
		int middle=start;
		for(int i=start+1;i<=endData;i++){
			if(needSort[i]<=temp){
				//如果是第二个数据直接交互
				if(i==middle+1){
					swapData(needSort,middle,middle+1);
					middle++;
					continue;
				}
				//中间位数据，遍历位数据，中间位后一位数据互相调换
				needSort[middle]=needSort[i];
				needSort[i]=needSort[middle+1];
				needSort[middle+1]=temp;
				middle++;
			}
		}
		if((middle-1)-start>=1){
			quickSortData(needSort,start,middle-1);
		}
		if(endData-(middle+1)>=1){
			quickSortData(needSort,middle+1,endData);
		}
	}

}
