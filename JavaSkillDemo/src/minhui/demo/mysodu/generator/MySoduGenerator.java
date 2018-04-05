
package minhui.demo.mysodu.generator;

import java.util.Scanner;
import java.util.TreeSet;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import minhui.demo.DemoInterface;

public class MySoduGenerator implements DemoInterface {
	SoduNode[][] soduNodes;
	// int index = 0;
	int maxSolutionNum=0;
	@Override
	public void startRun() {
		char[] charArray = new char[81];
		for (int i = 0; i < 81; i++) {
			charArray[i] = '0';
		}
		TreeSet<Integer> indexs = new TreeSet<>();
		TreeSet<Object> currentTreeSet = new TreeSet<>();
		int time=0;
		a: do {
			int index = (int) (Math.random() * 81);
			if (indexs.contains(index)) {
				continue;
			}
			indexs.add(index);
			initData(charArray);
			int list = index / 9;
			int row = index % 9;
			System.out.println("list:"+list+"row"+row+"index"+index);
			SoduNode currentNode = soduNodes[list][row];
			Integer[] suitValue = currentNode.getSuitValue();
			boolean hasOnlySolution = false;
			currentTreeSet.clear();

			b: for (int i = 0; i < suitValue.length; i++) {
				currentNode.value = suitValue[i];
				charArray[index] = (char) ('0' + suitValue[i]);
		
				int solutionNum = getSolutionNum(charArray);
				System.out.println("solutionNum"+solutionNum+"");
				if (solutionNum == 1) {
					break a;

				} else if (solutionNum > 1) {
					currentTreeSet.add(suitValue[i]);
				}
			}
			Integer[] solutions = new Integer[currentTreeSet.size()];
			currentTreeSet.toArray(solutions);
			int randomSolution = (int) (Math.random() * solutions.length);
			currentNode.value = solutions[randomSolution];
			charArray[index] = (char) ('0' + solutions[randomSolution]);
			time++;
			System.out.println("********************"+time+"*********************");
			for (int i = 0; i < 9; i++) {
				if(i%3==0){
					System.out.println("********************************************");
				}
				System.out.println(SoduNode.getNodesValue(soduNodes[i][0].listNode));
			}
			System.out.println("*****************************************");
			System.out.println("\n");
		} while (true);
		System.out.println("********************result*********************");
		for (int i = 0; i < 9; i++) {
			System.out.println(SoduNode.getNodesValue(soduNodes[i][0].listNode));
		}
	}

	private int getSolutionNum(char[] charArray) {
		maxSolutionNum++;
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
