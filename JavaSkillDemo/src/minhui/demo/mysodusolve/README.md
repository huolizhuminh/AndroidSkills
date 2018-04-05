## 初级玩法

首先看一下数独
![image](https://github.com/huolizhuminh/AndroidSkills/blob/master/JavaSkillDemo/images/sodu.jpg)

### 数独规则
数独的规则比较简单：
* 每一行包括了1到9的数字，并且不能重复。
* 每一列包括了1到9的数字，并且不能重复。
* 每一组包括了1到9的数字，并且不能重复。

### 玩数独求解的流程
* 步骤一：从上到下，从左到右选择一个空单元。
* 步骤二: 根据这个单元所在的行、列、组，求出这个单元可能的解。
* 步骤三：随机抽取一个未论证的解作为单元的解进行填入，进行步骤一。
* 步骤四：如果步骤三中这个单元的所有未论证的解为空，则说明上一次随机填入有误。机型步骤五。
* 步骤五：退到上一个单元，将上一次随机填入解从未论证随机解中删除，进行步骤三。
* 步骤六：如果步骤二中这个单元的所有可能解为空，则说明上一次随机填入有误，进行步骤五。
* 步骤七：如果发现已经无法选择单元，即单元已经填满，说明完成解数独。

**流程图:**
![image](https://github.com/huolizhuminh/AndroidSkills/blob/master/JavaSkillDemo/images/shuduprocess.jpg)

总体而言，整个过程是一个选择空单元，求合适解，选择未论证解，论证，论证之后再返回的过程。
## 代码实现

### 算法的实现步骤
**输入**
* 获取一个大小为81的范围为0-9的整型数组，0代表空。
* 新建一个9*9的二维单元结点数组，每个节点保存节点的位置，并将整形数组的值传给节点。
* 新建9个列的节点数组集合，9个行的节点数组集合，9个组的节点数组集合。每个节点保存保存所在列的节点数组集合，所在行的节点数组结合，所在组的节点数组结合。
**输入代码**
```

	private void initData(char[] charArray) {
//新建各个节点对应步骤二
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
//每个节点保存保存所在列的节点集合，所在行的节点集合，所在组的结合。对应步骤三
		for(int i=0 ;i<9;i++){
			SoduNode [] listNode=new SoduNode[9];
			for(int k=0;k<9;k++){
				listNode[k]=soduNodes[i][k];
				soduNodes[i][k].listNode=listNode;
			}
			System.out.println("listNode:"+SoduNode.getNodesValue(listNode));
		}
		for(int i=0 ;i<9;i++){
			SoduNode [] rowNode=new SoduNode[9];
			for(int k=0;k<9;k++){
				rowNode[k]=soduNodes[k][i];
				soduNodes[k][i].rowNode=rowNode;
			}
			System.out.println("rowNode:"+SoduNode.getNodesValue(rowNode));
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
				System.out.println("groupNode:"+SoduNode.getNodesValue(groupNode));
			}
		}
	
	}
```
**求解**
代码求解过程与玩数独的流程基本一致：
**核心代码**
```
//对应步骤一
private boolean getNextNodeAndCheck() {
	SoduNode lastNullNode = getNextNullNode(); //步骤二
		if(lastNullNode==null){
			return true;
		}     //对应步骤七
		Integer[] suitValue = lastNullNode.getSuitValue();
		if(suitValue==null||suitValue.length==0){
			System.out.println("no have suitable value");
			return false;
		}//对应步骤六
		for(int i=0;i<suitValue.length;i++){
			lastNullNode.value=suitValue[I];//步骤三
                       //对应步骤一
			if(getNextNodeAndCheck()){
				return true;
			}
			
		}
		return false;//所有可能解都已经论证完，无需论证解，对应步骤四
}
```
**获取可能解的过程**
```
Integer[] getSuitValue() {
    	TreeSet<Integer> notSuits = new TreeSet<>();
		TreeSet<Integer> allValue=	new TreeSet();
		for (int i = 0; i <= 8; i++) {
			if (listNode[i].value != 0) {
				notSuits.add(listNode[i].value);
			}
			if (rowNode[i].value != 0) {
				notSuits.add(rowNode[i].value);
			}
			if (groupNode[i].value != 0) {
				notSuits.add(groupNode[i].value);
			}
			
			allValue.add(allChar[i]);
		}
		int notSuitSize = notSuits.size();
		if (notSuitSize == 9) {
			return null;
		}
		allValue.removeAll(notSuits);
		System.out.println("allValue :" + getTreeValue(allValue));
		Integer[]suitValue=new Integer[allValue.size()];
		Iterator<Integer> iterator = allValue.iterator();
		int suitValueIndex=0;
		while(iterator.hasNext()){
			suitValue[suitValueIndex]=iterator.next();
			suitValueIndex++;
		}
		System.out.println("suitValue size:"+suitValue.length+" value:"+getSuitsValue(suitValue));
		return suitValue;
	}
```
获取可能解的逻辑为
* 1.获取节点所在列、行、组所有存在的值的集合
* 2.将一个1到9的集合减掉存在值的集合即为可能解
## 运行
将上图数独求解
 ```
请输入81个数字代表数独：/n
009100000040078023600402001032680057895000030064310000407863915008590762956721040
initdata is :
0 0 9 1 0 0 0 0 0 
0 4 0 0 7 8 0 2 3 
6 0 0 4 0 2 0 0 1 
0 3 2 6 8 0 0 5 7 
8 9 5 0 0 0 0 3 0 
0 6 4 3 1 0 0 0 0 
4 0 7 8 6 3 9 1 5 
0 0 8 5 9 0 7 6 2 
9 5 6 7 2 1 0 4 0 
resut is :
2 8 9 1 3 6 5 7 4 
5 4 1 9 7 8 6 2 3 
6 7 3 4 5 2 8 9 1 
1 3 2 6 8 9 4 5 7 
8 9 5 2 4 7 1 3 6 
7 6 4 3 1 5 2 8 9 
4 2 7 8 6 3 9 1 5 
3 1 8 5 9 4 7 6 2 
9 5 6 7 2 1 3 4 8 
```
## 联系我
邮箱：821579706@qq.com
微信：zhminh