package minhui.demo.sodusolve;

import java.util.Scanner;

import minhui.demo.DemoInterface;

public class myClass implements DemoInterface{

	@Override
	public void startRun() {
		// TODO Auto-generated method stub
		String testStr = "";
		
		do{
	
			@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
			testStr = scan.nextLine();
		}
		while(new Sudoku().checkStr(testStr)==false);
		
		new Solver(new Sudoku().StrToForm(testStr));
		
	}


}
