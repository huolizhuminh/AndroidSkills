
package minhui.demo.designtype.templatemethod;

public class FilmB extends BaseFilm {

	@Override
	void begin() {
		System.out.println(" FilmB start guanggao");
	}

	@Override
	void process() {
		System.out.println(" FilmB film");
	}

	@Override
	void finish() {
		System.out.println("FilmB music");
	}

}
