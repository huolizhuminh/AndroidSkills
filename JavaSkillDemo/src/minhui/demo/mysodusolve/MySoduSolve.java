
package minhui.demo.mysodusolve;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import javax.print.attribute.standard.PrinterLocation;

import com.sun.org.apache.xpath.internal.axes.NodeSequence;

import minhui.demo.DemoInterface;

public class MySoduSolve implements DemoInterface {
	SoduNode[][] soduNodes;

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
		for (int i = 0; i < 9; i++) {
			System.out.println(SoduNode.getNodesValue(soduNodes[i][0].listNode));
		}
		if (getNextNodeAndCheck()) {
			System.out.println("resut is :");
			for (int i = 0; i < 9; i++) {
				System.out.println(SoduNode.getNodesValue(soduNodes[i][0].listNode));
			}
			System.out.println("*************");
			int flag=0;
			for(int m=0;m<9;m++){
				for(int n=0;n<9;n++){
					flag=flag+soduNodes[m][n].value*((m*9+n)^2);
				}
			}
			System.out.println("flag:"+flag);
		} else {
			System.out.println("this is not a valid array");
		}

	}

	private boolean getNextNodeAndCheck() {
		// System.out.println("*******getNextNodeAndCheck********");

		SoduNode lastNullNode = getNextNullNode();
		if (lastNullNode == null) {
			System.out.println("is the last node");
			return true;
		}
		Set suitValueSet = lastNullNode.getSuitValueSet();
		if (suitValueSet == null || suitValueSet.size() == 0) {
			// System.out.println("no have suitable value");
			return false;
		}
		// for (int i = 0; i < suitValue.length; i++) {
		// lastNullNode.value = suitValue[i];
		// if (getNextNodeAndCheck()) {
		// return true;
		// }
		// lastNullNode.value = 0;
		// }
		do {
			Integer[] suitValue = new Integer[suitValueSet.size()];
			Iterator<Integer> iterator = suitValueSet.iterator();
			int suitValueIndex = 0;
			while (iterator.hasNext()) {
				suitValue[suitValueIndex] = iterator.next();
				suitValueIndex++;
			}
			lastNullNode.value = suitValue[(int) (Math.random() * suitValue.length)];
			if (getNextNodeAndCheck()) {
				return true;
			}

			suitValueSet.remove(lastNullNode.value);
		} while (suitValueSet.size() > 0);
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

	// private SoduNode getNextNullNode() {
	//
	// HashSet<SoduNode> set = new HashSet<>();
	// for (int i = 0; i < 9; i++) {
	// for (int k = 0; k < 9; k++) {
	// if (soduNodes[i][k].value == 0) {
	// set.add(soduNodes[i][k]);
	// }
	// }
	// }
	// System.out.println("getNextNullNode " + set.size());
	// if (set.isEmpty()) {
	// return null;
	// } else {
	// SoduNode[] nodes = new SoduNode[set.size()];
	// set.toArray(nodes);
	// System.out.println("nodes"+nodes.length );
	// int notnullIndex=(int) (Math.random() * set.size());
	//
	// return nodes[notnullIndex];
	// }
	// }

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
