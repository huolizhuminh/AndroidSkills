package minhui.demo.sodusolve;

public class Copy1To2 {
	public Copy1To2(int[][][] form1,int[][][] form2){
		for(int i=0;i<10;i++){
			 for(int j=0;j<10;j++){
				 for(int k=0;k<10;k++){
					 form2[i][j][k]=form1[i][j][k];
				 }
			 }
		 }
	}
}
