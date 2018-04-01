
package minhui.demo.designtype.appearance.facesystem;

import minhui.demo.designtype.appearance.facesystem.systemone.Systemone;
import minhui.demo.designtype.appearance.facesystem.systemtwo.Systemtwo;

/**
 * 外观模式实际上是很多库采用的一种模式，大部分时候只提供一个类对外暴露API，调用者无需知道里面的逻辑
 */
public class Face {
	Systemone systemone;
	Systemtwo systemtwo;

	public Face() {
		systemone = new Systemone();
		systemtwo = new Systemtwo();

	}

	public void start() {
		systemone.start();
		systemtwo.start();
	}
}
