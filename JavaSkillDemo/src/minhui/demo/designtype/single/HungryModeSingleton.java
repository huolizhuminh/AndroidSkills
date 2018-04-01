
package minhui.demo.designtype.single;

import java.io.ObjectStreamException;

import sun.security.jca.GetInstance;

public class HungryModeSingleton {
	private static HungryModeSingleton instance = new HungryModeSingleton();
    private HungryModeSingleton(){
    	
    }
    public static HungryModeSingleton getInstance(){
    	return instance;
    }
    private Object readResolve() throws ObjectStreamException {
        // instead of the object we're on,
        // return the class variable INSTANCE
        return instance;
    }
}
