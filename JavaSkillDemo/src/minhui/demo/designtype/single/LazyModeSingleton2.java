
package minhui.demo.designtype.single;

import java.io.ObjectStreamException;

import sun.security.jca.GetInstance;

public class LazyModeSingleton2 {
	private static LazyModeSingleton2 instance ;
    private LazyModeSingleton2(){
    	
    }
    public static synchronized LazyModeSingleton2 getInstance(){
    	if(instance==null){
    		instance=new LazyModeSingleton2();
    	}
    	return instance;
    }
    private Object readResolve() throws ObjectStreamException {
		// instead of the object we're on,
		// return the class variable INSTANCE
		return getInstance();
	}
}
