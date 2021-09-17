package org.yangmin.launcher;

/**
 * @author ym
 * @Title: XClassLoader
 * @Package org.yangmin.launcher
 * @Description: 自定义类加载器，用于加载加密Class
 * @date 2021/9/17 15:05
 **/
public class XClassLoader extends ClassLoader {

    private static volatile XClassLoader classLoader;

    private  XClassLoader(){}

    public static XClassLoader getInstance(){
        if(classLoader == null){
            synchronized (XClassLoader.class){
                if(classLoader == null){
                    classLoader = new XClassLoader();
                }
            }
        }
        return classLoader;
    }


    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        byte[] buffer = XlassFileLoader.getClassByte(name);
        if(null == buffer){
            throw  new ClassNotFoundException("class '"+name+"' not found.  ");
        }
        return defineClass(name,buffer,0,buffer.length);
    }
}
