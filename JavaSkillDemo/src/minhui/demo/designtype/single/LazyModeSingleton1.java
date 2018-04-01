
package minhui.demo.designtype.single;

import java.io.ObjectStreamException;

import sun.security.jca.GetInstance;

public class LazyModeSingleton1 {
	private static LazyModeSingleton1 instance ;
    private LazyModeSingleton1(){
    	
    }
    public static LazyModeSingleton1 getInstance(){
    	if(instance==null){
    		instance=new LazyModeSingleton1();
    	}
    	return instance;
    }
    private Object readResolve() throws ObjectStreamException {
		// instead of the object we're on,
		// return the class variable INSTANCE
		return getInstance();
	}
}
