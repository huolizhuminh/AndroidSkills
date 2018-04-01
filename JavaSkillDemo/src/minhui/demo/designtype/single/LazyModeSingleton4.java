
package minhui.demo.designtype.single;

import java.io.ObjectStreamException;

import sun.security.jca.GetInstance;

/**
 * 这种做法实际上还是线程不安全的：instance = new LazyModeSingleton3();
 * 实际上有三步申请内存空间、初始化对象、将instance指向步骤一申请的内存空间，如果有指令重排则可能先完成三，再完成二，导致逻辑错误
 */
public class LazyModeSingleton4 {
	private static LazyModeSingleton4 instance;

	private LazyModeSingleton4() {

	}

	public static LazyModeSingleton4 getInstance() {
		if (instance == null) {
			synchronized (LazyModeSingleton4.class) {
				if (instance == null) {
					instance = new LazyModeSingleton4();
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
