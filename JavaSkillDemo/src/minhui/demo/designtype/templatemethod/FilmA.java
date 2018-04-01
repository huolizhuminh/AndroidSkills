
package minhui.demo.designtype.templatemethod;

public class FilmA extends BaseFilm {

	@Override
	void begin() {
		System.out.println(" FilmA start music");
	}

	@Override
	void process() {
		System.out.println(" FilmA film");
	}

	@Override
	void finish() {
		System.out.println("FileB interview");
	}

}
