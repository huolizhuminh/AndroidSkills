
package minhui.demo.mysodusolve;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import minhui.demo.DemoInterface;

public class MySoduSolve implements DemoInterface {
	SoduNode[][] soduNodes;
  int   index=0;
	@Override
	public void startRun() {
		String testStr = "";
		System.out.println("请输入81个数字代表数独：/n");
		do {

			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			testStr = scan.nextLine();
			if (checkStr(testStr)) {
				findSoduResult(testStr);
			} else {
				System.out.println("请输入81个数字代表数独：/n");
			}

		} while (true);
	}

	private void findSoduResult(String testStr) {
		char[] charArray = testStr.toCharArray();
		initData(charArray);
		 System.out.println("initdata is :");
		   for(int i=0;i<9;i++){
			   System.out.println(SoduNode.getNodesValue(soduNodes[i][0].listNode));
		   }
	   if(getNextNodeAndCheck()){
		   System.out.println("resut is :");
		   for(int i=0;i<9;i++){
			   System.out.println(SoduNode.getNodesValue(soduNodes[i][0].listNode));
		   }
		//   System.out.println("index:"+index);
	   }else{
		   System.out.println("this is not a valid array");
	   }
		
		
	}
	private boolean getNextNodeAndCheck() {
	//	System.out.println("*******getNextNodeAndCheck********");
		index++;

		SoduNode lastNullNode = getNextNullNode();
		if(lastNullNode==null){
		//	System.out.println("is the last node");
			return true;
		}
		Integer[] suitValue = lastNullNode.getSuitValue();
		if(suitValue==null||suitValue.length==0){
		//	System.out.println("no have suitable value");
			return false;
		}
		for(int i=0;i<suitValue.length;i++){
			lastNullNode.value=suitValue[i];
			if(getNextNodeAndCheck()){
				return true;
			}
			lastNullNode.value=0;
		}
		return false;
	}

	private SoduNode getNextNullNode(){
		SoduNode lastNode;
		for(int i=0;i<9;i++){
			for(int k=0;k<9;k++){
				if(soduNodes[i][k].value==0){
					return soduNodes[i][k];
				}
			}
		}
		return null;
	}
    //建立各层数据的关系
	private void initData(char[] charArray) {
		soduNodes = new SoduNode[9][9];
		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++) {
				SoduNode soduNode = new SoduNode();
				soduNode.value=charArray[i*9+k]-'0';
				soduNode.xPosition=k;
				soduNode.yPosition=i;
				soduNodes[i][k]=soduNode;
			}
		}
		for(int i=0 ;i<9;i++){
			SoduNode [] listNode=new SoduNode[9];
			for(int k=0;k<9;k++){
				listNode[k]=soduNodes[i][k];
				soduNodes[i][k].listNode=listNode;
			}
		//	System.out.println("listNode:"+SoduNode.getNodesValue(listNode));
		}
		for(int i=0 ;i<9;i++){
			SoduNode [] rowNode=new SoduNode[9];
			for(int k=0;k<9;k++){
				rowNode[k]=soduNodes[k][i];
				soduNodes[k][i].rowNode=rowNode;
			}
			//System.out.println("rowNode:"+SoduNode.getNodesValue(rowNode));
		}
		for(int i=0;i<=2;i++){
			for(int k=0;k<=2;k++){
				SoduNode [] groupNode=new SoduNode[9];
				int index=0;
				int middlex=3*i+1;
				int middley=3*k+1;
				for(int j=-1;j<=1;j++){
					for(int l=-1;l<=1;l++){
						groupNode[index]=soduNodes[middlex+j][middley+l];
						soduNodes[middlex+j][middley+l].groupNode=groupNode;
						index++;
					}
				}
			//	System.out.println("groupNode:"+SoduNode.getNodesValue(groupNode));
			}
		}
	
	}

	private boolean checkStr(String testStr) {
		if (testStr == null) {
			return false;
		}
		String rawStr = testStr.trim();
		String patternType = "^[0-9]{81}$";
		Pattern pattern = Pattern.compile(patternType, Pattern.CASE_INSENSITIVE);
		return pattern.matcher(rawStr).matches();
	}

}
