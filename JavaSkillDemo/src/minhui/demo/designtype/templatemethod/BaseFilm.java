
package minhui.demo.designtype.templatemethod;

public abstract class BaseFilm {
	abstract void begin();

	abstract void process();

	abstract void finish();

	public void excute() {
		System.out.println("start play film");
		begin();
		process();
		finish();
		System.out.println("end play film ");
	}
}
