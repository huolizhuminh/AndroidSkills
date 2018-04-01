
package minhui.demo.designtype.single;

import java.io.ObjectStreamException;

import com.sun.org.apache.regexp.internal.recompile;

//实际上与LazyModesingleton4类似
public class InnerClassSingleton {
	static class InnerClass {
		static InnerClassSingleton instance = new InnerClassSingleton();
	}

	private InnerClassSingleton() {

	}

	public InnerClassSingleton getInstance() {
		return InnerClass.instance;
	}

	private Object readResolve() throws ObjectStreamException {
		// instead of the object we're on,
		// return the class variable INSTANCE
		return getInstance();
	}
}
