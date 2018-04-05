
package minhui.demo.mysodu.generator;

import java.util.Iterator;
import java.util.TreeSet;

public class MySolutionFinder implements ISoduSolutionFinder {
	SoduNode[][] soduNodes;
	int findIndex = 0;

	@Override
	public int findSolution(char[] charArray) {
		initData(charArray);
		System.out.println("");
		if (!findSoduResult()) {
            
			return 0;
		}
		System.out.println("findSoduResult");
		a: for (int i = 8; i >= 0; i--) {
			b: for (int k = 8; k >= 0; k--) {
				TreeSet<Integer> allValueSet = soduNodes[i][k].getAllValueSet();
				if (!soduNodes[i][k].needTobeSolve || allValueSet.size() == 1) {
					if (soduNodes[i][k].needTobeSolve) {
						soduNodes[i][k].value = 0;
					}
					continue b;
				}
				TreeSet<Integer> leftAllValue = soduNodes[i][k].getAllValueSet();
				Iterator<Integer> iterator = leftAllValue.iterator();
				c: while (iterator.hasNext()) {
					Integer leftValue = (Integer) iterator.next();
					if (leftValue == soduNodes[i][k].value) {
						continue c;
					}
					soduNodes[i][k].value = leftValue;
                    
					if (findSoduResult()) {
						return 2;
					}
				}
				soduNodes[i][k].value = 0;
			}
		}

		return 1;
	}

	private boolean findSoduResult() {

		if (getNextNodeAndCheck()) {
			return true;
		} else {
			return false;
		}

	}

	private boolean getNextNodeAndCheck() {
		findIndex++;
//		if (findIndex % 100 == 0) {
//			System.out.println("findIndex" + findIndex);
//		}
		SoduNode lastNullNode = getNextNullNode();
		if (lastNullNode == null) {
			return true;
		}
		Integer[] suitValue = lastNullNode.getSuitValue();
		if (suitValue == null || suitValue.length == 0) {
			// System.out.println("no have suitable value");
			return false;
		}
		for (int i = 0; i < suitValue.length; i++) {
			lastNullNode.value = suitValue[i];
			if (getNextNodeAndCheck()) {
				return true;
			}
			lastNullNode.getAllValueSet().remove(suitValue[i]);
		}
		lastNullNode.value = 0;
		return false;
	}

	private SoduNode getNextNullNode() {
		SoduNode lastNode;
		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++) {
				if (soduNodes[i][k].value == 0) {
					return soduNodes[i][k];
				}

			}
		}
		return null;
	}

	// 建立各层数据的关系
	private void initData(char[] charArray) {
		soduNodes = new SoduNode[9][9];
		for (int i = 0; i < 9; i++) {
			for (int k = 0; k < 9; k++) {
				SoduNode soduNode = new SoduNode();
				soduNode.value = charArray[i * 9 + k] - '0';
				soduNode.needTobeSolve = (soduNode.value == 0);
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
			// System.out.println("listNode:"+SoduNode.getNodesValue(listNode));
		}
		for (int i = 0; i < 9; i++) {
			SoduNode[] rowNode = new SoduNode[9];
			for (int k = 0; k < 9; k++) {
				rowNode[k] = soduNodes[k][i];
				soduNodes[k][i].rowNode = rowNode;
			}
			// System.out.println("rowNode:"+SoduNode.getNodesValue(rowNode));
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
				// System.out.println("groupNode:"+SoduNode.getNodesValue(groupNode));
			}
		}

	}
}
