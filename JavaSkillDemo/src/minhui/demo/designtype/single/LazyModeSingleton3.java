
package minhui.demo.designtype.single;

import java.io.ObjectStreamException;

import sun.security.jca.GetInstance;

/**
 * 这种做法才是安全的
 */
public class LazyModeSingleton3 {
	private static volatile LazyModeSingleton3 instance;

	private LazyModeSingleton3() {

	}

	public static LazyModeSingleton3 getInstance() {
		if (instance == null) {
			synchronized (LazyModeSingleton3.class) {
				if (instance == null) {
					instance = new LazyModeSingleton3();
				}
			}
		}
		return instance;
	}
	private Object readResolve() throws ObjectStreamException {
		// instead of the object we're on,
		// return the class variable INSTANCE
		return getInstance();
	}
}
