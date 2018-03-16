
package minhui.demo.sort;

import java.util.Arrays;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

public class MergeSort extends BaseSortDemo {

	@Override
	void sortData(int[] needSort) {
		 int[] temp=new int[needSort.length];
		sort(needSort,temp,0,needSort.length-1);
	}

	private void sort(int[] needSort,int[] temp,int i, int j) {
		if(i==j){
			return;
		}
		if(j-i==1){
			if(needSort[i]>needSort[j]){
				swapData(needSort, i, j);
			}
			return;
		}
		if(j-i==2){
			if(needSort[i]>needSort[i+1]){
				swapData(needSort, i, i+1);
			}
			if(needSort[i+1]>needSort[i+2]){
				swapData(needSort, i+1, i+2);
			}
			if(needSort[i]>needSort[i+1]){
				swapData(needSort, i, i+1);
			}
			return;
		}
		int middle=(int)(i+j)/2;
		sort(needSort,temp,i,middle);
		sort(needSort,temp,middle+1,j);
		merge(needSort,temp,i,middle,j);
		
	}

	private void merge(int[] needSort, int[] temp, int i, int middle, int j) {
	//	System.out.println("mergeStart:"+getArrayString(needSort,i,j));
		int currentFirst=i;
		int currentSecond=middle+1;
		for(int index=0;index<=j-i;index++){
			if(currentFirst==middle+1){
				//直接从temp拷贝到needSort
				arraysCopy(temp,0,index-1,needSort,i);
//				System.out.println("temp"+getArrayString(temp,0,j-i));
//				System.out.println("merger endx"+getArrayString(needSort,i,j));
				return;
			}else if(currentSecond==j+1){
				arraysCopy(needSort,currentFirst,middle,temp,index);
				break;
			}
			if(needSort[currentFirst]<needSort[currentSecond]){
				temp[index]=needSort[currentFirst];
				currentFirst++;
			}else{
				temp[index]=needSort[currentSecond];
				currentSecond++;
			}
		}
	//	System.out.println("temp"+getArrayString(temp,0,j-i));
		arraysCopy(temp, 0, j-i, needSort, i);
//		System.out.println("merger end"+getArrayString(needSort,i,j));
		
	}

	private void arraysCopy(int[] origin, int originFrom, int originTo, int[] des, int desFrom) {
		int desIndex=desFrom;
		for (int copyIndex=originFrom;copyIndex<=originTo;copyIndex++){
			des[desIndex]=origin[copyIndex];
			desIndex++;
		}
	}

}
