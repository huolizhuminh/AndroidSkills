
package minhui.demo.arithmatic;

import java.util.Scanner;

import minhui.demo.DemoInterface;

/**
 * 有一个很长二进制串，求出除以3的余数是多少，给出算法的时间复杂度。
 */
public class ArithMatic5 implements DemoInterface {

	@Override
	public void startRun() {
		Scanner sc = new Scanner(System.in);
		System.out.println("1输入字符：");
		String m = sc.next();
		int remain;
		try {
			remain = startDivideThree(m);
			System.out.println("remain is"+remain);
		} catch (Exception e) {
			System.out.println("can not dive");
			e.printStackTrace();
		}

	}

	private int startDivideThree(String m) throws Exception {
		int remain=0;
		for(int i=0;i<m.length();i++){
			char chars = m.charAt(i);
			if(chars=='0'){
				remain=(remain*2)%3;
			}else if(chars=='1'){
				remain=(remain*2+1)%3;
			}else{
				throw  new Exception();
			}
		}
		return remain;
	}

}
