package minhui.demo.sodusolve;

public class Algorithm_2 {
	private int[][][] demo = new int[10][10][10];
	private int[][][] test = new int[10][10][10];
	public Algorithm_2(	int[][][] formIn){
		new Copy1To2(formIn, demo);

		for(int i=1;i<10;i++){
			for(int j=1;j<10;j++){
				if(demo[i][j][0]==0){
					for(int k=1;k<10;k++){
						if(demo[i][j][k]==1){
							new Copy1To2(demo, test);
							test[i][j][0]=k;
							new Sudoku().excludePointNote(i,j, test);
							Algorithm_1 myAl_1 = new Algorithm_1(test);
							if(new Sudoku(myAl_1.getForm()).check()==false){
								demo[i][j][k]=0;
							}
						}
					}
				}
			}
		}
	}
	public int[][][] getForm(){
		return demo;
	}
	
	
}
