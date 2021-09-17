package org.yangmin;


import org.junit.Test;
import org.yangmin.launcher.XClassLoader;
import org.yangmin.launcher.XlassFileLoader;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws ClassNotFoundException,
            NoSuchMethodException, IllegalAccessException,
            InstantiationException, InvocationTargetException {
        //加载Hello类
        XClassLoader instance = XClassLoader.getInstance();
        Class<?> aClass = instance.loadClass("Hello");
        // 获取实例并调用hello方法
        Object obj1 = aClass.newInstance();
        Method methods = aClass.getMethod("hello");
        methods.invoke(obj1);
    }

}
