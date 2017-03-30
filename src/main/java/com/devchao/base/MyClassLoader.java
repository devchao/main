package com.devchao.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

class MyClassLoader extends ClassLoader {  
   
	private String classFileName;
	
	@Override
	public Class<?> loadClass(String name) throws ClassNotFoundException {
		System.out.println("MyClassLoader was called");
		return super.loadClass(name);
	}
	
	
	@Override  
    public Class<?> findClass(String name) throws ClassNotFoundException {  
		
        InputStream in = null;
		try {
			in = new FileInputStream(classFileName);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
        byte[] b = null;  
        try {  
            b = new byte[in.available()];  
            in.read(b);  
            in.close();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
        return defineClass(name, b, 0, b.length);  
    }


	public String getClassFileName() {
		return classFileName;
	}


	public void setClassFileName(String classFileName) {
		this.classFileName = classFileName;
	}  
	
}  
