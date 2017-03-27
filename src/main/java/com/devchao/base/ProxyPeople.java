package com.devchao.base;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class ProxyPeople  implements MethodInterceptor {
	 
	    public Object intercept(Object obj, Method method, Object[] arg, MethodProxy proxy) throws Throwable {
	    	if(method.getName().equals("smile")) {
		        System.out.println("Before:" + method);  
		        Object object = proxy.invokeSuper(obj, arg);
		        System.out.println("After:" + method); 
		        return object;
	    	} else {
	    		
	    	return proxy.invokeSuper(obj, arg);
	    	}
	    }
 
}
