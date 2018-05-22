
package minhui.demo.sodusave;

import java.util.ArrayList;

import com.sun.org.apache.regexp.internal.recompile;

public class MySoduGenerator {
	private static final String TAG = "MySoduGenerator";
	SoduNode[][] soduNodes;
	private char[] charArray;

	public int getNoNeedToSolve() {
		if(soduNodes==null){
			return 0;
		}
		int data=0;
		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				if(!soduNodes[i][j].needTobeSolve){
					data++;
				}
			}
		}
		return data;
	}

	public String getDataString() {
		if (charArray == null) {
			return null;
		}
		StringBuilder stringBuilder = new StringBuilder();
		for (char node : charArray) {
			stringBuilder.append(node);
		}
		return stringBuilder.toString();
	}

	private int noNeedSolve;

	// int index = 0;
	// int maxSolutionNum=0;
	public SoduNode[][] generateGame(Level level) throws Exception {
		int minSum = level.getMinSum();
		int maxSum = level.getMaxSum();
		charArray = new char[81];
		for (int i = 0; i < 81; i++) {
			charArray[i] = '0';
		}
		initData(charArray);
		noNeedSolve = 0;
		a: do {
			int index = getMostSuitableValueIndex();

			int list = index / 9;
			int row = index % 9;
			System.out.println("select fill node:" + "list:" + list + "row" + row + "index" + index);
			SoduNode currentNode = soduNodes[list][row];
			Integer[] suitValue = currentNode.getSuitValue();
			Integer[] hasSolutionValue = new Integer[9];
			int hasSolutionValueNum = 0;
			if (suitValue.length < 2) {
				continue;
			}
			currentNode.needTobeSolve = false;
			int noTrySuit = suitValue.length;
			boolean hasFound = false;
			while (noTrySuit > 0) {
				int random = (int) (Math.random() * suitValue.length);
				if (suitValue[random] == null) {
					continue;
				}
				currentNode.value = suitValue[random];
				System.out.println("suitValue:" + currentNode.value);
				charArray[index] = (char) ('0' + suitValue[random]);

				int solutionNum = getSolutionNum(charArray);
				System.out.println("solutionNum:" + solutionNum);
				if (solutionNum == 1) {
					noNeedSolve++;
					
					break a;

				} else if (solutionNum > 1) {
					if (minSum - noNeedSolve > 4) {
						/*
						 * charArray[index] = (char) ('0' + currentNode.value);
						 */
						hasFound = true;
						break;
					} else {
						hasSolutionValue[hasSolutionValueNum] = suitValue[random];
						hasSolutionValueNum++;
						suitValue[random] = null;
						noTrySuit--;
					}

				} else {
					suitValue[random] = null;
					noTrySuit--;
				}

			}

			if (!hasFound) {
				System.out.println("has not found");
				int hasSolutionRandom = (int) (hasSolutionValueNum * Math.random());
				currentNode.value = hasSolutionValue[hasSolutionRandom];
				charArray[index] = (char) ('0' + hasSolutionValue[hasSolutionRandom]);
			}
			noNeedSolve++;
			printsoDuProcess(noNeedSolve);

		} while (true);
		System.out.println("********************result*********************");
		for (int i = 0; i < 9; i++) {
			if (i % 3 == 0) {
				System.out.println("********************************************");
			}
			System.out.println(SoduNode.getNodesValue(soduNodes[i][0].listNode));
		}
		//失败
		if(noNeedSolve<17){
			
			soduNodes=null;
			return soduNodes;
		}
		int needEnterNum = (int) (minSum - noNeedSolve + 1 + (maxSum - minSum) * Math.random());
		System.out.println("need enter num " + needEnterNum);
		if (needEnterNum > 0) {
			enterNum(needEnterNum);
			//noNeedSolve = noNeedSolve + needEnterNum;
		}
		return soduNodes;
	}

	private int getMostSuitableValueIndex() {
		int mostSuitable = 1;
		ArrayList<SoduNode> mostSuitValueNodes = new ArrayList<>();
		for (int i = 0; i < 81; i++) {
			int y = i / 9;
			int x = i % 9;
			if (!soduNodes[x][y].needTobeSolve) {
				continue;
			}
			Integer[] suitValue = soduNodes[x][y].getSuitValue();
			if (suitValue.length > mostSuitable) {
				mostSuitable = suitValue.length;
				mostSuitValueNodes.clear();
				mostSuitValueNodes.add(soduNodes[x][y]);
			} else if (suitValue.length == mostSuitable) {
				mostSuitValueNodes.add(soduNodes[x][y]);
			}

		}
		int size = mostSuitValueNodes.size();
		SoduNode select = mostSuitValueNodes.get((int) (size * Math.random()));
		return select.yPosition * 9 + select.xPosition;
	}

	private void enterNum(int needEnterNum) throws Exception {
		if (needEnterNum <= 0) {
			return;
		}
		int enterNum = 0;
		MySolutionFinder mySolutionFinder = new MySolutionFinder();
		SoduNode[][] result = mySolutionFinder.findResult(soduNodes);
		while (true) {
			if (enterNum == needEnterNum) {
				break;
			}
			int x = (int) (9 * Math.random());
			int y = (int) (9 * Math.random());
			if (soduNodes[x][y].value != 0) {
				continue;
			}
			if (result[x][y].value==0) {
				throw new Exception("invalid result");
			}
			if (soduNodes[x][y].getSuitValue() == null) {
				continue;
			}
			if (soduNodes[x][y].getSuitValue().length < 3) {
				if (!hasSuitTableValue()) {
					break;
				} else {
					continue;
				}

			}
			soduNodes[x][y].value = result[x][y].value;
			soduNodes[x][y].needTobeSolve = false;
			charArray[x*9+y]=(char) ('0'+result[x][y].value);
			noNeedSolve++;
			enterNum++;
		}

	}

	private boolean hasSuitTableValue() {
		boolean hasSuitableValue=false;
		for(int i=0;i<9;i++){
			for(int k=0;k<9;k++){
				if(!soduNodes[i][k].needTobeSolve){
					continue;
				}
				Integer[] suitValue = soduNodes[i][k].getSuitValue();
				if(suitValue!=null&&suitValue.length>2){
					return true;
				}
			}
		}
		return false;
	}

	void printsoDuProcess(int time) {
		System.out.println("********************" + time + "*********************");
		for (int i = 0; i < 9; i++) {
			if (i % 3 == 0) {
				System.out.println("********************************************");
			}
			System.out.println(SoduNode.getNodesValue(soduNodes[i][0].listNode));
		}
		System.out.println("*****************************************");
		System.out.println("");
		System.out.println("");
	}

	private int getSolutionNum(char[] charArray) {
		// maxSolutionNum++;
		return new MySolutionFinder().findSolution(charArray);
	}

	// 建立各层数据的关系
	private void initData(char[] charArray) {
		soduNodes = new SoduNode[9][9];
		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++) {
				SoduNode soduNode = new SoduNode();
				soduNode.value = charArray[i * 9 + k] - '0';
				soduNode.xPosition = k;
				soduNode.yPosition = i;
				soduNodes[i][k] = soduNode;
			}
		}
		for (int i = 0; i < 9; i++) {
			SoduNode[] listNode = new SoduNode[9];
			for (int k = 0; k < 9; k++) {
				listNode[k] = soduNodes[i][k];
				soduNodes[i][k].listNode = listNode;
			}
			// Log.d("listNode:"+SoduNode.getNodesValue(listNode));
		}
		for (int i = 0; i < 9; i++) {
			SoduNode[] rowNode = new SoduNode[9];
			for (int k = 0; k < 9; k++) {
				rowNode[k] = soduNodes[k][i];
				soduNodes[k][i].rowNode = rowNode;
			}
			// Log.d("rowNode:"+SoduNode.getNodesValue(rowNode));
		}
		for (int i = 0; i <= 2; i++) {
			for (int k = 0; k <= 2; k++) {
				SoduNode[] groupNode = new SoduNode[9];
				int index = 0;
				int middlex = 3 * i + 1;
				int middley = 3 * k + 1;
				for (int j = -1; j <= 1; j++) {
					for (int l = -1; l <= 1; l++) {
						groupNode[index] = soduNodes[middlex + j][middley + l];
						soduNodes[middlex + j][middley + l].groupNode = groupNode;
						index++;
					}
				}
				// Log.d("groupNode:"+SoduNode.getNodesValue(groupNode));
			}
		}

	}

	public SoduNode[][] generateEmptyGame() {
		charArray = new char[81];
		for (int i = 0; i < 81; i++) {
			charArray[i] = '0';
		}
		initData(charArray);
		return soduNodes;
	}
}
