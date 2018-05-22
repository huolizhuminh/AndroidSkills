
package minhui.demo.sodusave;


import java.util.Iterator;
import java.util.TreeSet;

public class MySolutionFinder implements ISoduSolutionFinder {
    private static final String TAG = "MySolutionFinder";
    SoduNode[][] soduNodes;
    int findIndex = 0;
    public static final int NONE_RESULT = 0;
    public static final int SINGLE_RESULT = 1;
    public static final int MULTI_RESULT = 2;
    private boolean hasFoundResult;

    @Override
    public int findSolution(char[] charArray) {
        initData(charArray);
        //System.out.println("");
        System.out.println(  "begin to findSolution");
        if (!findSoduResult()) {
            System.out.println(  "not find result findIndex" + findIndex);
            return NONE_RESULT;
        }
        hasFoundResult = true;
        System.out.println(  " find result " + "   findIndex" + findIndex);
        for (int m = 80; m >= 0; m--) {
            int i = m / 9;
            int k = m % 9;
            TreeSet<Integer> allValueSet = soduNodes[i][k].getAllValueSet();
            if (!soduNodes[i][k].needTobeSolve || allValueSet.size() == 1) {
                if (soduNodes[i][k].needTobeSolve) {
                    soduNodes[i][k].value = 0;
                }
                continue;
            }
            System.out.println(  "find double num: i:  " + i + "   k:   " + k);
            TreeSet<Integer> leftAllValue = soduNodes[i][k].getAllValueSet();
            Iterator<Integer> iterator = leftAllValue.iterator();
            a:
            while (iterator.hasNext()) {
                Integer leftValue = (Integer) iterator.next();
                if (leftValue == soduNodes[i][k].value) {
                    continue a;
                }
                soduNodes[i][k].value = leftValue;

                if (findSoduResult()) {
                    System.out.println( " success  find double num: i:  " + i + "   k:   " + k + "   findIndex" + findIndex);
                    return MULTI_RESULT;
                }
            }
            soduNodes[i][k].value = 0;

        }
        System.out.println( " single " + findIndex);
        return SINGLE_RESULT;
    }

    private boolean findSoduResult() {

        if (getNextNodeAndCheck()) {
            return true;
        } else {
            return false;
        }

    }

    public SoduNode[][] findResult(SoduNode[][] data) {
        return findResult(data, false);
    }

    public SoduNode[][] findResult(SoduNode[][] data, boolean isSolve) {
        if (data == null) {
            return null;
        }
        soduNodes = new SoduNode[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                soduNodes[i][j] = new SoduNode();
                if (data[i][j].value != 0) {
                    if (isSolve || !data[i][j].needTobeSolve) {
                        soduNodes[i][j].value = data[i][j].value;
                        soduNodes[i][j].needTobeSolve = false;
                    }
                }

            }
        }
        initGroups();
        if (!findSoduResult()) {
            return null;
        }
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                soduNodes[i][j].needTobeSolve = data[i][j].needTobeSolve;


            }
        }
        return soduNodes;
    }

    private boolean getNextNodeAndCheck() {
        findIndex++;
        if (findIndex > 5000) {
            return false;
        }

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
        initGroups();

    }

    private void initGroups() {
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
