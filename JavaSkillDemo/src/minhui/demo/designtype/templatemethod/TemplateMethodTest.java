
package minhui.demo.designtype.templatemethod;

import minhui.demo.DemoInterface;

public class TemplateMethodTest implements DemoInterface {

	@Override
	public void startRun() {
		FilmA filmA = new FilmA();
		FilmB filmB = new FilmB();
		filmA.excute();
		filmB.excute();
	}

}
